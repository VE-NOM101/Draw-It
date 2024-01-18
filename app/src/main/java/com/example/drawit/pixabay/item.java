package com.example.drawit.pixabay;

public class item {

    private String imageUrl,tags,pageUrl;
    private int likes;

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public item(String imageUrl, String tags, int likes, String pageUrl) {
        this.imageUrl = imageUrl;
        this.tags = tags;
        this.likes = likes;
        this.pageUrl=pageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
