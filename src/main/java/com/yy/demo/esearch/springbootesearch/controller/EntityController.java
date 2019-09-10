package com.yy.demo.esearch.springbootesearch.controller;

import com.yy.demo.esearch.springbootesearch.entity.Entity;
import com.yy.demo.esearch.springbootesearch.service.TestService;
import io.searchbox.strings.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yy
 * @Description: TODO
 * @date 2019/9/9 10:51
 */
@RestController
@RequestMapping("/entityController")
public class EntityController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public  String save(long id ,String name){
        System.out.println("save 接口");
        if(id > 0 && StringUtils.isNotBlank(name)){
            Entity newEntity = new Entity(id,name);
            List<Entity> addList = new ArrayList<>();
            addList.add(newEntity);
            testService.saveEntity(addList);
            return "OK";
        }else {
            return "Bad input value";
        }
    }

    @RequestMapping(value = "search",method = RequestMethod.GET)
    public List<Entity> save(String name){
        List<Entity> entityList = null;
        if(StringUtils.isNotBlank(name)){
            entityList = testService.searchEntity(name);
        }
        return entityList;
    }
}
