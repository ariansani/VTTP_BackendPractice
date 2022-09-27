package vttpnusiss.assessmentpractice1.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Item {
    
    private Integer itemId;
    private String name;

    public Integer getItemId() {
        return itemId;
    }
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
   

    public static Item create(SqlRowSet rs){
        Item item = new Item();
        item.setItemId(rs.getInt("item_id"));
        item.setName(rs.getString("item_name"));

        return item;
    }


    public JsonObject toJson(){
        return Json.createObjectBuilder()
        .add("itemId", itemId)
        .add("name",name)
        .build();
    }

    
}
