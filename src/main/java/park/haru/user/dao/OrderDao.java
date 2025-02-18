package park.haru.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class OrderDao {
    @Autowired
    private SqlSession sqlSession;

    public List<HashMap<String,String>> order_select(int haruMarket_user_index){
        return sqlSession.selectList("user.order_select",haruMarket_user_index);
    }
}
