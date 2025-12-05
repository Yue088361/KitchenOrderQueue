package kitchen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KitchenQueue {

    private List<Order> activeOrders = new ArrayList<>();
    private NotificationService notifier;
    private List<String> retryQueue = new ArrayList<>();

    public KitchenQueue(NotificationService notifier) {
        this.notifier = notifier;
    }
    
    public void receiveNewOrder(Order order) {
        if (order!= null){
        activeOrders.add(order);
        }
    }
    public List<Order> getActiveOrders() {
        return Collections.unmodifiableList(activeOrders);
    }

    public void markOrderInProgress(String orderId) {
        Order order = findOrderById(orderId);
        order.setStatus(OrderStatus.IN_PROGRESS);
    }

    public void markOrderComplete(String orderId) {
        Order order = findOrderById(orderId);
        order.setStatus(OrderStatus.COMPLETE);
        activeOrders.remove(order); 
    }

    private Order findOrderById(String orderId) {
        for (Order order : activeOrders) {
            if (order.getId().equals(orderId)) {
                return order;
            }
        }
        throw new IllegalArgumentException("Order not found: " + orderId);
    }

    public void completeOrder(String orderId) {
        Order target = null;


        for (Order order : activeOrders) {
        if (order.getId().equals(orderId)) {
            target = order;
            break;
        }
        }

        if (target != null) {
        target.setStatus(OrderStatus.COMPLETE);
        activeOrders.remove(target);
        }
    }

    public void orderComplete(String orderId) {
        Order order = findOrderById(orderId);
        order.setStatus(OrderStatus.COMPLETE);

        boolean success = notifier.notifyReady(orderId); 
        if (!success) {
        retryQueue.add(orderId);
        }
    }
    
    public boolean hasRetries() {
        return !retryQueue.isEmpty();
    }
}

   