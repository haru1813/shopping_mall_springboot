package park.haru.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import park.haru.user.dao.CartManagerDao;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class CartManagerApiController {

    @Autowired
    private CartManagerDao cartManagerDao;

    @PostMapping("cart_manager_search")
    public ResponseEntity<?> category_manager_search(
            @RequestBody String reqs
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});

        List<Map<String,Object>> res = cartManagerDao.cart_manager_search(req);

        return ResponseEntity.ok().body(res);
    }

    @PostMapping("cart_manager_delete")
    public ResponseEntity<?> cart_manager_delete(
            @RequestBody String reqs
    ) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> req = objectMapper.readValue(reqs, new TypeReference<Map<String, Object>>() {});

        int harumarket_userBasket_index = (int) req.get("harumarket_userBasket_index");

        int result = cartManagerDao.cart_manager_delete(harumarket_userBasket_index);

        return ResponseEntity.ok().body(result);
    }

}
