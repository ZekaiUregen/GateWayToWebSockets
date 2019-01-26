package gateway;

import com.google.gson.Gson;
import config.WebSocketClient;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * GateWay to WebSockets
 *
 * @author Zekai_Uregen
 */
@Component
public class SocketHandler extends TextWebSocketHandler {

	HashMap<WebSocketSession, WebSocketClient> wsSessionsSubWsClientsMap = new HashMap<WebSocketSession, WebSocketClient>();

	private WebSocketClient clientEndPoint;

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {
		Map<String, String> value = new Gson().fromJson(message.getPayload(), Map.class);
		System.out.println("buraya girdi");

		wsSessionsSubWsClientsMap.get(session).sendMessage("Urgenss " +message.getPayload());

		// wait 5 seconds for messages from websocket
		Thread.sleep(5000);
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//the messages will be broadcasted to all users.
		String query = session.getUri().getQuery();



		try {
			// open websocket
			clientEndPoint = new WebSocketClient(new URI(query.substring(query.indexOf("=") + 1)));
			System.out.println(query.substring(query.indexOf("=") + 1) + " ip li sockete bağlandı : ");
			wsSessionsSubWsClientsMap.put(session,clientEndPoint);

			// add listener
			clientEndPoint.addMessageHandler(new WebSocketClient.MessageHandler() {
				public void handleMessage(String message) {
					try {
						session.sendMessage(new TextMessage(message));
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println(message);
				}
			});


		} catch (URISyntaxException ex) {
			System.err.println("URISyntaxException exception: " + ex.getMessage());
		}
	}

}