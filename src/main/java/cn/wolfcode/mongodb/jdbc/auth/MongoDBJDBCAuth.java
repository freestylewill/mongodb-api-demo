package cn.wolfcode.mongodb.jdbc.auth;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

public class MongoDBJDBCAuth {
    public static void main(String[] args) {
        try {
            //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址  
            //ServerAddress()两个参数分别为 服务器地址 和 端口  
            ServerAddress serverAddress = new ServerAddress("localhost", 27017);
            List<ServerAddress> serverAddressList = new ArrayList<ServerAddress>();
            serverAddressList.add(serverAddress);

            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码  
            MongoCredential credential = MongoCredential.createScramSha1Credential("username", "databaseName", "password".toCharArray());
//            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
//            credentials.add(credential);


            MongoClientOptions options = MongoClientOptions.builder()
                    //设置连接超时时间为10s
                    .connectTimeout(1000 * 10)
                    //设置最长等待时间为10s
                    .maxWaitTime(1000 * 10)
                    .build();

            //通过连接认证获取MongoDB连接  
            MongoClient mongoClient = new MongoClient(serverAddressList, credential, options);

            //连接到数据库  
            MongoDatabase mongoDatabase = mongoClient.getDatabase("databaseName");
            System.out.println("Connect to database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
} 