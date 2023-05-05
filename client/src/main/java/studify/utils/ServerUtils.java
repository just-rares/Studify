package studify.utils;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import studify.dto.User;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;


import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class ServerUtils {
    private static final String BASE_URL = "http://localhost:8080/";
    private static String server;
    private final OkHttpClient httpClient;
    private boolean isConnected = false;

    private StompSession session;

    public ServerUtils() {
        this.httpClient = new OkHttpClient();
        // TODO make changes to the server;
        String address = "localhost";
        String port = "8080";
        server = "http://" + address + ":" + port + "/";
        try {
            session = connect("ws://localhost:8080/websocket").get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private ListenableFuture<StompSession> connect(String url) {
        var client = new StandardWebSocketClient();
        var stomp = new WebSocketStompClient(client);
        stomp.setMessageConverter(new MappingJackson2MessageConverter());
        isConnected = true;
        return stomp.connect(url, new StompSessionHandlerAdapter() {});
    }


    public <T> void registerForMessage(String dest, Class<T> type, Consumer<T> consumer) {
        session.subscribe(dest, new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return type;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                consumer.accept((T) payload);
            }
        });
    }

    public void send(String dest, Object o) {
        session.send(dest, o);
        System.out.println("Sent " + o.getClass().getName() + " to " + dest);
    }


    /**
     * This method does a POST request to the server
     * following the "api/users/new/{username}" endpoint. This endpoint
     * creates a new user based on a string of the name.
     *
     * @param username String variable for the new user
     *
     * @return Response of the server.
     * @throws IOException In case of errors / unexpected answer from the server
     */
    public String createNewUser(String username) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "api/users/new/" + username)
                .post(RequestBody.create(new byte[0],
                        MediaType.parse("application/json; charset=utf-8")))
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }
    /**
     * This method does a POST request to the server
     * following the "api/activities/new/{username}" endpoint. This endpoint
     * creates a new activity based on a string of the name.
     *
     * @param title String variable for the new user
     *
     * @return Response of the server.
     * @throws IOException In case of errors / unexpected answer from the server
     */
    public String addActivity(String title) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "api/activities/new/" + title)
                .post(RequestBody.create(new byte[0],
                        MediaType.parse("application/json; charset=utf-8")))
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Server returned an error: "
                        + response.code() + " " + response.message());
            }
            return response.body().string();
        } catch (IOException e) {
            throw new IOException("Error making HTTP request: " + e.getMessage(), e);
        }
    }

    /**
     * Sends an HTTP GET request to the server to retrieve a list of all users.
     * This method requires the "com.google.code.gson" dependency.
     * The module must export studify.dto for it to have access to the User class.
     * An empty User constructor has been added to allow the method to create the objects.
     * @return List of Users in the database
     * @throws IOException In case of unwanted response
     * GSON is used to parse the array of users and create objects with it.
     * TODO Learn more about Gson and how to use it for the rest of the GET requests
     */
    public List<User> getUsers() {
        Request request = new Request.Builder()
                .url(BASE_URL + "api/users")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            assert response.body() != null;
            String responseBody = response.body().string();
            Gson gson = new Gson();
            Type userListType = new TypeToken<List<User>>(){}.getType();
            return gson.fromJson(responseBody, userListType);
        }
        catch (IOException e) {
            System.out.println("Error when trying to retrieve the users" +
                    " from the database: " + e.getMessage());
        }
        return null;
    }

    public User getUserByUsername(String username) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "api/users/" + username)
                .build();
        try(Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            if(response.body() == null) {
                System.out.println("User \"" + username + "\" not found");
                return null;
            }
            String responseBody = response.body().string();
            Gson gson = new Gson();
            Type userType = new TypeToken<User>(){}.getType();
            return gson.fromJson(responseBody, userType);
        }
    }

    /**
     * This method's purpose is to check the connection upon loading the app
     * and in case the database file is missing, this requests prompts the server
     * to create a new, empty one.
     *
     * @return True / False whether the connection works
     * @throws IOException In case of unexpected answer from the server
     */
    public boolean connectionCheck() {
        Request request = new Request.Builder()
                .url(BASE_URL + "testConnection")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.isSuccessful();
        } catch (Exception e) {
            System.out.println("Unexpected Error");
        }
        return false;
    }


}
