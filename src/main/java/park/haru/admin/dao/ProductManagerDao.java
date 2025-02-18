package park.haru.admin.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductManagerDao {
    @Autowired
    private SqlSession sqlSession;

    public List<Map<String,Object>> productManager_search(Map<String, Object> req){
        return sqlSession.selectList("admin.productManager_search",req);
    }

    public int productManager_insert(Map<String, Object> req){
        return sqlSession.insert("admin.productManager_insert",req);
    }

    public Map<String,Object> productManager_view(int req){
        return sqlSession.selectOne("admin.productManager_view",req);
    }

    public int productManager_update(Map<String, Object> req){
        return sqlSession.insert("admin.productManager_update",req);
    }
}
