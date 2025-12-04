package kitchen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class kitchenQueue {

    private List<Order> activeOrders = new ArrayList<>();
    //private final Map<String, Order> orders = new HashMap<>();
    
    public void receiveNewOrder(Order order) {
        if (order!= null){
        activeOrders.add(order);
        }
    }
    public List<Order> getActiveOrders() {
        return Collections.unmodifiableList(activeOrders);
    }

    /*public void markOrderInProgress(String orderId) {
        Order order = orders.get(orderId);
        if (order == null) throw new IllegalArgumentException("Order not found: " + orderId);
        order.setStatus(OrderStatus.IN_PROGRESS);
    }

    public void markOrderComplete(String orderId) {
        Order order = orders.get(orderId);
        if (order == null) throw new IllegalArgumentException("Order not found: " + orderId);
        order.setStatus(OrderStatus.COMPLETE);
    }*/

    
    
}

   