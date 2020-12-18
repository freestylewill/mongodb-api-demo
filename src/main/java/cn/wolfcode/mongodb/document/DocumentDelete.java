package cn.wolfcode.mongodb.document;

import cn.wolfcode.mongodb.utils.MongoConstant;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class DocumentDelete {
   /**
    * 删除第一个文档
    * 要删除集合中的第一个文档，首先你需要使用com.mongodb.DBCollection类中的 findOne()方法来获取第一个文档，然后使用remove 方法删除。
    * @param args
    */
   public static void main( String args[] ){
      try{
         // 连接到 mongodb 服务
         MongoClient mongoClient = new MongoClient(MongoConstant.host , MongoConstant.port );
         // 连接到数据库
         MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.databaseName);

         System.out.println("Connect to database successfully");

         MongoCollection<Document> collection = mongoDatabase.getCollection("test");
         System.out.println("集合 test 选择成功");

         //删除符合条件的第一个文档  
         collection.deleteOne(Filters.eq("likes", 200));  
         //删除所有符合条件的文档  
         collection.deleteMany (Filters.eq("likes", 200));  
         //检索查看结果  
         FindIterable<Document> findIterable = collection.find();  
         MongoCursor<Document> mongoCursor = findIterable.iterator();  
         while(mongoCursor.hasNext()){  
           System.out.println(mongoCursor.next());  
         }  
           
      }catch(Exception e){
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
     }
   }
}