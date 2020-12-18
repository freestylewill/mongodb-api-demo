package cn.wolfcode.mongodb.jdbc;

import com.mongodb.client.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBURL {

    public static void main(String[] args) {
//        创建MongoDB的链接客户端,用户名为：psymongo，密码为：123456，数据库为：psytest,集合为：spit
        MongoClient mongoClient =MongoClients.create("mongodb://root:root@192.168.35.128:27017/?authSource=test");
//        获取对应的数据库
        MongoDatabase psytest = mongoClient.getDatabase("test");
//        获取对应的文档集合
        MongoCollection<Document> spit = psytest.getCollection("data");
//        查询该集合中的所有文档
        FindIterable<Document> documents = spit.find();
//        遍历文档数据，打印出nickname的值
        for (Document document : documents) {
            System.out.println(document);
        }
    }
}