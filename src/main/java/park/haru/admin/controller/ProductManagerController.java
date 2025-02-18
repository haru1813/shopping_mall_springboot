package park.haru.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import park.haru.admin.service.ProductManagerService;
import park.haru.admin.service.ProductSizeManagerService;
import park.haru.config.TokenExportInterceptor;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class ProductManagerController {

    @Autowired
    private ProductManagerService ProductManagerService;

    @PostMapping("productManager_search")
    public ResponseEntity<?> productManager_search(
            @RequestBody String reqs
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});

        List<Map<String,Object>> res = ProductManagerService.productManager_search(req);

        return ResponseEntity.ok().body(res);
    }

    @PutMapping("productManager_insert")
    public ResponseEntity<?> productManager_insert(
            @RequestBody String reqs
    ) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});
        int haruMarket_user_index = TokenExportInterceptor.haruMarket_user_index;
        req.put("harumarket_product_insertUserIndex",haruMarket_user_index);

        int result = ProductManagerService.productManager_insert(req);

        return ResponseEntity.ok().body(req);
    }

    @PostMapping("productManager_view")
    public ResponseEntity<?> productManager_view(
            @RequestBody String reqs
    ) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});

        int harumarket_product_index = (int) req.get("harumarket_product_index");

        Map<String,Object> result = ProductManagerService.productManager_view(harumarket_product_index);

        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("productManager_update")
    public ResponseEntity<?> productManager_update(
            @RequestBody String reqs
    ) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});
        int haruMarket_user_index = TokenExportInterceptor.haruMarket_user_index;
        req.put("harumarket_product_updateUserIndex",haruMarket_user_index);

        int result = ProductManagerService.productManager_update(req);

        return ResponseEntity.ok().body(req);
    }
}
