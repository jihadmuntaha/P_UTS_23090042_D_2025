package com.mycompany.p_uts_23090042_d_2025;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.Arrays;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author user
 */
public class CRUD_23090042_D_2025 {
    
    private static void viewData(FindIterable<Document> doc, String text){
        System.out.println(text);
        for (Document d : doc){
            System.out.println(d);}
    }
    
     public static void main(String[] args) {
        //Menghubungkan ke database
        String URL = "mongodb://localhost:27017/";
        MongoClient client = MongoClients.create(URL);
        MongoDatabase db = client.getDatabase("uts_23090042_D_2025");
        MongoCollection<Document> tabel = db.getCollection("coll_23090042_D_2025");
        
        //Insert data (create)
        Document data = new Document("name","Sepatu Nike Air Max 213")
                .append("size", Arrays.asList("41", "42", "43", "44", "45"))
                .append("color", Arrays.asList("Red","White", "Blue", "Green"))
                .append("quantity", 45);
        tabel.insertOne (data);
        //view
        FindIterable<Document> result = tabel.find();
        for (Document d : result) {
            System.out.println(d.toJson());
            //System.out.println("Nama Produk: "+d.get("name"));
            //System.out.println("Quantity: "+d.get("quantity"));
        }   
        
        
        //Update
        Bson filter = Filters.eq("name","Sepatu Nike Air Max 213");
        Bson update = Updates.set("xup", "x245");
        tabel.updateOne(filter, update);
        
        //view
        result = tabel.find();
        for (Document d : result) {
            System.out.println(d.toJson());
            //System.out.println("Nama Produk: "+d.get("name"));
            //System.out.println("Quantity: "+d.get("quantity"));
        }
        
        //Delete Data
        System.out.println("===Delete Data===");
        Bson dataTarget = Filters.eq("xup", "x245");
        tabel.deleteOne(dataTarget);
        
        //Search Data
        System.out.println("===Search Data===");
        Bson find = Filters.eq("xup", "x245");
        Document d = tabel.find(find).first();
        if(d !=null) {
            System.out.println(d.toJson());
        }else {
            System.out.println("Data Not Found");
        }
        
        
    }
}

