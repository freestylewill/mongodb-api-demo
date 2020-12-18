package cn.wolfcode.mongodb.document;

import cn.wolfcode.mongodb.utils.MongoConstant;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DocumentFind {
    /**
     * 检索所有文档
     * 我们可以使用 com.mongodb.client.MongoCollection 类中的 find() 方法来获取集合中的所有文档。
     *
     * 此方法返回一个游标，所以你需要遍历这个游标。
     */
    public static void main(String args[]) {
        try {
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient(MongoConstant.host , MongoConstant.port );
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.databaseName);

            System.out.println("Connect to database successfully");

            MongoCollection<Document> collection = mongoDatabase.getCollection("test");
            System.out.println("集合 test 选择成功");

            //检索所有文档
            /**
             * 1. 获取迭代器FindIterable<Document>
             * 2. 获取游标MongoCursor<Document>
             * 3. 通过游标遍历检索出的文档集合
             * */
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                System.out.println(mongoCursor.next());
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

}