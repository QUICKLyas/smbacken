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
    private String email;
    private String code;

    public AuthCode() {
    }

    public AuthCode(ObjectId _id, String email, String code) {
        this._id = _id;
        this.email = email;
        this.code = code;
    }

    @Override
    public String toString() {
        return "AuthCode{" +
                "_id=" + _id +
                ", email='" + email + '\'' +
                ", code='" + code + '\'' +
                '}';
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
