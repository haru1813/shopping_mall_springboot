package park.haru.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import park.haru.config.TokenExportInterceptor;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminApiController {

    @PostMapping("move")
    public ResponseEntity<?> move(){
        Map data = new HashMap();
        data.put("move2","ok");

        System.out.println(TokenExportInterceptor.haruMarket_user_index);

        return ResponseEntity.ok().body(data);
    }
}
