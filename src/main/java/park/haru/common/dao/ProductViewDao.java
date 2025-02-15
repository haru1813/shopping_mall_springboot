package park.haru.common.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductViewDao {
    @Autowired
    private SqlSession sqlSession;

    public List<HashMap<String,Object>> product_view(int harumarket_product_index){
        return sqlSession.selectList("common.product_view",harumarket_product_index);
    }

    public List<HashMap<String,Object>> harumarket_product_optionSelect(Map<String, String> data){
        return sqlSession.selectList("common.harumarket_product_optionSelect",data);
    }
}
