package park.haru.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import park.haru.admin.service.ProductColorManagerService;
import park.haru.admin.service.ProductSizeManagerService;
import park.haru.config.TokenExportInterceptor;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class ProductSizeManagerController {

    @Autowired
    private ProductSizeManagerService productSizeManagerService;

    @PostMapping("productSize_manager_search")
    public ResponseEntity<?> productSize_manager_search(
            @RequestBody String reqs
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});

        List<Map<String,Object>> res = productSizeManagerService.productSize_manager_search(req);

        return ResponseEntity.ok().body(res);
    }

    @PutMapping("productSize_manager_insert")
    public ResponseEntity<?> productSize_manager_insert(
            @RequestBody String reqs
    ) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});
        int haruMarket_user_index = TokenExportInterceptor.haruMarket_user_index;
        req.put("harumarket_productSize_insertUserIndex",haruMarket_user_index);

        int result = productSizeManagerService.productSize_manager_insert(req);

        return ResponseEntity.ok().body(req);
    }

    @PostMapping("productSize_manager_view")
    public ResponseEntity<?> productSize_manager_view(
            @RequestBody String reqs
    ) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});

        int harumarket_productSize_index = (int) req.get("harumarket_productSize_index");

        Map<String,Object> result = productSizeManagerService.productSize_manager_view(harumarket_productSize_index);

        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("productSize_manager_update")
    public ResponseEntity<?> productSize_manager_update(
            @RequestBody String reqs
    ) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});
        int haruMarket_user_index = TokenExportInterceptor.haruMarket_user_index;
        req.put("harumarket_productSize_updateUserIndex",haruMarket_user_index);

        int result = productSizeManagerService.productSize_manager_update(req);

        return ResponseEntity.ok().body(req);
    }
}
