public class TrafficEvent {
    private final int senderId;
    private final int receiverId;
    private final String eventType;
    private final int queueSize;
    private final TrafficLightState currentState;

    public TrafficEvent(int senderId, int receiverId, String eventType, int queueSize, TrafficLightState currentState) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.eventType = eventType;
        this.queueSize = queueSize;
        this.currentState = currentState;
    }

    public String getEventType() {
        return eventType;
    }

    public TrafficLightState getCurrentState() {
        return currentState;
    }
}

