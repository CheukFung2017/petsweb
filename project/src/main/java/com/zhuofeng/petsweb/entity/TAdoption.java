package com.zhuofeng.petsweb.entity;

public class TAdoption {
    private Integer adoptId;

    private String petName;

    private String age;

    private Integer sex;

    private Integer sterilized;

    private Integer immune;

    private Integer postId;

    private Integer typeId;

    private Integer isAdopted;

    private TPost post;

    public Integer getAdoptId() {
        return adoptId;
    }

    public void setAdoptId(Integer adoptId) {
        this.adoptId = adoptId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSterilized() {
        return sterilized;
    }

    public void setSterilized(Integer sterilized) {
        this.sterilized = sterilized;
    }

    public Integer getImmune() {
        return immune;
    }

    public void setImmune(Integer immune) {
        this.immune = immune;
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

    public Integer getIsAdopted() {
        return isAdopted;
    }

    public void setIsAdopted(Integer isAdopted) {
        this.isAdopted = isAdopted;
    }

    public TPost getPost(){
        return post;
    }

    public void setPost(TPost post){
        this.post=post;
    }
}