package park.haru.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.admin.dao.ProductManagerDao;
import park.haru.admin.dao.ProductSizeManagerDao;

import java.util.List;
import java.util.Map;

@Service
public class ProductManagerService {
    @Autowired
    private ProductManagerDao productManagerDao;

    public List<Map<String,Object>> productManager_search(Map<String, Object> req){
        return productManagerDao.productManager_search(req);
    }

    public int productManager_insert(Map<String, Object> req){
        return productManagerDao.productManager_insert(req);
    }

    public Map<String,Object> productManager_view(int req){
        return productManagerDao.productManager_view(req);
    }

    public int productManager_update(Map<String, Object> req){
        return productManagerDao.productManager_update(req);
    }
}
