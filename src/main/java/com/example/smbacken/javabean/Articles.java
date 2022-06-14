package com.example.smbacken.javabean;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "articles")
public class Articles {
    @MongoId
    private ObjectId _id;
    private String coverImage;
    private String title;
    private String content;
    private String tags;
    private String author;
    private String avatarUrl;
    private Double date;
    private String intro;
    private int views;
    private int like;
    private int comments;
    private String authorId;

    public Articles() {
    }

    public Articles(ObjectId _id, String coverImage, String title, String content,
                    String tags, String author, String avatarUrl, Double date, String intro,
                    int views, int like, int comments, String authorId) {
        this._id = _id;
        this.coverImage = coverImage;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.author = author;
        this.avatarUrl = avatarUrl;
        this.date = date;
        this.intro = intro;
        this.views = views;
        this.like = like;
        this.comments = comments;
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "_id=" + _id +
                ", coverImage='" + coverImage + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", tags='" + tags + '\'' +
                ", author='" + author + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", date=" + date +
                ", intro='" + intro + '\'' +
                ", view=" + views +
                ", like=" + like +
                ", comments=" + comments +
                ", authorId='" + authorId + '\'' +
                '}';
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
