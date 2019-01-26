package config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * GateWay to WebSockets
 *
 * By running as java application embbeded tomcat server starts to run.
 * And gateway Websocket Server starts to listen clients
 *
 * @author Zekai_Uregen
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
