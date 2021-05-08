

import exceptions.LimitOfReconnectionsException;
import utils.Response;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;


public class Connector {
    InetSocketAddress serverAddress;
    SocketChannel client;
    private int reconnectionTimeout = 5;
    private int maxReconnectionAttempts = 10;
    ByteArrayOutputStream b1 = new ByteArrayOutputStream(1024);
    ObjectOutputStream outputStream;
    ObjectInput input;
    boolean isFull;
    int lastByte = 0;

    ByteBuffer byteBuffer;
    byte[] buffer = new byte[1024];

    public Connector(int PORT) throws LimitOfReconnectionsException {
        try {
            serverAddress = new InetSocketAddress("localhost", PORT);
            client = SocketChannel.open(serverAddress);
            client.configureBlocking(false);
            lastByte = 0;
            outputStream = new ObjectOutputStream(b1);
            isFull = true;
        }catch (ConnectException e){
            System.out.println("Не возможно подключиться к серверу");
            reconnect();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Object data) throws LimitOfReconnectionsException {
        try {
            b1 = new ByteArrayOutputStream(2048);
            outputStream = new ObjectOutputStream(b1);
            outputStream.writeObject(data);
            byteBuffer = ByteBuffer.wrap(b1.toByteArray());
            client.write(byteBuffer);


        } catch (IOException e) {
            reconnect();
        }
    }

    private void reconnect() throws LimitOfReconnectionsException {
        try {
            client.close();
        } catch (IOException | NullPointerException ignored) {
        }
        int attempts = 0;
        while (attempts < maxReconnectionAttempts) {
            System.out.println("Попытка переподключения...");
            try {
                client = SocketChannel.open(serverAddress);
                client.configureBlocking(false);
                lastByte = 0;
                outputStream = new ObjectOutputStream(b1);
                isFull = true;
                System.out.println("Подключение восстановлено");
                return;
            } catch (IOException ioException) {
                try {
                    System.out.println("Ожидаем...");
                    Thread.sleep(reconnectionTimeout * 1000);
                    attempts += 1;
                } catch (InterruptedException ignored) { }
            }
        }
        throw new LimitOfReconnectionsException("Время ожидания превышено");
    }


    public Response receive() throws LimitOfReconnectionsException {
        try {
            if(isFull) buffer = new byte[2048];
            lastByte = client.read(ByteBuffer.wrap(buffer, lastByte,1024));
            if(lastByte == 0)return null;
            input = new ObjectInputStream(new ByteArrayInputStream(buffer));
            System.out.println("Ждёт ответ от сервера");
            isFull = true;
            lastByte = 0;
            return (Response) input.readObject();
        }
        catch (StreamCorruptedException e){
            isFull=false;
            return null;
        }
        catch (ClosedByInterruptException | ClassNotFoundException ignored){
            return null;
        }
        catch (IOException e) {
            reconnect();
            return null;
        }

    }

}