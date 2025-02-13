package park.haru.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.common.dao.CommonDao;

import java.util.HashMap;
import java.util.List;

@Service
public class CommonService {

    @Autowired
    private CommonDao commonDao;

    public List<HashMap<String,String>> category(){
        return commonDao.category();
    }

    public List<HashMap<String,String>> advertise(){
        return commonDao.advertise();
    }

    public List<HashMap<String,String>> new_products(){
        return commonDao.new_products();
    }
}
