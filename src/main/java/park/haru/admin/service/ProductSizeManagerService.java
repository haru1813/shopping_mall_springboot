package park.haru.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.admin.dao.ProductColorManagerDao;
import park.haru.admin.dao.ProductSizeManagerDao;

import java.util.List;
import java.util.Map;

@Service
public class ProductSizeManagerService {
    @Autowired
    private ProductSizeManagerDao productSizeManagerDao;

    public List<Map<String,Object>> productSize_manager_search(Map<String, Object> req){
        return productSizeManagerDao.productSize_manager_search(req);
    }

    public int productSize_manager_insert(Map<String, Object> req){
        return productSizeManagerDao.productSize_manager_insert(req);
    }

    public Map<String,Object> productSize_manager_view(int req){
        return productSizeManagerDao.productSize_manager_view(req);
    }

    public int productSize_manager_update(Map<String, Object> req){
        return productSizeManagerDao.productSize_manager_update(req);
    }
}
