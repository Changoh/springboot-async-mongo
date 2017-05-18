package com.example.demo.config;

import com.mongodb.ConnectionString;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClientSettings;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoDatabase;
import com.mongodb.connection.ClusterSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {
    @Bean
    public MongoDatabase mongoClient(){
        ConnectionString connectionString = new ConnectionString("mongodb://127.0.0.1:27017");
        MongoClient client;
        client = MongoClients.create(MongoClientSettings.builder()
                .clusterSettings(ClusterSettings.builder().applyConnectionString(connectionString).build()).build());
        return client.getDatabase("demo");
    }
}
