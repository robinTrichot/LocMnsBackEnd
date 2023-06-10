package com.projetLocMns.ProjetFilRougeLocMnsV3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ProjetFilRougeLocMnsV3ApplicationTests {


    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;



    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }




    @Test
    @WithMockUser(roles = {"USER"})
    void userConnected_OkAttendu() throws Exception {
        mvc.perform(get("/user/copies")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void validateAnHireByAnUser_403Attendu() throws Exception {
        mvc.perform(get("/admin/validate/hire")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void validateAnHireByAnAdmin_JSONAttendu() throws Exception {
        mvc.perform(post("/admin/validate/hire").contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void addUsagerByAnAdmin_JSONAttendu() throws Exception {
        mvc.perform(post("/admin/addUsager").contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void addUsagerByAnUser_403Attendu() throws Exception {
        mvc.perform(post("/admin/addUsager")).andExpect(status().isForbidden());
    }

    @Test
    void addUsagerByAnyone_403Attendu() throws Exception {
        mvc.perform(post("/admin/addUsager")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void deleteUsagerByAnUser_403Attendu() throws Exception {
        mvc.perform(delete("/admin/deleteUsager/1")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void deleteUsagerByAnAdmin_OkAttendu() throws Exception {
        mvc.perform(delete("/admin/deleteUsager/4")).andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = {"USER"})
    void deleteACopyByAnUser_403Attendu() throws Exception {
        mvc.perform(delete("/admin/deleteCopy/4")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void deleteACopyByAnAdmin_OkAttendu() throws Exception {
        mvc.perform(delete("/admin/deleteCopy/3")).andExpect(status().isOk());
    }

}
