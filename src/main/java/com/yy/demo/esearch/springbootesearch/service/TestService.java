package com.yy.demo.esearch.springbootesearch.service;

import com.yy.demo.esearch.springbootesearch.entity.Entity;

import java.util.List;

/**
 * @author yy
 * @Description: TODO
 * @date 2019/9/9 10:14
 */
public interface TestService {

    void saveEntity(Entity entity);

    void saveEntity(List<Entity> entityList);

    List<Entity> searchEntity(String searchContent);

}
