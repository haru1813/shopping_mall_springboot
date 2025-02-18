package park.haru.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.user.dao.ChangeDao;

import java.util.Map;

@Service
public class ChangeService {

    @Autowired
    private ChangeDao changeDao;

    public Map<String, Object> id_find(int haruMarket_user_index){
        return changeDao.id_find(haruMarket_user_index);
    }

    public int change1(Map<String, Object> data){
        return changeDao.change1(data);
    }

    public int change2(Map<String, Object> data){
        return changeDao.change2(data);
    }
}
