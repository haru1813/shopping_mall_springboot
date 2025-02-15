package park.haru.common.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductSearchDao {
    @Autowired
    private SqlSession sqlSession;

    public List<HashMap<String,String>> category_select(){
        return sqlSession.selectList("common.category_select");
    }

    public int total_page2(Map data){
        return sqlSession.selectOne("common.total_page2",data);
    }

    public List<HashMap<String,String>> page_view2(Map data){
        return sqlSession.selectList("common.page_view2",data);
    }
}
