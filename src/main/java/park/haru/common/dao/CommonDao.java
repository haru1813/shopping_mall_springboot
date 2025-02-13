package park.haru.common.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class CommonDao {
    @Autowired
    private SqlSession sqlSession;

    public List<HashMap<String,String>> category(){
        return sqlSession.selectList("common.category");
    }

    public List<HashMap<String,String>> advertise(){
        return sqlSession.selectList("common.advertise");
    }

    public List<HashMap<String,String>> new_products(){
        return sqlSession.selectList("common.new_products");
    }
}
