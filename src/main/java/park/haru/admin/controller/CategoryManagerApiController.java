package park.haru.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import park.haru.admin.service.CategoryManagerService;
import park.haru.config.TokenExportInterceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/admin")
public class CategoryManagerApiController {

    @Autowired
    private CategoryManagerService categoryManagerService;

    @PostMapping("category_manager_search")
    public ResponseEntity<?> category_manager_search(
            @RequestBody String reqs
    ) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});

        List<Map<String,Object>> res = categoryManagerService.category_manager_search(req);

        return ResponseEntity.ok().body(res);
    }

    @PutMapping("category_manager_insert")
    public ResponseEntity<?> category_manager_insert(
            @RequestBody String reqs
    ) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});
        int haruMarket_user_index = TokenExportInterceptor.haruMarket_user_index;
        req.put("haruMarket_productCategory_insertUserIndex",haruMarket_user_index);

        int result = categoryManagerService.category_manager_insert(req);

        return ResponseEntity.ok().body(req);
    }

    @PostMapping("category_manager_view")
    public ResponseEntity<?> category_manager_view(
            @RequestBody String reqs
    ) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});

        int haruMarket_productCategory_index = (int) req.get("haruMarket_productCategory_index");

        Map<String,Object> result = categoryManagerService.category_manager_view(haruMarket_productCategory_index);

        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("category_manager_update")
    public ResponseEntity<?> category_manager_update(
            @RequestBody String reqs
    ) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});
        int haruMarket_user_index = TokenExportInterceptor.haruMarket_user_index;
        req.put("haruMarket_productCategory_updateUserIndex",haruMarket_user_index);

        int result = categoryManagerService.category_manager_update(req);

        return ResponseEntity.ok().body(req);
    }
}
