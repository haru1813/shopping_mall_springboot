package park.haru.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import park.haru.common.dto.join_informationDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UserInformationExport {
    public String getAccessToken() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.iamport.kr/users/getToken"))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"imp_key\":\"0612271828576128\",\"imp_secret\":\"iaCDp6uyoZwaXToAAeCCKODuZhLoSNnVKiHhOdgMSL4ILNhnmxF1PSRxHf8jeeS34a1Yzou5vRuG4sOs\"}"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response.body());
        JsonNode responseNode = rootNode.get("response");
        return responseNode.get("access_token").asText();
    }

    public join_informationDTO getInformation(join_informationDTO joinInformationDTO) throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.iamport.kr/certifications/"+joinInformationDTO.getImp_uid()))
                .header("Content-Type", "application/json")
                .header("Authorization", joinInformationDTO.getAccess_token())
                .method("GET", HttpRequest.BodyPublishers.ofString("{}"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response.body());
        JsonNode responseNode = rootNode.get("response");

        join_informationDTO new_joinInformationDTO = objectMapper.readValue(responseNode.toString(), join_informationDTO.class);
        return new_joinInformationDTO;
    }
}
