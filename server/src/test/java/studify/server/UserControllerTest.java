package studify.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.h2.engine.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import studify.server.entities.AppUser;
import studify.server.repository.UserRepository;
import studify.server.service.UserService;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private AppUser user1, user2, user3;

    @BeforeEach
    public void setup() {
        userService = new UserService(userRepository);

        userRepository.deleteAll();

        user1 = new AppUser("User1");
        user2 = new AppUser("User2");
        user3 = new AppUser("User3");
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<String> expectedUsernames = Arrays.asList(user1.username, user2.username, user3.username);

        mockMvc.perform(get("/api/users/"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[*].username", containsInAnyOrder(expectedUsernames.toArray())));
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get("/api/users/{username}", "User1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.username").value("User1"));
    }

    @Test
    public void testAddUser() throws Exception {
        // Create an instance of AppUser
        AppUser user = new AppUser("newUser");
        user.experience = 100;
        user.level = 200;

        // Perform the POST request and pass the user as the request body
        MvcResult result = mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isCreated())
                .andReturn();

        // Get the response content as a string
        String responseContent = result.getResponse().getContentAsString();

        // Deserialize the response into an AppUser object
        ObjectMapper objectMapper = new ObjectMapper();
        AppUser actualUser = objectMapper.readValue(responseContent, AppUser.class);

        // Assert the properties of the actual user
        assertEquals(user.username, actualUser.username);
        assertEquals(user.experience, actualUser.experience);
        assertEquals(user.level, actualUser.level);
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





    @Test
    public void testNewUser() throws Exception {
        // Create an instance of AppUser
        AppUser user = new AppUser("newUser");

        // Perform the POST request and pass the user as the request body
        MvcResult result = mockMvc.perform(post("/api/users/new/newUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isCreated())
                .andReturn();

        // Get the response content as a string
        String responseContent = result.getResponse().getContentAsString();

        // Deserialize the response into an AppUser object
        ObjectMapper objectMapper = new ObjectMapper();
        AppUser actualUser = objectMapper.readValue(responseContent, AppUser.class);

        // Assert the properties of the actual user
        assertEquals(user.username, actualUser.username);
        assertEquals(user.experience, actualUser.experience);
        assertEquals(user.level, actualUser.level);
    }



}
