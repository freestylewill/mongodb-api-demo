package cn.wolfcode.mongodb.collection;

import cn.wolfcode.mongodb.utils.MongoConstant;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * 创建集合
 * 我们可以使用 com.mongodb.client.MongoDatabase 类中的createCollection()来创建集合
 */
public class MongoDBJDBCCollection {
   public static void main( String args[] ){
      try{   
      // 连接到 mongodb 服务
      MongoClient mongoClient = new MongoClient(MongoConstant.host , MongoConstant.port );
      // 连接到数据库
      MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.databaseName);
      System.out.println("Connect to database successfully");
      mongoDatabase.createCollection("test");
      System.out.println("集合创建成功");
        
      }catch(Exception e){
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
     }
   }

   /**
    * 获取集合
    * 我们可以使用com.mongodb.client.MongoDatabase类的 getCollection() 方法来获取一个集合
    */
   public static void getCollection(){
      try{
         // 连接到 mongodb 服务
         MongoClient mongoClient = new MongoClient(MongoConstant.host , MongoConstant.port );
         // 连接到数据库
         MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.databaseName);

         System.out.println("Connect to database successfully");

         MongoCollection<Document> collection = mongoDatabase.getCollection("test");
         System.out.println("集合 test 选择成功");
      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
   }
}