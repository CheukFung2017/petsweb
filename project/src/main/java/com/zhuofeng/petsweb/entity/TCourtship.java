package com.zhuofeng.petsweb.entity;

public class TCourtship {
    private Integer courtshipId;

    private Integer sex;

    private String variety;

    private Integer postId;

    private Integer typeId;

    private Integer isMarried;

    public Integer getCourtshipId() {
        return courtshipId;
    }

    public void setCourtshipId(Integer courtshipId) {
        this.courtshipId = courtshipId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
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

    public Integer getIsMarried() {
        return isMarried;
    }

    public void setIsMarried(Integer isMarried) {
        this.isMarried = isMarried;
    }
}