package park.haru.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.user.dao.BuyDao;

import java.util.Map;

@Service
public class BuyService {
    @Autowired
    private BuyDao buyDao;

    public Map<String, Object> information_find(int haruMarket_user_index){
        return buyDao.information_find(haruMarket_user_index);
    }

    public Map<String, Object> product_information_view(Map<String, Object> data){
        return buyDao.product_information_view(data);
    }

    public int product_buy_try1(Map<String, Object> data){
        return buyDao.product_buy_try1(data);
    }

    public String product_buy_try2(int haruMarket_user_index){
        return buyDao.product_buy_try2(haruMarket_user_index);
    }

    public int product_buy_master(Map<String, Object> data){
        return buyDao.product_buy_master(data);
    }

    public int product_buy_detail(Map<String, Object> data){
        return buyDao.product_buy_detail(data);
    }

    public int harumarket_product_salePrice(int harumarket_product_index){
        return buyDao.harumarket_product_salePrice(harumarket_product_index);
    }

    public int userBasket_delete(int harumarket_userBasket_index){
        return buyDao.userBasket_delete(harumarket_userBasket_index);
    }
}
