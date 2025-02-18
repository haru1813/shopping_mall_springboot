package park.haru.admin.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BuyManagerDao {
    @Autowired
    private SqlSession sqlSession;

    public List<Map<String,Object>> buy_manager_search1(Map<String, Object> req){
        return sqlSession.selectList("admin.buy_manager_search1",req);
    }

    public List<Map<String,Object>> buy_manager_search2(String req){
        return sqlSession.selectList("admin.buy_manager_search2",req);
    }

    public int buy_manager_update(Map<String, Object> req){
        return sqlSession.update("admin.buy_manager_update",req);
    }
}
