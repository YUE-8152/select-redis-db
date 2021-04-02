package com.select.redis.db.bean;

import java.io.Serializable;

/**
 * @ProjectName: select-redis-db
 * @Package: com.select.redis.db.bean
 * @ClassName: UserPo
 * @Author: YUE
 * @Description:
 * @Date: 2021/4/2 9:22
 * @Version: 1.0
 */
public class UserPo implements Serializable {
    private static final long serialVersionUID = -8109354421929092678L;

    private Integer id;

    private String name;

    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserPo(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public UserPo() {
    }
}
