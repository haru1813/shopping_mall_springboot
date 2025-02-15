package park.haru.common.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TokenDAO {
    @Autowired
    private SqlSession sqlSession;

    public List<HashMap<String,Object>> tokenSelect(String harumarket_userToken_ActiveToken){
        return sqlSession.selectList("common.tokenSelect",harumarket_userToken_ActiveToken);
    }

    public int tokenUpdate(Map data){
        return sqlSession.update("common.tokenUpdate",data);
    }
}
