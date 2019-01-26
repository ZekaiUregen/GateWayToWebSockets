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
 * Gateway controls all traffic by this class.
 *
 * @author Zekai_Uregen
 */
@Component
public class SocketHandler extends TextWebSocketHandler {

	// Which client session uses which target WebSocketserver mapping.
	HashMap<WebSocketSession, WebSocketClient> wsSessionsSubWsClientsMap = new HashMap<WebSocketSession, WebSocketClient>();

	private WebSocketClient clientEndPoint;

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {

		Map<String, String> value = new Gson().fromJson(message.getPayload(), Map.class);

		System.out.println("session id:" + session.getId() + " sends a message to Gateway Web Socket Server ");

		wsSessionsSubWsClientsMap.get(session).sendMessage(message.getPayload());

		System.out.println("Gateway Web Socket Server sends coming message from session id " + session.getId() + " to target web socket.");

		// wait 5 seconds for messages from websocket
		Thread.sleep(5000);
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		System.out.println("session id:" + session.getId() + " connected to gateway Web Socket Server by" + session.getUri());

		String query = session.getUri().getQuery();

		try {
			// open websocket
			clientEndPoint = new WebSocketClient(new URI(query.substring(query.indexOf("=") + 1)));
			System.out.println("GateWay Web Socket Server connected to target Web Socket ip:" + query.substring(query.indexOf("=") + 1) + " for client session:" + session.getId());

			// Which client session uses which target WebSocketserver mapping. Putting.
			wsSessionsSubWsClientsMap.put(session,clientEndPoint);

			// Add listener to target webclient socket for handling message.
			clientEndPoint.addMessageHandler(new WebSocketClient.MessageHandler() {
				public void handleMessage(String message) {

					try {
						session.sendMessage(new TextMessage(message));
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("Gateway Web Socket Server takes massage from target web socket and sends to client " + message);
				}
			});


		} catch (URISyntaxException ex) {
			session.sendMessage(new TextMessage("A probllem occured while connecting target web socket ip:" + query.substring(query.indexOf("=") + 1)));
		}
	}

}