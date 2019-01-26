package config;

import gateway.SocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * GateWay to WebSockets
 *
 * @author Zekai_Uregen
 */
@Configuration
@EnableWebSocket
public class WebSocketServerConfig implements WebSocketConfigurer {


	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new SocketHandler(), "/gateway");
	}

}