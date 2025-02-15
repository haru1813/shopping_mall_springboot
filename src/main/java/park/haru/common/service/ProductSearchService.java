package park.haru.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.common.dao.ProductSearchDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductSearchService {
    @Autowired
    private ProductSearchDao productSearchDao;

    public List<HashMap<String,String>> category_select(){
        return productSearchDao.category_select();
    }

    public int total_page2(Map data){
        return productSearchDao.total_page2(data);
    }

    public List<HashMap<String,String>> page_view2(Map data){
        return productSearchDao.page_view2(data);
    }
}
