package com.zhuofeng.petsweb.entity;

public class TFindpet {
    private Integer findpetId;

    private String petName;

    private String losedate;

    private Integer postId;

    private Integer typeId;

    private Integer isFound;

    public Integer getFindpetId() {
        return findpetId;
    }

    public void setFindpetId(Integer findpetId) {
        this.findpetId = findpetId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getLosedate() {
        return losedate;
    }

    public void setLosedate(String losedate) {
        this.losedate = losedate;
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

    public Integer getIsFound() {
        return isFound;
    }

    public void setIsFound(Integer isFound) {
        this.isFound = isFound;
    }
}