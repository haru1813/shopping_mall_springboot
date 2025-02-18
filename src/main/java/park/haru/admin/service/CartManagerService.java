package park.haru.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.user.dao.CartManagerDao;

import java.util.List;
import java.util.Map;

@Service
public class CartManagerService {

    @Autowired
    private CartManagerDao cartManagerDao;

    public List<Map<String,Object>> cart_manager_search(Map<String, Object> req){
        return cartManagerDao.cart_manager_search(req);
    }

    public int category_manager_update(int req){
        return cartManagerDao.cart_manager_delete(req);
    }

}
