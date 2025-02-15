package park.haru.common.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import park.haru.common.dto.ProductSearchDto;
import park.haru.common.service.ProductSearchService;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/common")
public class ProductSearchApiController {

    @Autowired
    private ProductSearchService productSearchService;

    @GetMapping("/category_select")
    public ResponseEntity<?> category_select(){
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .body(productSearchService.category_select());
    }

    @GetMapping("/total_page2/{fdata}")
    public ResponseEntity<?> total_page2(
            @PathVariable String fdata
            ) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ProductSearchDto productSearchDto = objectMapper.readValue(fdata, ProductSearchDto.class);

        Map<String, Object> data = new HashMap<>();
        data.put("haruMarket_productCategory_index", productSearchDto.getHaruMarket_productCategory_index());
        data.put("harumarket_product_name", productSearchDto.getHarumarket_product_name());

        int totalPosts = productSearchService.total_page2(data);
        int postsPerPage = 30;
        int totalPages = (int) Math.ceil((double) totalPosts / postsPerPage);
        data.clear();
        data.put("totalPages", totalPages);

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .body(data);
    }

    @GetMapping("/page_view2/{fdata}")
    public ResponseEntity<?> page_view2(
            @PathVariable String fdata
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductSearchDto productSearchDto = objectMapper.readValue(fdata, ProductSearchDto.class);

        Map<String, Object> data = new HashMap<>();
        data.put("haruMarket_productCategory_index", productSearchDto.getHaruMarket_productCategory_index());
        data.put("harumarket_product_name", productSearchDto.getHarumarket_product_name());
        data.put("page", productSearchDto.getPage());

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .body(productSearchService.page_view2(data));
    }
}
