package com.zhuofeng.petsweb.entity;

public class TSavepet {
    private Integer savepetId;

    private String fingdate;

    private Integer postId;

    private Integer typeId;

    private Integer isSaved;

    public Integer getSavepetId() {
        return savepetId;
    }

    public void setSavepetId(Integer savepetId) {
        this.savepetId = savepetId;
    }

    public String getFingdate() {
        return fingdate;
    }

    public void setFingdate(String fingdate) {
        this.fingdate = fingdate;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(Integer isSaved) {
        this.isSaved = isSaved;
    }
}