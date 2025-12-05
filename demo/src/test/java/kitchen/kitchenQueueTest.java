package kitchen;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KitchenQueueTest {
    private NotificationService notifier;
    private KitchenQueue queue;
    @BeforeEach
    void setUp() {
        notifier = mock(NotificationService.class);
        queue = new KitchenQueue(notifier); // fresh queue before each test
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
        assertEquals(OrderStatus.COMPLETE, order.getStatus(),
        "Order status should be COMPLETED");
    }
    
    // STUB: Network failure notifying system.
    @Test
        void testConnectionLostHandling() {
        Order order = new Order("104");
        queue.receiveNewOrder(order);
        when(notifier.notifyReady(order.getId())).thenReturn(false);
        queue.orderComplete(order.getId());
        assertTrue(queue.hasRetries(), "Order ID should be stored for retry when connection is lost");
    }

    // Customer should receive notification
    @Test
        void CustomerShouldBeNotifiedWhenOrderIsComplete(){
        FakeNotificationService fakeNotifier = new FakeNotificationService();
        KitchenQueue queue = new KitchenQueue(fakeNotifier);

        Order order = new Order("105");
        queue.receiveNewOrder(order);
        
        queue.orderComplete(order.getId());

        assertEquals("105", fakeNotifier.lastNotifiedOrderId,
        "Notifier should receive correct order ID when order is completed");
    }       
}



