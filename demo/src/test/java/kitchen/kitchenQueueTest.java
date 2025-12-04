package kitchen;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse; 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KitchenQueueTest {

    private KitchenQueue queue;
    @BeforeEach
    void setUp() {
    queue = new KitchenQueue(); // fresh queue before each test
    }

    //New order appears automatically

    @Test
    void newOrderAppearsInTheQueue() {
        Order newOrder = new Order ("ID: 101");
        queue.receiveNewOrder(newOrder);

        assertTrue(queue.getActiveOrders().contains(newOrder));
    }
    @Test
    void orderStatusShouldUpdateToInProgress() {
        Order order = new Order("102");
        queue.receiveNewOrder(order);

        queue.markOrderInProgress(order.getId());  

        assertEquals(OrderStatus.IN_PROGRESS, order.getStatus(),
                "Order status should be IN_PROGRESS after starting");
    }
    @Test
    void completedOrdersShouldBeRemovedFromActiveList() {
    Order order = new Order("103");
    queue.receiveNewOrder(order);

    queue.completeOrder(order.getId());  

    List<Order> activeOrders = queue.getActiveOrders();

    assertFalse(activeOrders.contains(order),
            "Completed order should not be in active list");
    assertEquals(OrderStatus.COMPLETED, order.getStatus(),
            "Order status should be COMPLETED");
}
    
    }
   
   
    





