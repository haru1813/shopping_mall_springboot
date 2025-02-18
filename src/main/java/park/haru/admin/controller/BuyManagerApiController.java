package park.haru.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import park.haru.admin.service.BuyManagerService;
import park.haru.config.TokenExportInterceptor;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class BuyManagerApiController {
    @Autowired
    private BuyManagerService buyManagerService;

    @PostMapping("buy_manager_search1")
    public ResponseEntity<?> buy_manager_search1(
            @RequestBody String reqs
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});

        List<Map<String,Object>> res = buyManagerService.buy_manager_search1(req);

        return ResponseEntity.ok().body(res);
    }

    @PostMapping("buy_manager_search2")
    public ResponseEntity<?> buy_manager_search2(
            @RequestBody String reqs
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});
        String haruMarket_BuyMaster_order = (String) req.get("haruMarket_BuyMaster_order");

        List<Map<String,Object>> res = buyManagerService.buy_manager_search2(haruMarket_BuyMaster_order);

        return ResponseEntity.ok().body(res);
    }

    @PatchMapping("buy_manager_update")
    public ResponseEntity<?> buy_manager_update(
            @RequestBody String reqs
    ) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});

        int result = buyManagerService.buy_manager_update(req);

        return ResponseEntity.ok().body(result);
    }

}
