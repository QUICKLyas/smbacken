package com.example.smbacken.javabean;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Data
@Document(collection = "ArticleBriefInfo")
public class ArticleBriefInfo {
    @Id
    private ObjectId id;
    private String author;
    private String date;
    private String title;
    private String intro;
    private String[] tags;
    private String coverImage;
    private int views;
    private int like;
    private int comments;

    public ArticleBriefInfo() {
    }

    public ArticleBriefInfo(ObjectId id, String author, String date,
                            String title, String intro, String[] tags,
                            String coverImage, int views, int like,
                            int comments) {
        this.id = id;
        this.author = author;
        this.date = date;
        this.title = title;
        this.intro = intro;
        this.tags = tags;
        this.coverImage = coverImage;
        this.views = views;
        this.like = like;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "ArticleBriefInfo{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", intro='" + intro + '\'' +
                ", tags=" + Arrays.toString(tags) +
                ", coverImage='" + coverImage + '\'' +
                ", views=" + views +
                ", like=" + like +
                ", comments=" + comments +
                '}';
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(String id) {
        this.id = new ObjectId(id);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
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
}