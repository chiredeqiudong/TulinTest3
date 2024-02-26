package com.it.pojo;

import java.sql.Date;

/**
 * @author zy293
 * id:主键
 * title:标题
 * content:内容
 * writer:创作者
 * createTime:创建时间
 */
public class Announcement {
    private Integer id;
    private String title;
    private String content;
    private String writer;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
