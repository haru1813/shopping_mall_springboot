package park.haru.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.common.dao.ProductListDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductListService {

    @Autowired
    private ProductListDao productListDao;

    public String haruMarket_productCategory_name(int haruMarket_productCategory_index){
        return productListDao.haruMarket_productCategory_name(haruMarket_productCategory_index);
    }

    public int total_page(int haruMarket_productCategory_index){
        return productListDao.total_page(haruMarket_productCategory_index);
    }

    public List<HashMap<String,String>> page_view(Map data){
        return productListDao.page_view(data);
    }

}
