package park.haru.common.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LoginDao {
    @Autowired
    private SqlSession sqlSession;

    public List<HashMap<String,Object>> Authentication(String haruMarket_user_id){
        return sqlSession.selectList("common.Authentication",haruMarket_user_id);
    }

    public int Authentication_token(Map data){
        return sqlSession.update("common.Authentication_token",data);
    }

    public List<HashMap<String,Object>> Authorization(int haruMarket_user_index){
        return sqlSession.selectList("common.Authorization",haruMarket_user_index);
    }
}
