package park.haru.common.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.common.dao.JoinDAO;

import java.sql.SQLException;
import java.util.Map;

@Service
public class JoinService {
    @Autowired
    private JoinDAO joinDAO;

    public int join_check(String haruMarket_user_uniqueKey){
        return joinDAO.join_check(haruMarket_user_uniqueKey);
    }

    @Transactional(rollbackOn = SQLException.class)
    public int join(Map data) throws SQLException{
        return joinDAO.join(data);
    }

    public int haruMarket_user_index(Map data){
        return joinDAO.haruMarket_user_index(data);
    }

    @Transactional(rollbackOn = SQLException.class)
    public int tokenCreate(Map data) throws SQLException{
        return joinDAO.tokenCreate(data);
    }
}
