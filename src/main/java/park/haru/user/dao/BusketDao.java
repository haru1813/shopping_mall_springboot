package park.haru.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BusketDao {
    @Autowired
    private SqlSession sqlSession;

    public int harumarket_userbasket_insert(List<Map<String, Object>> data){
        return sqlSession.insert("user.harumarket_userbasket_insert",data);
    }

    public int harumarket_userbasket_count(int haruMarket_user_index){
        return sqlSession.selectOne("user.harumarket_userbasket_count",haruMarket_user_index);
    }

    public int harumarket_userbasket_DistinctCount(Map<String, Object> data){
        return sqlSession.selectOne("user.harumarket_userbasket_DistinctCount",data);
    }

    public Map<String, Object> harumarket_userbasket_DistinctCountDetail(Map<String, Object> data){
        return sqlSession.selectOne("user.harumarket_userbasket_DistinctCountDetail",data);
    }

    public int harumarket_userbasket_insert2(Map<String, Object> data){
        return sqlSession.insert("user.harumarket_userbasket_insert2",data);
    }

    public int harumarket_userbasket_update(Map<String, Object> data){
        return sqlSession.insert("user.harumarket_userbasket_update",data);
    }
}
