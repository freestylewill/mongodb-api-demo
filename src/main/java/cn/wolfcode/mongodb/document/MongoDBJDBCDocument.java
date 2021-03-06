package cn.wolfcode.mongodb.document;

import cn.wolfcode.mongodb.utils.MongoConstant;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBJDBCDocument {
    /**
     * 插入文档
     * 我们可以使用com.mongodb.client.MongoCollection类的 insertMany() 方法来插入一个文档
     *
     * @param args
     */
    public static void main(String args[]) {
        try {
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient(MongoConstant.host, MongoConstant.port);
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.databaseName);

            System.out.println("Connect to database successfully");

            MongoCollection<Document> collection = mongoDatabase.getCollection("test");
            System.out.println("集合 test 选择成功");
            //插入文档
            /**
             * 1. 创建文档 org.bson.Document 参数为key-value的格式
             * 2. 创建文档集合List<Document>
             * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document)
             * */
            Document document = new Document("title", "MongoDB").
                    append("description", "database").
                    append("likes", 100).
                    append("by", "Fly");
            List<Document> documents = new ArrayList<Document>();
            documents.add(document);
            collection.insertMany(documents);
            System.out.println("文档插入成功");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }


    /**
     * 检索所有文档
     * 我们可以使用 com.mongodb.client.MongoCollection 类中的 find() 方法来获取集合中的所有文档。
     * <p>
     * 此方法返回一个游标，所以你需要遍历这个游标。
     */
    public static void find() {
        try {
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient(MongoConstant.host, MongoConstant.port);
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

    /**
     * 更新文档
     * 你可以使用 com.mongodb.client.MongoCollection 类中的 updateMany() 方法来更新集合中的文档。
     */
    public static void updateMany() {
        try {
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient(MongoConstant.host, MongoConstant.port);
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.databaseName);

            System.out.println("Connect to database successfully");

            MongoCollection<Document> collection = mongoDatabase.getCollection("test");
            System.out.println("集合 test 选择成功");

            //更新文档   将文档中likes=100的文档修改为likes=200
            collection.updateMany(Filters.eq("likes", 100), new Document("$set", new Document("likes", 200)));
            //检索查看结果
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                System.out.println(mongoCursor.next());
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * 删除第一个文档
     * 要删除集合中的第一个文档，首先你需要使用com.mongodb.DBCollection类中的 findOne()方法来获取第一个文档，然后使用remove 方法删除。
     */
    public static void delete() {
        try {
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient(MongoConstant.host, MongoConstant.port);
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoConstant.databaseName);

            System.out.println("Connect to database successfully");

            MongoCollection<Document> collection = mongoDatabase.getCollection("test");
            System.out.println("集合 test 选择成功");

            //删除符合条件的第一个文档
            collection.deleteOne(Filters.eq("likes", 200));
            //删除所有符合条件的文档
            collection.deleteMany(Filters.eq("likes", 200));
            //检索查看结果
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