package vttpnusiss.assessmentpractice1.controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttpnusiss.assessmentpractice1.models.Order;
import vttpnusiss.assessmentpractice1.services.OrderService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderRESTController {

    @Autowired
    private OrderService orderSvc;

    @GetMapping(path = "/order")
    public ResponseEntity<String> getAllOrders() {
        List<Order> orderList = orderSvc.getAllOrders();

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Order order : orderList) {
            arrayBuilder.add(order.toJson());
        }
        return ResponseEntity.ok(arrayBuilder.build().toString());

    }

    @GetMapping(("/order/{id}"))
    public ResponseEntity<String> getOrder(@PathVariable Integer id) {

        Order order = orderSvc.getOrderById(id);

        // tv.toJson();
        return ResponseEntity.ok(order.toJson().toString());

    }

    @PostMapping(path = "/order/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addOrder(@RequestBody String payload) {

        JsonObject responseJson;
        
        try (InputStream is = new ByteArrayInputStream(payload.getBytes(StandardCharsets.UTF_8))) {
            JsonReader reader = Json.createReader(is);
            JsonObject o = reader.readObject();
            
            Order order = new Order();
            order.setEmail(o.getString("email"));

            boolean addedSuccessfully = orderSvc.addOrder(order);

            responseJson = Json.createObjectBuilder()
            .add("status", addedSuccessfully)
            .build();

        } catch (Exception ex) {
            JsonObject errJson = Json.createObjectBuilder()
                    .add("errorMessage", ex.getMessage()).build();
           return ResponseEntity.status(400).body(errJson.toString());
        }
        return ResponseEntity.ok(responseJson.toString());

    }

}
