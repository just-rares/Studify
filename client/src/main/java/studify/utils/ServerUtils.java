package studify.utils;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;


public class ServerUtils {
    private static final String BASE_URL = "http://localhost:8080/api/users";
    private final OkHttpClient httpClient;

    public ServerUtils() {
        this.httpClient = new OkHttpClient();
    }

    public String registerUser(String username) throws IOException {
        Request request = new Request.Builder()
            .url(BASE_URL + "/" + username)
            .post(RequestBody.create(new byte[0], MediaType.parse("application/json; charset=utf-8")))
            .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }
}
