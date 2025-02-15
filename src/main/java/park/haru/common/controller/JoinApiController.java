package park.haru.common.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import park.haru.common.dto.join_informationDTO;
import park.haru.common.service.JoinService;
import park.haru.config.UserInformationExport;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.Map;

// 클래스에 커서를 넣고 alt+enter로 테스트
@RequiredArgsConstructor
@RestController
@RequestMapping("/common")
public class JoinApiController {

    @Autowired
    private JoinService joinService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserInformationExport userInformationExport;

    @GetMapping("/join_informationExport/{fdata}")
    public ResponseEntity<?> join_informationExport(
            @PathVariable String fdata
    ) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        join_informationDTO joinInformationDTO = objectMapper.readValue(fdata, join_informationDTO.class);
        joinInformationDTO.setAccess_token(userInformationExport.getAccessToken());

        join_informationDTO new_joinInformationDTO = userInformationExport.getInformation(joinInformationDTO);

        if(joinService.join_check(new_joinInformationDTO.getUnique_key()) != 0){
            return ResponseEntity.badRequest().body("이미 고객님 명의로 가입되셨습니다.");
        }

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .body(new_joinInformationDTO);
    }

//    public String getAccessToken(join_informationDTO joinInformationDTO) throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://api.iamport.kr/users/getToken"))
//                .header("Content-Type", "application/json")
//                .method("POST", HttpRequest.BodyPublishers.ofString("{\"imp_key\":\"0612271828576128\",\"imp_secret\":\"iaCDp6uyoZwaXToAAeCCKODuZhLoSNnVKiHhOdgMSL4ILNhnmxF1PSRxHf8jeeS34a1Yzou5vRuG4sOs\"}"))
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode rootNode = objectMapper.readTree(response.body());
//        JsonNode responseNode = rootNode.get("response");
//        return responseNode.get("access_token").asText();
//    }
//
//    public join_informationDTO getInformation(join_informationDTO joinInformationDTO) throws IOException, InterruptedException{
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://api.iamport.kr/certifications/"+joinInformationDTO.getImp_uid()))
//                .header("Content-Type", "application/json")
//                .header("Authorization", joinInformationDTO.getAccess_token())
//                .method("GET", HttpRequest.BodyPublishers.ofString("{}"))
//                .build();
//
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode rootNode = objectMapper.readTree(response.body());
//        JsonNode responseNode = rootNode.get("response");
//
//        join_informationDTO new_joinInformationDTO = objectMapper.readValue(responseNode.toString(), join_informationDTO.class);
//        return new_joinInformationDTO;
//    }

    @Transactional(rollbackOn = SQLException.class)
    @PostMapping("/join")
    public ResponseEntity<?> join(
            @RequestBody String fdata
    ) throws IOException, SQLException,UnexpectedRollbackException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.readValue(fdata, new TypeReference<Map<String, String>>() {});
        map.compute("haruMarket_user_pw", (k, haruMarket_user_pw) -> bCryptPasswordEncoder.encode(haruMarket_user_pw));
        map.put("haruMarket_user_role","사용자");

        try{
            joinService.join(map) ;

            int haruMarket_user_index = joinService.haruMarket_user_index(map);
            map.clear();
            map.put("haruMarket_user_index", String.valueOf(haruMarket_user_index));
            map.put("harumarket_userToken_ActiveToken","");
            map.put("harumarket_userToken_RefreshToken","");
            joinService.tokenCreate(map);

            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                    .body("회원가입 완료");
        }
        catch (SQLException exception){
            return ResponseEntity.badRequest()
                    .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                    .body("이미 존재하는 아이디입니다. 다른 아이디를 입력하여 주십시오.");
            //throw new SQLException("SQL ERROR");
        }
    }
}
