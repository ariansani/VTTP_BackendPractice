package vttpnusiss.assessmentpractice1.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttpnusiss.assessmentpractice1.models.Item;
import vttpnusiss.assessmentpractice1.models.Order;

@Repository
public class OrderRepository {

    private static final String SQL_GET_ALL_ORDERS = "SELECT * FROM order_list";
    private static final String SQL_GET_ORDER = "SELECT * FROM order_list WHERE order_id = ?";
    private static final String SQL_ADD_ORDER = "INSERT INTO order_list (email) VALUES(?)";
    private static final String SQL_GET_ALL_ITEMS = "SELECT * from items";
    private static final String SQL_GET_ITEM_BY_NAME = "select * from items where item_name LIKE ?";

    @Autowired
    private JdbcTemplate template;

    public List<Order> getAllOrders() {
        List<Order> orderList = new LinkedList<>();

        SqlRowSet rs = template.queryForRowSet(SQL_GET_ALL_ORDERS);
        while (rs.next()) {
            Order order = Order.create(rs);
            orderList.add(order);
        }
        return orderList;
    }

    public Order getOrder(Integer id) {
        Order order = new Order();

        SqlRowSet rs = template.queryForRowSet(SQL_GET_ORDER, id);
        while (rs.next()) {
            order = Order.create(rs);

        }
        return order;

    }

    public boolean addOrder(Order order){
        int count = template.update(SQL_ADD_ORDER, order.getEmail());

        return 1 == count;
    }




    public List<Item> getAllItems(){
        List<Item> itemList = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GET_ALL_ITEMS);
        while (rs.next()) {
            Item item = Item.create(rs);
            itemList.add(item);
        }
        return itemList;
    }

    public Item getItem(String name) {
        Item item = new Item();

        SqlRowSet rs = template.queryForRowSet(SQL_GET_ITEM_BY_NAME,name);

        while (rs.next()) {
            item = Item.create(rs);

        }
        return item;

    }

}
