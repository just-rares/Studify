package studify.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class WebSocketStompUtils {
    private static String server;
    private OkHttpClient httpClient;
    private StompSession session;

    public WebSocketStompUtils() {
        this.httpClient = new OkHttpClient();
    }

    public void establishConnection(String serverURL) throws Exception {
        server = serverURL;

        try {
            Request request = new Request.Builder()
                .url(server + "/api/connect")
                .build();

            try (Response response = httpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
            } catch (IOException e) {
                throw new Exception("Error establishing RESTful connection: " + e.getMessage(), e);
            }

            String websocketURL = serverURL.replace("http", "ws");
            session = connect(websocketURL + "/websocket");
        } catch (Exception e) {
            throw new Exception("Error establishing WebSocket connection: " + e.getMessage(), e);
        }
    }

    private StompSession connect(String url) {
        var client = new StandardWebSocketClient();
        var stomp = new WebSocketStompClient(client);
        stomp.setMessageConverter(new MappingJackson2MessageConverter());

        try {
            return stomp.connect(url, new StompSessionHandlerAdapter() {
            }).get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        throw new IllegalStateException();
    }

    public <T> StompSession.Subscription registerForMessages(String dest, Consumer<T> consumer) {
        return session.subscribe(dest, new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                // Use TypeReference and ObjectMapper to determine the payload type
                ObjectMapper objectMapper = new ObjectMapper();
                TypeReference<T> typeRef = new TypeReference<>() {};
                return objectMapper.constructType(typeRef);
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                consumer.accept((T) payload);
            }
        });
    }
}
