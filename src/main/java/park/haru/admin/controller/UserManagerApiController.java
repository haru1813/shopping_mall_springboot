package park.haru.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import park.haru.admin.service.CategoryManagerService;
import park.haru.admin.service.UserManagerService;
import park.haru.config.TokenExportInterceptor;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class UserManagerApiController {

    @Autowired
    private UserManagerService userManagerService;

    @PostMapping("user_manager_search")
    public ResponseEntity<?> user_manager_search(
            @RequestBody String reqs
    ) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});

        List<Map<String,Object>> res = userManagerService.user_manager_search(req);

        return ResponseEntity.ok().body(res);
    }


    @PostMapping("user_manager_view")
    public ResponseEntity<?> user_manager_view(
            @RequestBody String reqs
    ) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});

        int haruMarket_user_index = (int) req.get("haruMarket_user_index");

        Map<String,Object> result = userManagerService.user_manager_view(haruMarket_user_index);

        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("user_manager_update")
    public ResponseEntity<?> user_manager_update(
            @RequestBody String reqs
    ) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});
        int haruMarket_user_index = TokenExportInterceptor.haruMarket_user_index;
        req.put("haruMarket_user_updateUserIndex",haruMarket_user_index);

        int result = userManagerService.user_manager_update(req);

        return ResponseEntity.ok().body(req);
    }
}
