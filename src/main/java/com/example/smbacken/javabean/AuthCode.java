package com.example.smbacken.javabean;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "AuthCode")
public class AuthCode {
    @MongoId
    private ObjectId _id;
    private String phone;
    private String email;
    private String code;
    private String imageCode;
    public AuthCode() {
    }

    public AuthCode(ObjectId _id, String phone, String email, String code, String imageCode) {
        this._id = _id;
        this.phone = phone;
        this.email = email;
        this.code = code;
        this.imageCode = imageCode;
    }

    @Override
    public String toString() {
        return "AuthCode{" +
                "_id=" + _id +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", code='" + code + '\'' +
                ", imageCode='" + imageCode + '\'' +
                '}';
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
