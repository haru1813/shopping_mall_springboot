package park.haru.user.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.user.dao.BusketDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BusketService {
    @Autowired
    private BusketDao busketDao;

    @Transactional(rollbackOn = SQLException.class)
    public int harumarket_userbasket_insert(List<Map<String, Object>> data){
        return busketDao.harumarket_userbasket_insert(data);
    }

    public int harumarket_userbasket_count(int haruMarket_user_index){
        return busketDao.harumarket_userbasket_count(haruMarket_user_index);
    }

    public int harumarket_userbasket_DistinctCount(Map<String, Object> data){
        return busketDao.harumarket_userbasket_DistinctCount(data);
    }

    public Map<String, Object> harumarket_userbasket_DistinctCountDetail(Map<String, Object> data){
        return busketDao.harumarket_userbasket_DistinctCountDetail(data);
    }

    @Transactional(rollbackOn = SQLException.class)
    public int harumarket_userbasket_insert2(Map<String, Object> data){
        return busketDao.harumarket_userbasket_insert2(data);
    }

    @Transactional(rollbackOn = SQLException.class)
    public int harumarket_userbasket_update(Map<String, Object> data){
        return busketDao.harumarket_userbasket_update(data);
    }

    public List<HashMap<String,String>> basket_select(int haruMarket_user_index){
        return busketDao.basket_select(haruMarket_user_index);
    }

    public int basket_delete(Map<String, Object> data){
        return busketDao.basket_delete(data);
    }
}
