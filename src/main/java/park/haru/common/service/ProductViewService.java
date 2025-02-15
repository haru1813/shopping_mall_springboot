package park.haru.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.common.dao.ProductViewDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductViewService {
    @Autowired
    private ProductViewDao productViewDao;

    public List<HashMap<String,Object>> product_view(int harumarket_product_index){
        return productViewDao.product_view(harumarket_product_index);
    }

    public List<HashMap<String,Object>> harumarket_product_optionSelect(Map<String, String> data){
        return productViewDao.harumarket_product_optionSelect(data);
    }
}
