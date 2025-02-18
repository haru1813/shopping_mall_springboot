package park.haru.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import park.haru.config.TokenExportInterceptor;
import park.haru.user.service.OrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class OrderApiController {

    @Autowired
    private OrderService orderService;

    @PostMapping("order_select")
    public ResponseEntity<?> order_select() throws JsonProcessingException {
        int haruMarket_user_index = TokenExportInterceptor.haruMarket_user_index;
        List<HashMap<String,String>> res = orderService.order_select(haruMarket_user_index);

        return ResponseEntity.ok().body(res);
    }
}
