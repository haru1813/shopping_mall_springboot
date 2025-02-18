package park.haru.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.user.dao.OrderDao;

import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    public List<HashMap<String,String>> order_select(int haruMarket_user_index){
        return orderDao.order_select(haruMarket_user_index);
    }
}
