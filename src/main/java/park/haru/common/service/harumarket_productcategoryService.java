package park.haru.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import park.haru.common.model.harumarket_productcategory;
import park.haru.common.repository.harumarket_productcategoryRepository;

import java.util.List;

@Service
public class harumarket_productcategoryService {
    @Autowired
    harumarket_productcategoryRepository harumarket_productcategoryRepository;

    public List<harumarket_productcategory> findAll(){
        return harumarket_productcategoryRepository.findAll();
    }
}
