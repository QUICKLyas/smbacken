package com.example.smbacken.javabean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "list")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticlesList {
    @MongoId
    private String _id;
    private String coverImage;
    private String title;
    private String id;
    private String avatarUrl;
    private Double date;
    private String intro;
    private int views;
    private int like;
    private int comments;
    private String tags;
    private String authorId;

    public ArticlesList() {
    }

    public ArticlesList(String _id, String coverImage, String title,
                        String id, String avatarUrl, Double date, String intro,
                        int views, int like, int comments, String tags, String authorId) {
        this._id = _id;
        this.coverImage = coverImage;
        this.title = title;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.date = date;
        this.intro = intro;
        this.views = views;
        this.like = like;
        this.comments = comments;
        this.tags = tags;
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "ArticlesList{" +
                "_id='" + _id + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", date=" + date +
                ", intro='" + intro + '\'' +
                ", views=" + views +
                ", like=" + like +
                ", comments=" + comments +
                ", tags='" + tags + '\'' +
                ", authorId='" + authorId + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Double getDate() {
        return date;
    }

    public void setDate(Double date) {
        this.date = date;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}