package kitchen;

public class FakeNotificationService implements NotificationService {
    String lastNotifiedOrderId = null;
    public boolean shouldSucceed = true; // allows testing success + failure

    @Override
    public boolean notifyReady(String orderId) {
        lastNotifiedOrderId = orderId;
        return shouldSucceed;
    }
}