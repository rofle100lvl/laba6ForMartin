

import commandDescriptions.CommandDescription;
import support.CommandName;
import utils.CollectionManager;
import utils.CommandManager;
import utils.Parser;
import utils.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NIOServer {

    private static final Map<SocketChannel, CommandDescription> sockets = new ConcurrentHashMap<>();
    private CommandManager commandManager;
    private CollectionManager collectionManager;
    private ServerSocketChannel serverChannel;
    Selector selector;

    public NIOServer() throws IOException {
        collectionManager = Parser.fromXmlToObject("collection.txt");
        commandManager = new CommandManager(collectionManager);
        collectionManager.setCommandManager(commandManager);
        serverChannel = ServerSocketChannel.open();
        serverChannel.socket().bind(new InetSocketAddress(45001));
        serverChannel.configureBlocking(false);
    }

    public void run() throws IOException {
        selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            for (SelectionKey key : selector.selectedKeys()) {
                if (key.isValid()) {
                    try {
                        if (key.isAcceptable()) {
                            SocketChannel socketChannel = serverChannel.accept();
                            socketChannel.configureBlocking(false);
                            log("Connected " + socketChannel.getRemoteAddress());
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        } else if (key.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            try {
                                byte[] buffer = new byte[2048];
                                int bytesRead = socketChannel.read(ByteBuffer.wrap(buffer));
                                log("Reading from " + socketChannel.getRemoteAddress() + ", bytes read=" + bytesRead);

                                if (bytesRead == -1) {
                                    log("Connection closed " + socketChannel.getRemoteAddress());
                                    sockets.remove(socketChannel);
                                    socketChannel.close();
                                }

                                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream
                                        (buffer));
                                sockets.put(socketChannel, (CommandDescription) objectInputStream.readObject());
                                // Detecting end of the message
                                socketChannel.register(selector, SelectionKey.OP_WRITE);
                            }catch (IOException ignored){
                                socketChannel.close();
                                System.out.println("Подключение с клиентом разорвано");
                            }

                        } else if (key.isWritable()) {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            byte[] buffer = new byte[1024];
                            Response response = commandManager.launchCommand(sockets.get(socketChannel));

                            // делаем ответ
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2048);
                            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);

                            outputStream.writeObject(response);
                            ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());

                            int bytesWritten = socketChannel.write(byteBuffer);
                            log("Writing to " + socketChannel.getRemoteAddress() + ", bytes written=" + bytesWritten);

                            if (sockets.get(socketChannel).getName() == CommandName.EXIT){
                                socketChannel.close();
                                continue;
                            }
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        log("Клиент отключился от сервера");
                        e.printStackTrace();
                    }
                }
            }

            selector.selectedKeys().clear();
        }
    }

    private static void log(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + message);
    }
}