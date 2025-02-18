package park.haru.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import park.haru.config.TokenExportInterceptor;
import park.haru.user.service.ChangeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class ChangeApiController {

    @Autowired
    private ChangeService changeService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("id_find")
    public ResponseEntity<?> id_find() throws JsonProcessingException {
        int haruMarket_user_index = TokenExportInterceptor.haruMarket_user_index;
        Map<String, Object> reguestData = changeService.id_find(haruMarket_user_index);

        return ResponseEntity.ok().body(reguestData);
    }

    @PostMapping("change1")
    public ResponseEntity<?> change1(
            @RequestBody String reqData
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> list = objectMapper.readValue(reqData, new TypeReference<Map<String, Object>>() {});
        int haruMarket_user_index = TokenExportInterceptor.haruMarket_user_index;
        list.put("haruMarket_user_index",haruMarket_user_index);

        String haruMarket_user_pw = (String) list.get("haruMarket_user_pw");
        String check_haruMarket_user_pw = (String) list.get("check_haruMarket_user_pw");
        if(bCryptPasswordEncoder.matches(check_haruMarket_user_pw,haruMarket_user_pw)){
            changeService.change1(list);

            list.clear();
            list.put("code","200");
            list.put("msg","고객님의 정보가 수정되었습니다.");
            return ResponseEntity.ok().body(list);
        }
        else {
            list.clear();
            list.put("code","400");
            list.put("msg","비밀번호가 서로 일치하지 않습니다.");
            return ResponseEntity.ok().body(list);
        }
    }

    @PostMapping("change2")
    public ResponseEntity<?> change2(
            @RequestBody String reqData
    ) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> list = objectMapper.readValue(reqData, new TypeReference<Map<String, Object>>() {});
        int haruMarket_user_index = TokenExportInterceptor.haruMarket_user_index;
        list.put("haruMarket_user_index",haruMarket_user_index);

        String haruMarket_user_pw = (String) list.get("haruMarket_user_pw");
        String check_haruMarket_user_pw = (String) list.get("check_haruMarket_user_pw");
        String change_haruMarket_user_pw = (String) list.get("change_haruMarket_user_pw");

        if(bCryptPasswordEncoder.matches(check_haruMarket_user_pw,haruMarket_user_pw)){
            list.compute("haruMarket_user_pw", (k, haruMarket_user_pw_change) -> bCryptPasswordEncoder.encode(change_haruMarket_user_pw));
            changeService.change2(list);

            list.clear();
            list.put("code","200");
            list.put("msg","고객님의 비밀번호가 수정되었습니다.");
            return ResponseEntity.ok().body(list);
        }
        else {
            list.clear();
            list.put("code","400");
            list.put("msg","비밀번호가 다릅니다.");
            return ResponseEntity.ok().body(list);
        }
    }

}
