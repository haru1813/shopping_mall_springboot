package park.haru.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.common.dao.FindDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FindService {
    @Autowired
    private FindDao findDao;

    public List<HashMap<String,Object>> findUser(Map data){
        return findDao.findUser(data);
    }

    public int updatePw(Map data){
        return findDao.updatePw(data);
    }
}
