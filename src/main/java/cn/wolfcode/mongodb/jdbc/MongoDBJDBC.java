package cn.wolfcode.mongodb.jdbc;

import cn.wolfcode.mongodb.utils.MongoConstant;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBJDBC{

   public static void main( String args[] ){
      try{
          // 连接到 mongodb 服务
          MongoClient mongoClient = new MongoClient(MongoConstant.host , MongoConstant.port );
          // 连接到数据库
          MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.databaseName);
       System.out.println("Connect to database successfully");
        
      }catch(Exception e){
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
     }
   }
}