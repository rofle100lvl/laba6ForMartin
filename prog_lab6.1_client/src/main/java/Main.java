import exceptions.LimitOfReconnectionsException;

import java.io.IOException;
import java.io.ObjectOutput;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {
        try {
            App app = new App();
            app.run();
        } catch (LimitOfReconnectionsException limitOfReconnectionsException) {
            limitOfReconnectionsException.printStackTrace();
        } catch (UnknownHostException unknownHostException) {
            unknownHostException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
