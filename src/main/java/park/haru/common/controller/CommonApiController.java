package park.haru.common.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import park.haru.common.service.CommonService;
import park.haru.config.jwt.TokenProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/common")
public class CommonApiController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private TokenProvider tokenProvider;

    @GetMapping("/")
    public String index(){
        return "안녕";
    }

    @GetMapping("/category")
    public ResponseEntity<?> category(){
        List<HashMap<String, String>> list = commonService.category();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .body(list);
    }

    @GetMapping("/advertise")
    public ResponseEntity<?> advertise(){
        List<HashMap<String, String>> list = commonService.advertise();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .body(list);
    }

    @GetMapping("/new_products")
    public ResponseEntity<?> new_products(){
        List<HashMap<String, String>> list = commonService.new_products();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .body(list);
    }

    @PostMapping("/token_test")
    public ResponseEntity<?> token_test(
            @RequestBody String fdata
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.readValue(fdata, new TypeReference<Map<String, String>>() {});

        String token = map.get("token").replaceFirst("Bearer ", "");
        map.clear();
        map.put("haruMarket_user_index",tokenProvider.getHaruMarket_user_index(token));

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .body(map);
    };
}
