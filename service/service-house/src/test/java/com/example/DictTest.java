package com.example;

import com.example.dao.DictDao;
import com.example.entity.Dict;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = "classpath:spring/spring-dao.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DictTest {

    @Autowired
    private DictDao dictDao;

    @Test
    public void test(){
        List<Dict> listByParentId = dictDao.findListByParentId(1l);
        System.out.println("listByParentId = " + listByParentId);
    }
}
