package arkpas.culinaryblog.integrationTests.controllerIntegrationTests;

import arkpas.culinaryblog.domain.Cattegory;
import arkpas.culinaryblog.service.CattegoryService;
import arkpas.culinaryblog.service.RecipeCattegoryService;
import arkpas.culinaryblog.utils.CattegoryType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CattegoryControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeCattegoryService recipeCattegoryService;

    @MockBean
    private CattegoryService cattegoryService;

    @Test
    public void controllerShouldReturnPageWithSearchResults () throws Exception{
        doReturn(new ArrayList<>()).when(recipeCattegoryService).getRecipesFromCattegory(anyString());
        mockMvc.perform(get("/kategoria/nazwaKategorii")).andExpect(view().name("searchResults"));
    }

    @Test
    @WithMockUser(authorities = "blogger")
    public void controllerShouldReturnPageWithCattegoryForm () throws Exception{
        mockMvc.perform(get("/kategoria/dodaj")).andExpect(view().name("cattegoryForm"));
    }

    @Test
    @WithMockUser(authorities = "blogger")
    public void controllerShouldReturnPageWithCattegoryModificationPage () throws Exception{

        mockMvc.perform(get("/kategoria/modyfikuj")).andExpect(view().name("cattegoryModification"));
    }

    @Test
    @WithMockUser(authorities = "blogger")
    public void controllerShouldReturnCattegoryEditionForm () throws Exception{
        Cattegory cattegory = new Cattegory();
        cattegory.setCattegoryType(CattegoryType.MEAL);
        doReturn(cattegory).when(cattegoryService).getCattegory(anyInt());
        mockMvc.perform(get("/kategoria/modyfikuj/edytuj/1")).andExpect(view().name("cattegoryEditionForm"));
    }

    @Test
    @WithMockUser(authorities = "blogger")
    public void controllerShouldCallDeleteMethod () throws Exception{
        doNothing().when(cattegoryService).deleteCattegory(any());
        mockMvc.perform(get("/kategoria/modyfikuj/usun/1")).andExpect(status().is3xxRedirection());
        verify(cattegoryService, times(1)).deleteCattegory(any());

    }
}
