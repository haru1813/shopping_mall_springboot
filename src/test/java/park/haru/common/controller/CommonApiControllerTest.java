package park.haru.common.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CommonApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @DisplayName("category: 카테고리 조회")
    @Test
    public void category() throws Exception{
        final String url = "/common/category";

        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        final MvcResult mvcResult = resultActions.andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);

        resultActions
                .andExpect(status().isOk());
    }

    @DisplayName("advertise: 카테고리 조회")
    @Test
    public void advertise() throws Exception{
        final String url = "/common/advertise";

        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        final MvcResult mvcResult = resultActions.andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);

        resultActions
                .andExpect(status().isOk());
    }

    @DisplayName("new_products: 카테고리 조회")
    @Test
    public void new_products() throws Exception{
        final String url = "/common/new_products";

        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        final MvcResult mvcResult = resultActions.andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);

        resultActions
                .andExpect(status().isOk());
    }
}