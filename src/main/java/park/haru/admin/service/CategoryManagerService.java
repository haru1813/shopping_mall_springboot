package park.haru.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.admin.dao.CategoryManagerDao;

import java.util.List;
import java.util.Map;

@Service
public class CategoryManagerService {
    @Autowired
    private CategoryManagerDao categoryManagerDao;

    public List<Map<String,Object>> category_manager_search(Map<String, Object> req){
        return categoryManagerDao.category_manager_search(req);
    }

    public int category_manager_insert(Map<String, Object> req){
        return categoryManagerDao.category_manager_insert(req);
    }

    public Map<String,Object> category_manager_view(int req){
        return categoryManagerDao.category_manager_view(req);
    }

    public int category_manager_update(Map<String, Object> req){
        return categoryManagerDao.category_manager_update(req);
    }
}
