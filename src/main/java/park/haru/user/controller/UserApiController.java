package park.haru.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import park.haru.config.TokenExportInterceptor;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserApiController {

    @PostMapping("move2")
    public ResponseEntity<?> move2(){
        Map data = new HashMap();
        data.put("move2","ok");

        System.out.println(TokenExportInterceptor.haruMarket_user_index);

        return ResponseEntity.ok().body(data);
    }
}
