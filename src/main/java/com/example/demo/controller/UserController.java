package com.example.demo.controller;

import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by changoh on 17. 5. 18.
 */
@RestController
public class UserController {

    @Autowired
    MongoDatabase database;

    @RequestMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        MongoCollection<Document> collection = database.getCollection("test");
        Document doc = new Document("username", user.getUserName())
                .append("email", user.getEmail())
                .append("fullname", user.getFullname())
                .append("location", user.getLocaltion())
                .append("age", user.getAge())
                .append("gender", user.getGender());

        collection.insertOne(doc, new SingleResultCallback<Void>() {
            @Override
            public void onResult(final Void result, final Throwable t) {
                System.out.println("Inserted!");
            }
        });
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }
}
