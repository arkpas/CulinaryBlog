package arkpas.culinaryblog.integrationTests.controllerIntegrationTests;

import arkpas.culinaryblog.domain.Recipe;
import arkpas.culinaryblog.domain.User;
import arkpas.culinaryblog.service.RateService;
import arkpas.culinaryblog.service.RecipeService;
import arkpas.culinaryblog.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;
    @MockBean
    private UserService userService;
    @MockBean
    private RateService rateService;

    @Test
    public void controllerShouldRedirectBecauseNoRecipeWasFound () throws Exception{
        doReturn(null).when(recipeService).getRecipe(anyString());
        mockMvc.perform(get("/przepis/kompot"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void controllerShouldReturnRecipePage () throws Exception {
        doReturn(new Recipe()).when(recipeService).getRecipe(anyString());
        doReturn(null).when(userService).getCurrentUser();
        mockMvc.perform(get("/przepis/dowolny"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipePage"));
    }

    @Test
    @WithMockUser(authorities = "blogger")
    public void controllerShouldReturnRecipeForm () throws Exception {
        mockMvc.perform(get("/przepis/dodaj"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipeForm"));
    }

    @Test
    @WithMockUser(authorities = "blogger")
    public void controllerShouldReturnRecipeEditionForm () throws Exception {
        doReturn(new Recipe()).when(recipeService).getRecipe(anyInt());
        mockMvc.perform(get("/przepis/modyfikuj/edytuj/11"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipeEditionForm"));
    }

    @Test
    @WithMockUser(authorities = "blogger")
    public void controllerShouldRedirectToMainPageBecauseNoRecipeWasFoundToDelete () throws Exception {
        doReturn(new Recipe()).when(recipeService).getRecipe(anyInt());
        mockMvc.perform(get("/przepis/modyfikuj/usun/145"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }



}
