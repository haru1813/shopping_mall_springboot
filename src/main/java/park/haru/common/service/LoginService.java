package park.haru.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.common.dao.LoginDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;

    public List<HashMap<String,Object>> Authentication(String haruMarket_user_id){
        return loginDao.Authentication(haruMarket_user_id);
    }

    public int Authentication_token(Map data){
        return loginDao.Authentication_token(data);
    }

    public List<HashMap<String,Object>> Authorization(int haruMarket_user_index){
        return loginDao.Authorization(haruMarket_user_index);
    }
}
