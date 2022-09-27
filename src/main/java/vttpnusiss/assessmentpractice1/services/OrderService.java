package vttpnusiss.assessmentpractice1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttpnusiss.assessmentpractice1.models.Item;
import vttpnusiss.assessmentpractice1.models.Order;
import vttpnusiss.assessmentpractice1.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;
    
    public List<Order> getAllOrders(){
        return orderRepo.getAllOrders();
    }

    public Order getOrderById(Integer id){
        return orderRepo.getOrder(id);
    }

    public boolean addOrder(Order order){
        return orderRepo.addOrder(order);
    }

    public List<Item> getAllItems(){
        return orderRepo.getAllItems();
    }

    public Item getItemByName(String name){
        return orderRepo.getItem(name);
    }

}
