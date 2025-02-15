package park.haru.common.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductListDao {
    @Autowired
    private SqlSession sqlSession;

    public String haruMarket_productCategory_name(int haruMarket_productCategory_index){
        return sqlSession.selectOne("common.haruMarket_productCategory_name",haruMarket_productCategory_index);
    }

    public int total_page(int haruMarket_productCategory_index){
        return sqlSession.selectOne("common.total_page",haruMarket_productCategory_index);
    }

    public List<HashMap<String,String>> page_view(Map data){
        return sqlSession.selectList("common.page_view",data);
    }
}
