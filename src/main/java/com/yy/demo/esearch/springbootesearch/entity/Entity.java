package com.yy.demo.esearch.springbootesearch.entity;

import java.io.Serializable;

/**
 * @author yy
 * @Description: TODO
 * @date 2019/9/9 10:09
 */
public class Entity implements Serializable {

    private static final long serialVersionUID = -8663511927139158130L;

    public static final String  INDEX_NAME="index_entity";

    public static final String TYPE="tstype";

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Entity(){
        super();
    }

    public Entity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
