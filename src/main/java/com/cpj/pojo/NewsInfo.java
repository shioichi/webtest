package com.cpj.pojo;

import com.cpj.pojo.base.GeneralPojo;

import java.util.Date;

/**
 * Created by chenpengjiang on 2016/4/11.
 */
public class NewsInfo extends GeneralPojo {
    private static final long serialVersionUID = 100000003L;
    private int id;
    private int type;
    private String content;
    private Date creattime;

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
