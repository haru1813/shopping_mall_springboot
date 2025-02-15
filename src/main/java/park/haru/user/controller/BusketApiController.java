package park.haru.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import park.haru.config.TokenExportInterceptor;
import park.haru.user.service.BusketService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class BusketApiController {

    @Autowired
    private BusketService busketService;

    @Transactional(rollbackOn = SQLException.class)
    @PostMapping("/busket_insert")
    public ResponseEntity<?> busket_insert(
            @RequestBody String busket_insert
    ) throws JsonProcessingException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> list = objectMapper.readValue(busket_insert, new TypeReference<List<Map<String, Object>>>() {});
        int haruMarket_user_index = TokenExportInterceptor.haruMarket_user_index;

        for (Map<String, Object> item : list){
            item.put("haruMarket_user_index",haruMarket_user_index);

            int distinct = busketService.harumarket_userbasket_DistinctCount(item);

            if(distinct==0){
                busketService.harumarket_userbasket_insert2(item);
            }
            else {
                Map<String, Object> sData = busketService.harumarket_userbasket_DistinctCountDetail(item);
                int harumarket_userBasket_account = (int) sData.get("harumarket_userBasket_account");
                String harumarket_userBasket_account_add_string = item.get("harumarket_userBasket_account").toString();
                int harumarket_userBasket_account_add = Integer.parseInt(harumarket_userBasket_account_add_string);
                int harumarket_userBasket_account_total = harumarket_userBasket_account + harumarket_userBasket_account_add;
                int harumarket_userBasket_index = (int) sData.get("harumarket_userBasket_index");
                item.put("harumarket_userBasket_index",harumarket_userBasket_index);
                item.put("harumarket_userBasket_account",harumarket_userBasket_account_total);
                busketService.harumarket_userbasket_update(item);
            }
        }

        //busketService.harumarket_userbasket_insert(list);

        if(busketService.harumarket_userbasket_count(haruMarket_user_index) > 10){
            throw new SQLException("장바구니는 10개까지만 등록 가능합니다.");
        }

        Map<String, String> res = new HashMap<>();
        res.put("msg","장바구니를 등록하였습니다.");
        return ResponseEntity.ok().body(res);
    };

}
