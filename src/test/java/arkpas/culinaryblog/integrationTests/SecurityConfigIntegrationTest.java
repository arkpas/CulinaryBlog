package arkpas.culinaryblog.integrationTests;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    public void anonymousUserShouldHavePermissionToEnterSite () throws Exception {
        mockMvc.perform(get("/")).andExpect(view().name("landing")).andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithAnonymousUser
    public void anonymousUserShouldNotHavePermissionToAddRecipes () throws Exception {
        mockMvc.perform(get("/przepis/dodaj")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithAnonymousUser
    public void anonymousUserShouldNotHavePermissionToAddCattegories () throws Exception {
        mockMvc.perform(get("/kategoria/dodaj")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithAnonymousUser
    public void anonymousUserShouldNotHavePermissionToModifyRecipes () throws Exception {
        mockMvc.perform(get("/przepis/modyfikuj/edytuj/1")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithAnonymousUser
    public void anonymousUserShouldNotHavePermissionToModifyCattegories () throws Exception {
        mockMvc.perform(get("/kategoria/modyfikuj")).andExpect(status().is3xxRedirection());
    }


    @Test
    @WithMockUser (username = "user", password = "pass", authorities = "user")
    public void loggedUserShouldHavePermissionToEnterSite () throws Exception {
        mockMvc.perform(get("/")).andExpect(view().name("landing")).andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser (username = "user", password = "pass", authorities = "user")
    public void loggedUserShouldNotHavePermissionToAddRecipes () throws Exception {
        mockMvc.perform(get("/przepis/dodaj")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser (username = "user", password = "pass", authorities = "user")
    public void loggedUserShouldNotHavePermissionToAddCattegories () throws Exception {
        mockMvc.perform(get("/kategoria/dodaj")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser (username = "user", password = "pass", authorities = "user")
    public void loggedUserShouldNotHavePermissionToModifyRecipes () throws Exception {
        mockMvc.perform(get("/przepis/modyfikuj/edytuj/1")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser (username = "user", password = "pass", authorities = "user")
    public void loggedUserShouldNotHavePermissionToModifyCattegories () throws Exception {
        mockMvc.perform(get("/kategoria/modyfikuj")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = "blogger")
    public void bloggerShouldHavePermissionToAddRecipes () throws Exception {
        mockMvc.perform(get("/przepis/dodaj")).andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(authorities = "blogger")
    public void bloggerShouldHavePermissionToAddCattegories () throws Exception {
        mockMvc.perform(get("/kategoria/dodaj")).andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(authorities = "blogger")
    public void bloggerShouldHavePermissionToModifyRecipes () throws Exception {
        mockMvc.perform(get("/przepis/modyfikuj/edytuj/1")).andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(authorities = "blogger")
    public void bloggerShouldHavePermissionToModifyCattegories () throws Exception {
        mockMvc.perform(get("/kategoria/modyfikuj")).andExpect(status().is2xxSuccessful());
    }

}
