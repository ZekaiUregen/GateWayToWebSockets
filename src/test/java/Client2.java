import config.WebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * GateWay to WebSockets
 *
 * @author Zekai_Uregen
 */

public class Client2 {

    public static void main(String[] args) {
        try {
            // open websocket
            final WebSocketClient clientEndPoint = new WebSocketClient(new URI("ws://localhost:8080/gateway?client_id=ws://demos.kaazing.com/echo"));

            // add listener
            clientEndPoint.addMessageHandler(new WebSocketClient.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });

            // send message to websocket
            clientEndPoint.sendMessage("{'name':'Zekai'}");

            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }
}
