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
import park.haru.common.dto.ProductSearchDto;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductSearchApiControllerTest {
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

    @DisplayName("category_select : 테스트")
    @Test
    void category_select() throws Exception {
        final String url = "/common/category_select";

        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        final MvcResult mvcResult = resultActions.andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);

        resultActions
                .andExpect(status().isOk());
    }

    @DisplayName("total_page2 : 테스트")
    @Test
    void total_page2() throws Exception {
        final ProductSearchDto productSearchDto = ProductSearchDto.builder()
                .haruMarket_productCategory_index(1)
                .harumarket_product_name("")
                .build();

        //final String requestBody = objectMapper.writeValueAsString(productSearchDto);

        final String url = "/common/total_page2";

        final ResultActions resultActions = mockMvc.perform(get(url)
                .flashAttr("productSearchDto",productSearchDto)
                .accept(MediaType.APPLICATION_JSON));

        final MvcResult mvcResult = resultActions.andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);

        resultActions
                .andExpect(status().isOk());
    }

    @DisplayName("page_view2 : 테스트")
    @Test
    void page_view2() throws Exception {
        final ProductSearchDto productSearchDto = ProductSearchDto.builder()
                .haruMarket_productCategory_index(1)
                .harumarket_product_name("")
                .page(2)
                .build();

        final String url = "/common/page_view2";

        final ResultActions resultActions = mockMvc.perform(get(url)
                .flashAttr("productSearchDto",productSearchDto)
                .accept(MediaType.APPLICATION_JSON));

        final MvcResult mvcResult = resultActions.andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);

        resultActions
                .andExpect(status().isOk());
    }
}