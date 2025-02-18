package park.haru.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CartManagerDao {
    @Autowired
    private SqlSession sqlSession;

    public List<Map<String,Object>> cart_manager_search(Map<String, Object> req){
        return sqlSession.selectList("admin.cart_manager_search",req);
    }

    public int cart_manager_delete(int req){
        return sqlSession.delete("admin.cart_manager_delete",req);
    }
}
