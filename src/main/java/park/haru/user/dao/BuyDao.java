package park.haru.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class BuyDao {
    @Autowired
    private SqlSession sqlSession;

    public Map<String, Object> information_find(int haruMarket_user_index){
        return sqlSession.selectOne("user.information_find",haruMarket_user_index);
    }

    public Map<String, Object> product_information_view(Map<String, Object> data){
        return sqlSession.selectOne("user.product_information_view",data);
    }

    public int product_buy_try1(Map<String, Object> data){
        return sqlSession.selectOne("user.product_buy_try1",data);
    }

    public String product_buy_try2(int haruMarket_user_index){
        return sqlSession.selectOne("user.product_buy_try2",haruMarket_user_index);
    }

    public int product_buy_master(Map<String, Object> data){
        return sqlSession.insert("user.product_buy_master",data);
    }

    public int product_buy_detail(Map<String, Object> data){
        return sqlSession.insert("user.product_buy_detail",data);
    }

    public int harumarket_product_salePrice(int harumarket_product_index){
        return sqlSession.selectOne("user.harumarket_product_salePrice",harumarket_product_index);
    }

    public int userBasket_delete(int harumarket_userBasket_index){
        return sqlSession.insert("user.userBasket_delete",harumarket_userBasket_index);
    }
}
