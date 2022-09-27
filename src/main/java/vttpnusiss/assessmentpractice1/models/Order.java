package vttpnusiss.assessmentpractice1.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Order {

    private Integer orderId;
    private String email;
    

    public Integer getOrderId() {
        return orderId;
    }


    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    
    public static Order create(SqlRowSet rs){
        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setEmail(rs.getString("email"));
        return order;
    }


    public JsonObject toJson(){
        return Json.createObjectBuilder()
        .add("orderId",orderId)
        .add("email", email)
        .build();
    }

}
