package park.haru.admin.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductSizeManagerDao {
    @Autowired
    private SqlSession sqlSession;

    public List<Map<String,Object>> productSize_manager_search(Map<String, Object> req){
        return sqlSession.selectList("admin.productSize_manager_search",req);
    }

    public int productSize_manager_insert(Map<String, Object> req){
        return sqlSession.insert("admin.productSize_manager_insert",req);
    }

    public Map<String,Object> productSize_manager_view(int req){
        return sqlSession.selectOne("admin.productSize_manager_view",req);
    }

    public int productSize_manager_update(Map<String, Object> req){
        return sqlSession.insert("admin.productSize_manager_update",req);
    }
}
