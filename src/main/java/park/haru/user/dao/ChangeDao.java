package park.haru.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ChangeDao {
    @Autowired
    private SqlSession sqlSession;

    public Map<String, Object> id_find(int haruMarket_user_index){
        return sqlSession.selectOne("user.id_find",haruMarket_user_index);
    }

    public int change1(Map<String, Object> data){
        return sqlSession.update("user.change1",data);
    }

    public int change2(Map<String, Object> data){
        return sqlSession.update("user.change2",data);
    }
}
