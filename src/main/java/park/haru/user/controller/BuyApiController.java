package park.haru.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import park.haru.config.TokenExportInterceptor;
import park.haru.user.service.BuyService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class BuyApiController {
    @Autowired
    private BuyService buyService;

    @PostMapping("information_find")
    public ResponseEntity<?> information_find() throws JsonProcessingException {
        int haruMarket_user_index = TokenExportInterceptor.haruMarket_user_index;
        Map<String, Object> res = buyService.information_find(haruMarket_user_index);

        return ResponseEntity.ok().body(res);
    }

    @PostMapping("product_information_view")
    public ResponseEntity<?> product_information_view(
            @RequestBody String harumarket_userBuy
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> list = objectMapper.readValue(harumarket_userBuy, new TypeReference<List<Map<String, Object>>>() {});
        List<Map<String, Object>> res = new ArrayList<>();

        for(Map<String,Object> data : list){
            res.add(buyService.product_information_view(data));
        }

        return ResponseEntity.ok().body(res);
    }

    @PostMapping("product_buy_try")
    public ResponseEntity<?> product_buy_try(
            @RequestBody String harumarket_userBuy
    ) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> list = objectMapper.readValue(harumarket_userBuy, new TypeReference<List<Map<String, Object>>>() {});

        int harumarket_product_salePrice = 0;

        for(Map<String,Object> data : list){
            harumarket_product_salePrice += buyService.product_buy_try1(data);
        }

        int haruMarket_user_index = TokenExportInterceptor.haruMarket_user_index;
        String haruMarket_user_phone = buyService.product_buy_try2(haruMarket_user_index);

        Map<String, Object> res = new HashMap<>();
        res.put("harumarket_product_salePrice",harumarket_product_salePrice);
        res.put("haruMarket_user_phone",haruMarket_user_phone);

        return ResponseEntity.ok().body(res);
    }

    @PostMapping("product_buy_master")
    public ResponseEntity<?> product_buy_master(
            @RequestBody String req
    ) throws JsonProcessingException{
        int haruMarket_user_index = TokenExportInterceptor.haruMarket_user_index;

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> list = objectMapper.readValue(req, new TypeReference<Map<String, Object>>() {});
        list.put("haruMarket_user_index",haruMarket_user_index);
        list.put("haruMarket_BuyMaster_status","결제완료");

        buyService.product_buy_master(list);

        Map<String, Object> res = new HashMap<>();
        res.put("msg","OK");
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("product_buy_detail")
    public ResponseEntity<?> product_buy_detail(
            @RequestBody String req
    ) throws JsonProcessingException{
        int haruMarket_user_index = TokenExportInterceptor.haruMarket_user_index;

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> list = objectMapper.readValue(req, new TypeReference<List<Map<String, Object>>>() {});

        for (Map<String, Object> map : list){
            map.put("haruMarket_user_index",haruMarket_user_index);
            //String harumarket_product_counts = (String) map.get("harumarket_product_count");
            int harumarket_product_count = (int) map.get("harumarket_product_count");
            //String harumarket_product_indexs = (String) map.get("harumarket_product_indexs");
            int harumarket_product_index = (int) map.get("harumarket_product_index");
            int harumarket_product_salePrice = buyService.harumarket_product_salePrice(harumarket_product_index);
            int harumarket_buyDetail_amount = harumarket_product_count * harumarket_product_salePrice;
            map.put("harumarket_buyDetail_amount",harumarket_buyDetail_amount);

            buyService.product_buy_detail(map);
        }

        Map<String, Object> res = new HashMap<>();
        res.put("msg","OK");
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("userBasket_delete")
    public ResponseEntity<?> userBasket_delete(
            @RequestBody String req
    ) throws JsonProcessingException{
        int haruMarket_user_index = TokenExportInterceptor.haruMarket_user_index;

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> list = objectMapper.readValue(req, new TypeReference<List<Map<String, Object>>>() {});

        for (Map<String, Object> map : list){
            int harumarket_userBasket_index = (int) map.get("harumarket_userBasket_index");
            buyService.userBasket_delete(harumarket_userBasket_index);
        }

        Map<String, Object> res = new HashMap<>();
        res.put("msg","OK");
        return ResponseEntity.ok().body(res);
    }
}
