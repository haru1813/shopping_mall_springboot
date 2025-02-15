package park.haru.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.common.dao.TokenDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TokenService {
    @Autowired
    private TokenDAO tokenDAO;

    public List<HashMap<String,Object>> tokenSelect(String harumarket_userToken_ActiveToken){
        return tokenDAO.tokenSelect(harumarket_userToken_ActiveToken);
    }

    public int tokenUpdate(Map data){
        return tokenDAO.tokenUpdate(data);
    }
}
