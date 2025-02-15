package park.haru.common.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import park.haru.common.service.ProductListService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 클래스에 커서를 넣고 alt+enter로 테스트
@RequiredArgsConstructor
@RestController
@RequestMapping("/common")
public class ProductListApiController {

    @Autowired
    private ProductListService productListService;

    @GetMapping("/haruMarket_productCategory_name/{haruMarket_productCategory_index}")
    public ResponseEntity<?> haruMarket_productCategory_name(@PathVariable int haruMarket_productCategory_index){
        if(haruMarket_productCategory_index == 0)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("haruMarket_productCategory_name", "전체상품");

            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                    .body(data);
        }
        else {
            Map<String, Object> data = new HashMap<>();
            data.put("haruMarket_productCategory_name", productListService.haruMarket_productCategory_name(haruMarket_productCategory_index));

            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                    .body(data);
        }
    }

    @GetMapping("/total_page/{haruMarket_productCategory_index}")
    public ResponseEntity<?> total_page(@PathVariable int haruMarket_productCategory_index){
        int totalPosts = productListService.total_page(haruMarket_productCategory_index);
        int postsPerPage = 30;
        int totalPages = (int) Math.ceil((double) totalPosts / postsPerPage);

        Map<String, Object> data = new HashMap<>();
        data.put("totalPages", totalPages);

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .body(data);
    }

    @GetMapping("/page_view/{haruMarket_productCategory_index}/{page}")
    public ResponseEntity<?> page_view(
            @PathVariable int haruMarket_productCategory_index,
            @PathVariable int page
    ){
        Map<String, Object> data = new HashMap<>();
        data.put("haruMarket_productCategory_index", haruMarket_productCategory_index);
        data.put("page", page);

        List<HashMap<String, String>> list = productListService.page_view(data);

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .body(list);
    }

}
