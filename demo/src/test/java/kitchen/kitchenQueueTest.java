package kitchen;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class kitchenQueueTest {

    private kitchenQueue queue;
    @BeforeEach
    void setUp() {
    queue = new kitchenQueue(); // fresh queue before each test
    }

    //New order appears automatically

    @Test
    void newOrderAppearsInTheQueue() {
        Order newOrder = new Order ("ID: 101");
        queue.receiveNewOrder(newOrder);

        assertTrue(queue.getActiveOrders().contains(newOrder));
    }
    // Order Status updates in real-time.
    /*@Test
    void orderStatusShouldUpdateToInProgressAndThenComplete(){
         Order newOrder = new Order ("ID: 101");
         queue.receiveNewOrder(newOrder);

          // move to IN_PROGRESS 
          queue.markOrderInProgress(newOrder.getId()); 
          assertEquals(OrderStatus.IN_PROGRESS,newOrder.getStatus(), "Order status should be IN_PROGRESS after starting"); 

         // move to complete
            queue.markOrderComplete(newOrder.getId());
            assertEquals(OrderStatus.COMPLETE,newOrder.getStatus(),
            "Order status should be COMPLETE after finishing");   
       }*/

    }
   
   
    





