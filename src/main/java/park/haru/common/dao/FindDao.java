package park.haru.common.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FindDao {
    @Autowired
    private SqlSession sqlSession;

    public List<HashMap<String,Object>> findUser(Map data){
        return sqlSession.selectList("common.findUser",data);
    }

    public int updatePw(Map data){
        return sqlSession.update("common.updatePw",data);
    }
}
