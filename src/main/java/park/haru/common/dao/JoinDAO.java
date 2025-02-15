package park.haru.common.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class JoinDAO {
    @Autowired
    private SqlSession sqlSession;

    public int join_check(String haruMarket_user_uniqueKey){
        return sqlSession.selectOne("common.join_check",haruMarket_user_uniqueKey);
    }

    public int join(Map data){
        return sqlSession.insert("common.join",data);
    }

    public int haruMarket_user_index(Map data){
        return sqlSession.selectOne("common.haruMarket_user_index",data);
    }

    public int tokenCreate(Map data){
        return sqlSession.insert("common.tokenCreate",data);
    }
}
