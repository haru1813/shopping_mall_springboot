package park.haru.common.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import park.haru.common.service.CommonService;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/common")
public class CommonApiController {

    @Autowired
    private CommonService commonService;

    @GetMapping("/category")
    public ResponseEntity<?> category(){
        List<HashMap<String, String>> list = commonService.category();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .body(list);
    }

    @GetMapping("/advertise")
    public ResponseEntity<?> advertise(){
        List<HashMap<String, String>> list = commonService.advertise();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .body(list);
    }

    @GetMapping("/new_products")
    public ResponseEntity<?> new_products(){
        List<HashMap<String, String>> list = commonService.new_products();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .body(list);
    }
}
