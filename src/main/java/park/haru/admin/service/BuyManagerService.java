package park.haru.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.admin.dao.BuyManagerDao;

import java.util.List;
import java.util.Map;

@Service
public class BuyManagerService {
    @Autowired
    private BuyManagerDao buyManagerDao;

    public List<Map<String,Object>> buy_manager_search1(Map<String, Object> req){
        return buyManagerDao.buy_manager_search1(req);
    }

    public List<Map<String,Object>> buy_manager_search2(String req){
        return buyManagerDao.buy_manager_search2(req);
    }

    public int buy_manager_update(Map<String, Object> req){
        return buyManagerDao.buy_manager_update(req);
    }
}
