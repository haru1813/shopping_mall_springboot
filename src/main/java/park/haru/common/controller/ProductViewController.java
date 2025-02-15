package park.haru.common.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import park.haru.common.service.ProductViewService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common")
public class ProductViewController {

    @Autowired
    private ProductViewService productViewService;

    @GetMapping("product_view/{harumarket_product_index}")
    public ResponseEntity<?> product_view(
            @PathVariable int harumarket_product_index
    ){
        HashMap<String,Object> data = productViewService.product_view(harumarket_product_index).get(0);
        return ResponseEntity.ok().body(data);
    }

    @PostMapping("option_select")
    public ResponseEntity<?> option_select(
            @RequestBody String fdata
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.readValue(fdata, new TypeReference<Map<String, String>>() {});
        List<HashMap<String,Object>> rdatas = productViewService.harumarket_product_optionSelect(map);

        return ResponseEntity.ok().body(rdatas);
    }
}
