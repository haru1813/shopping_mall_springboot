package park.haru.common.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import park.haru.common.service.TokenService;
import park.haru.config.auth.User;
import park.haru.config.jwt.JwtProperties;
import park.haru.config.jwt.TokenProvider;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common")
public class TokenApiController {

    String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("token_refresh")
    public ResponseEntity<?> token_refresh(HttpServletRequest request) {
        Map<String,String> data = new HashMap();

        String header = request.getHeader(jwtProperties.getHeader());
        if (header == null || !header.startsWith(TOKEN_PREFIX)){
            return ResponseEntity.badRequest().body(data);
        }

        List<HashMap<String,Object>> tokens = tokenService.tokenSelect(header);
        String harumarket_userToken_RefreshToken = tokens.get(0).get("harumarket_userToken_RefreshToken").toString().replace(TOKEN_PREFIX, "");

        if(tokenProvider.validToken(harumarket_userToken_RefreshToken)){
            User user = User.builder()
                    .haruMarket_user_index((Integer) tokens.get(0).get("haruMarket_user_index"))
                    .build();

            Map<String, String> map = new HashMap<>();
            map.put("harumarket_userToken_index",tokens.get(0).get("harumarket_userToken_index").toString());
            map.put("harumarket_userToken_ActiveToken",tokens.get(0).get("harumarket_userToken_RefreshToken").toString());
            map.put("harumarket_userToken_RefreshToken",TOKEN_PREFIX+tokenProvider.generateToken(user,Duration.ofMinutes(jwtProperties.getRefresh())));
            tokenService.tokenUpdate(map);

            data.put("token",tokens.get(0).get("harumarket_userToken_RefreshToken").toString());
            return ResponseEntity.ok().body(data);

//            return ResponseEntity.ok()
//                    .header("Access-Control-Expose-Headers", jwtProperties.getHeader())
//                    .header(jwtProperties.getHeader(),TOKEN_PREFIX+harumarket_userToken_RefreshToken).build();
        }

        return ResponseEntity.badRequest().body(data);
    }

}
