package park.haru.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private SqlSession sqlSession;

    @GetMapping("/")
    public String test1(){
        String test = sqlSession.selectOne("test.testSelect");
        return test;
    }
}
