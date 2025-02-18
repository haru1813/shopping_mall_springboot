package park.haru.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.admin.dao.CategoryManagerDao;
import park.haru.admin.dao.UserManagerDao;

import java.util.List;
import java.util.Map;

@Service
public class UserManagerService {
    @Autowired
    private UserManagerDao userManagerDao;

    public List<Map<String,Object>> user_manager_search(Map<String, Object> req){
        return userManagerDao.user_manager_search(req);
    }

    public Map<String,Object> user_manager_view(int req){
        return userManagerDao.user_manager_view(req);
    }

    public int user_manager_update(Map<String, Object> req){
        return userManagerDao.user_manager_update(req);
    }
}
