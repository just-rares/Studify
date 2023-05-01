package studify.utils;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import studify.dto.User;

import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;




public class ServerUtils {
    private static final String BASE_URL = "http://localhost:8080/";
    private final OkHttpClient httpClient;

    public ServerUtils() {
        this.httpClient = new OkHttpClient();
    }



    public String registerUser(String username) throws IOException {
        Request request = new Request.Builder()
            .url(BASE_URL + "api/users/new/" + username)
            .post(RequestBody.create(new byte[0], MediaType.parse("application/json; charset=utf-8")))
            .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    public String addActivity(String title) throws IOException {
        Request request = new Request.Builder()
            .url(BASE_URL + "api/activities/new/" + title)
            .post(RequestBody.create(new byte[0], MediaType.parse("application/json; charset=utf-8")))
            .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
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
    public List<User> getUsers() throws IOException {
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
            List<User> users = gson.fromJson(responseBody, userListType);
            return users;
        }
    }

    public boolean connectionCheck() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "test")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return true;
        }
    }


}
