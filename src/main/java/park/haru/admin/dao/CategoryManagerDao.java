package park.haru.admin.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CategoryManagerDao {
    @Autowired
    private SqlSession sqlSession;

    public List<Map<String,Object>> category_manager_search(Map<String, Object> req){
        return sqlSession.selectList("admin.category_manager_search",req);
    }

    public int category_manager_insert(Map<String, Object> req){
        return sqlSession.insert("admin.category_manager_insert",req);
    }

    public Map<String,Object> category_manager_view(int req){
        return sqlSession.selectOne("admin.category_manager_view",req);
    }

    public int category_manager_update(Map<String, Object> req){
        return sqlSession.insert("admin.category_manager_update",req);
    }
}
