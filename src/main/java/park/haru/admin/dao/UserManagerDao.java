package park.haru.admin.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserManagerDao {
    @Autowired
    private SqlSession sqlSession;

    public List<Map<String,Object>> user_manager_search(Map<String, Object> req){
        return sqlSession.selectList("admin.user_manager_search",req);
    }

    public Map<String,Object> user_manager_view(int req){
        return sqlSession.selectOne("admin.user_manager_view",req);
    }

    public int user_manager_update(Map<String, Object> req){
        return sqlSession.insert("admin.user_manager_update",req);
    }
}
