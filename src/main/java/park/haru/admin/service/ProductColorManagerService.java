package park.haru.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import park.haru.admin.dao.ProductColorManagerDao;

import java.util.List;
import java.util.Map;

@Service
public class ProductColorManagerService {
    @Autowired
    private ProductColorManagerDao productColorManagerDao;

    public List<Map<String,Object>> productColor_manager_search(Map<String, Object> req){
        return productColorManagerDao.productColor_manager_search(req);
    }

    public int productColor_manager_insert(Map<String, Object> req){
        return productColorManagerDao.productColor_manager_insert(req);
    }

    public Map<String,Object> productColor_manager_view(int req){
        return productColorManagerDao.productColor_manager_view(req);
    }

    public int productColor_manager_update(Map<String, Object> req){
        return productColorManagerDao.productColor_manager_update(req);
    }
}
