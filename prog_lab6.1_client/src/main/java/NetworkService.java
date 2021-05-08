import commandDescriptions.CommandDescription;
import exceptions.LimitOfReconnectionsException;
import utils.Response;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class NetworkService {
    private String host;
    private int port;
    private int reconnectionTimeout;
    private int maxReconnectionAttempts;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public NetworkService(String host, int port, int maxReconnectionAttempts, int reconnectionTimeout) throws LimitOfReconnectionsException, UnknownHostException {
        this.host = host;
        this.port = port;
        this.maxReconnectionAttempts = maxReconnectionAttempts;
        this.reconnectionTimeout = reconnectionTimeout;
        connect();
    }

    private void connect() throws UnknownHostException, LimitOfReconnectionsException {
        int attempts = 0;
        while(attempts++ < maxReconnectionAttempts) {
            try {
                socket = new Socket(host, port);
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                System.out.println("Соединение установлено");
                return;
            } catch (UnknownHostException e) {
                throw e;
            } catch (IOException e) {
                System.out.println("Сервер недоступен, попытка переподключения...");
                try {
                    Thread.sleep(reconnectionTimeout * 1000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }
        throw new LimitOfReconnectionsException();
    }

    public void send(CommandDescription commandDescription) throws LimitOfReconnectionsException {
        try {
            objectOutputStream.writeObject(commandDescription);
        } catch (IOException e) {
            try {
                connect();
            } catch (UnknownHostException ignored) { }
        }
    }

    public Response read() throws LimitOfReconnectionsException {
        try {
            while (objectInputStream.available() > 0) {
                System.out.println("Чтение из потока сервера");
                return (Response) objectInputStream.readObject();
            }
        } catch (IOException ioException) {
            try {
                connect();
            }
            catch (LimitOfReconnectionsException limitOfReconnectionsException){
                throw limitOfReconnectionsException;
            }
            catch (UnknownHostException ignored) { }
        } catch (ClassNotFoundException e) { e.printStackTrace();}
        return null;
    }

}