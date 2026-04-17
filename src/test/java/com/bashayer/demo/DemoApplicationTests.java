package com.bashayer.demo;
import com.bashayer.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
 import org.springframework.boot.test.autoconfigure.*;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DemoApplicationTests {
@Autowired
private MockMvc mockMvc;
@Autowired
private UserRepository userRepository;
@Autowired
private JdbcTemplate jdbcTemplate;
@BeforeAll
void setup() {
    userRepository.deleteAll();
    jdbcTemplate.execute("ALTER TABLE users AUTO_INCREMENT=1");

}
    @Test
    void testAddUserAutomation() throws Exception {
        String userJson = "{\"name\":\"Ahmad\",\"email\":\"Ahmad@Test\"}";
        mockMvc.perform(post("/api/add")
                .contentType(MediaType.APPLICATION_JSON).content(userJson))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Ahmad"))
                .andExpect(jsonPath("$.email").value("Ahmad@Test"));

    }
    @Test
    void testAddUsersWithMissingName() throws Exception {
        String invalidUserJson = "{\"email\":\"Reem@Test\"}";
        mockMvc.perform(post("/api/add")
                .contentType(MediaType.APPLICATION_JSON).content(invalidUserJson))
                .andExpect(status().isBadRequest());

    }
    @Test
    void testAddAnaThenGetUserId() throws Exception {
    String userJson = "{\"name\":\"Alle\",\"email\":\"Alle@Test\"}";
    mockMvc.perform(post("/api/add")
            .contentType(MediaType.APPLICATION_JSON).content(userJson))
            .andExpect(status().isOk());
    mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Mohamad"))
            .andExpect(jsonPath("$.email").value("Alle@Test"));

    }
    @Test
    void testUpdateUserAutomation() throws Exception {
        String initialUser = "{\"name\":\"Alle\",\"email\":\"Alle@Test\"}";
        mockMvc.perform(post("/api/add")
                .contentType(MediaType.APPLICATION_JSON).content(initialUser))
                .andExpect(status().isOk());


        String userUpdateJson = "{\"name\":\"Mohamad\",\"email\":\"Alle@Test\"}";
        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON).content(userUpdateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mohamad"));
    }
@Test
    void testDeleteUserAutomation() throws Exception {
    String result=
    mockMvc.perform(post("/api/add").contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"testD\",\"email\":\"TestD@Test\"}"))
            .andReturn().getResponse().getContentAsString();
    Integer id = com.jayway.jsonpath.JsonPath.read(result,"$.id");
    //mockMvc.perform(get("/api/users/1")).andExpect(status().isOk());
    mockMvc.perform(delete("/api/users/"+id)).andExpect(status().isNoContent());
    //mockMvc.perform(get("/api/users/"+id)).andExpect(status().isNotFound());

}
@Test
    void testAddUserWithInvalidEmail() throws Exception {
    String invalidUserJson = "{\"name\":\"Rayan\",\"email\":\"not-an-email\"}";
    mockMvc.perform(post("/api/add")
            .contentType(MediaType.APPLICATION_JSON).content(invalidUserJson))
            .andExpect(status().isBadRequest());
}
@Test
    void testAddUserWithEmptyName() throws Exception {
    String emptyUserJson = "{\"name\":\"\",\"email\":\"empty@Test\"}";
    mockMvc.perform(post("/api/add")
            .contentType(MediaType.APPLICATION_JSON).content(emptyUserJson))
            .andExpect(status().isBadRequest());
}
}