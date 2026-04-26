package com.bashayer.demo;

import com.bashayer.demo.repository.UserRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        jdbcTemplate.execute("ALTER TABLE users AUTO_INCREMENT=1");
    }

    @Test
    void testAddUserAutomation() throws Exception {
        String userJson = "{\"name\":\"Ahmad\",\"email\":\"Ahmad@Test\"}";

        mockMvc.perform(post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ahmad"))
                .andExpect(jsonPath("$.email").value("Ahmad@Test"));

        assertTrue(userRepository.existsById(1L));
    }

    @Test
    void testAddUsersWithMissingName() throws Exception {
        String invalidUserJson = "{\"email\":\"Reem@Test\"}";

        mockMvc.perform(post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidUserJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAddUserThenGetUserById() throws Exception {
        String userJson = "{\"name\":\"Alle\",\"email\":\"Alle@Test\"}";

        String result = mockMvc.perform(post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer id = JsonPath.read(result, "$.id");

        mockMvc.perform(get("/api/users/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alle"))
                .andExpect(jsonPath("$.email").value("Alle@Test"));
    }

    @Test
    void testUpdateUserAutomation() throws Exception {
        String initialUser = "{\"name\":\"Alle\",\"email\":\"Alle@Test\"}";

        String result = mockMvc.perform(post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(initialUser))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer id = JsonPath.read(result, "$.id");

        String userUpdateJson = "{\"name\":\"Mohamad\",\"email\":\"mohamad@Test\"}";

        mockMvc.perform(put("/api/users/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userUpdateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mohamad"))
                .andExpect(jsonPath("$.email").value("mohamad@Test"));

        assertTrue(userRepository.findById(id.longValue()).isPresent());
        assertTrue(userRepository.findById(id.longValue())
                .get()
                .getName()
                .equals("Mohamad"));
    }


    @Test
    void testDeleteUserAutomation() throws Exception {
        String result = mockMvc.perform(post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"testD\",\"email\":\"TestD@Test\"}"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer id = JsonPath.read(result, "$.id");

        mockMvc.perform(delete("/api/users/" + id))
                .andExpect(status().isNoContent());

        assertFalse(userRepository.findById(id.longValue()).isPresent());
    }
    // ... existing code ...

    @Test
    void testAddUserWithDuplicateEmail() throws Exception {
        String firstUser = "{\"name\":\"First User\",\"email\":\"duplicate@test.com\"}";
        String secondUser = "{\"name\":\"Second User\",\"email\":\"duplicate@test.com\"}";

        mockMvc.perform(post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(firstUser))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(secondUser))
                .andExpect(status().isBadRequest());

        org.junit.jupiter.api.Assertions.assertEquals(1, userRepository.count());
    }

// ... existing code ...

    @Test
    void testAddUserWithInvalidEmail() throws Exception {
        String invalidUserJson = "{\"name\":\"Rayan\",\"email\":\"not-an-email\"}";

        mockMvc.perform(post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidUserJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAddUserWithEmptyName() throws Exception {
        String emptyUserJson = "{\"name\":\"\",\"email\":\"empty@Test\"}";

        mockMvc.perform(post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(emptyUserJson))
                .andExpect(status().isBadRequest());
    }
    // ... existing code ...

    @Test
    void testSearchUsersByName() throws Exception {
        mockMvc.perform(post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Bashayer\",\"email\":\"bashayer@test.com\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Ahmad\",\"email\":\"ahmad@test.com\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/users/search")
                        .param("name", "Bash"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Bashayer"))
                .andExpect(jsonPath("$[0].email").value("bashayer@test.com"));
    }

    @Test
    void testAddUserWithShortName() throws Exception {
        String invalidUserJson = "{\"name\":\"A\",\"email\":\"shortname@test.com\"}";

        mockMvc.perform(post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidUserJson))
                .andExpect(status().isBadRequest());
    }


    @Test
    void testGetNonExistingUserReturnsNotFound() throws Exception {
        mockMvc.perform(get("/api/users/9999"))
                .andExpect(status().isNotFound());
    }
// ... existing code ...

    @Test
    void testUpdateNonExistingUserReturnsNotFound() throws Exception {
        String updateJson = "{\"name\":\"Updated User\",\"email\":\"updated@test.com\"}";

        mockMvc.perform(put("/api/users/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isNotFound());
    }
// ... existing code ...

    @Test
    void testDeleteNonExistingUserReturnsNotFound() throws Exception {
        mockMvc.perform(delete("/api/users/9999"))
                .andExpect(status().isNotFound());
    }
// ... existing code ...

    @Test
    void testSearchUsersByNonExistingNameReturnsEmptyList() throws Exception {
        mockMvc.perform(post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Bashayer\",\"email\":\"bashayer@test.com\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/users/search")
                        .param("name", "NotFoundName"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
// ... existing code ...

    @Test
    void testUpdateUserWithDuplicateEmail() throws Exception {
        String firstUserResult = mockMvc.perform(post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"First User\",\"email\":\"first@test.com\"}"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        mockMvc.perform(post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Second User\",\"email\":\"second@test.com\"}"))
                .andExpect(status().isOk());

        Integer firstUserId = JsonPath.read(firstUserResult, "$.id");

        String updateJson = "{\"name\":\"First User Updated\",\"email\":\"second@test.com\"}";

        mockMvc.perform(put("/api/users/" + firstUserId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isBadRequest());
    }

// ... existing code ...
}