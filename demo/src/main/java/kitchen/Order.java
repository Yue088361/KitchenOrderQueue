package kitchen;

public class Order {

    private String id;          
    private OrderStatus status; 

    public Order(String id) {
        this.id = id;
        this.status = OrderStatus.NEW; 
    }

    /*public String getId() {
        return id;
    }

      public OrderStatus getStatus() {
        return status;
    }
    
     public void setStatus(OrderStatus status) {
        this.status = status;
    }*/
  
    
    
}
