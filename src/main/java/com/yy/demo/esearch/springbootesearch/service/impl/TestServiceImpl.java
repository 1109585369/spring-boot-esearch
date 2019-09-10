package com.yy.demo.esearch.springbootesearch.service.impl;

import com.yy.demo.esearch.springbootesearch.entity.Entity;
import com.yy.demo.esearch.springbootesearch.service.TestService;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author yy
 * @Description: TODO
 * @date 2019/9/9 10:16
 */
@Service
public class TestServiceImpl implements TestService {

    private static final Logger log = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private JestClient jestClient;

    @Override
    public void saveEntity(Entity entity) {
        Index index = new Index.Builder(entity).index(Entity.INDEX_NAME).type(Entity.TYPE).build();
        try{
            jestClient.execute(index);
            log.info("es插入完成");
        }catch (IOException e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Override
    public void saveEntity(List<Entity> entityList) {
        Bulk.Builder bulk = new Bulk.Builder();
        for (Entity entity : entityList) {
            Index index = new Index.Builder(entity).index(Entity.INDEX_NAME).type(Entity.TYPE).build();
            bulk.addAction(index);
        }
        try {
            jestClient.execute(bulk.build());
            log.info("es插入完成'");
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Entity> searchEntity(String searchContent) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("name",searchContent));
        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(Entity.INDEX_NAME).addType(Entity.TYPE).build();
        try {
            JestResult result =  jestClient.execute(search);
            return result.getSourceAsObjectList(Entity.class);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }
}
