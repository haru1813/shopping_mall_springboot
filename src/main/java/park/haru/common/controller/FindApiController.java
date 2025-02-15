package park.haru.common.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import park.haru.common.dto.join_informationDTO;
import park.haru.common.service.FindService;
import park.haru.config.UserInformationExport;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/common")
public class FindApiController {

    @Autowired
    private UserInformationExport userInformationExport;

    @Autowired
    private FindService findService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/id_find")
    public ResponseEntity<?> id_find(
            @RequestBody String fdata
    ) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        join_informationDTO joinInformationDTO = objectMapper.readValue(fdata, join_informationDTO.class);
        joinInformationDTO.setAccess_token(userInformationExport.getAccessToken());
        join_informationDTO new_joinInformationDTO = userInformationExport.getInformation(joinInformationDTO);

        Map<String,String> map = new HashMap<>();
        map.put("haruMarket_user_uniqueKey",new_joinInformationDTO.getUnique_key());
        map.put("haruMarket_user_id","");

        List<HashMap<String,Object>> datas = findService.findUser(map);

        if(datas.size() == 1){
            Map<String,Object> resData = datas.get(0);
            return ResponseEntity.ok().body(resData);
        }
        else {
            map.clear();
            return ResponseEntity.badRequest().body(map);
        }
    }

    @PostMapping("/pw_find")
    public ResponseEntity<?> pw_find(
            @RequestBody String fdata
    ) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        join_informationDTO joinInformationDTO = objectMapper.readValue(fdata, join_informationDTO.class);
        joinInformationDTO.setAccess_token(userInformationExport.getAccessToken());
        join_informationDTO new_joinInformationDTO = userInformationExport.getInformation(joinInformationDTO);

        Map<String,String> map = new HashMap<>();
        map.put("haruMarket_user_uniqueKey",new_joinInformationDTO.getUnique_key());
        map.put("haruMarket_user_id",joinInformationDTO.getHaruMarket_user_id());

        List<HashMap<String,Object>> datas = findService.findUser(map);

        if(datas.size() == 1){
            String impl_pw = generateRandomPassword(8);

            Map<String,Object> updateData = datas.get(0);
            String update_pw = bCryptPasswordEncoder.encode(impl_pw);
            updateData.compute("haruMarket_user_pw", (k, haruMarket_user_pw) -> update_pw);
            findService.updatePw(updateData);

            Map<String,Object> resData = datas.get(0);
            resData.compute("haruMarket_user_pw", (k, haruMarket_user_pw) -> impl_pw);
            return ResponseEntity.ok().body(resData);
        }
        else {
            map.clear();
            return ResponseEntity.badRequest().body(map);
        }
    }

    public String generateRandomPassword(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be a positive integer.");
        }

        String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int charactersLength = characters.length();
        StringBuilder randomString = new StringBuilder(length);

        // SecureRandom을 사용하여 예측 불가능한 난수 생성 (보안 권장)
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charactersLength); // 0 ~ charactersLength-1 범위의 난수 생성
            randomString.append(characters.charAt(randomIndex));
        }

        return randomString.toString();
    }
}
