import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrafficLight {
    private final int id;
    private final String type;
    private final TrafficLightType trafficLightType;
    private TrafficLightState state;
    private final BlockingQueue<TrafficEvent> eventQueue = new LinkedBlockingQueue<>();

    public TrafficLight(int id, String type, TrafficLightType trafficLightType) {
        this.id = id;
        this.type = type;
        this.trafficLightType = trafficLightType;
        this.state = TrafficLightState.RED;
    }

    public int getId() {
        return id;
    }

    public TrafficLightState getState() {
        return state;
    }

    public void setState(TrafficLightState state) {
        this.state = state;
    }

    public void sendEvent(int receiverId, TrafficEvent event, TrafficLight[] allLights) {
        TrafficLight receiver = getTrafficLightById(receiverId, allLights);
        if (receiver != null) {
            receiver.eventQueue.add(event);
        }
    }

    public void processEvents() {
        new Thread(() -> {
            while (true) {
                try {
                    TrafficEvent event = eventQueue.take();
                    handleEvent(event);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }

    private void handleEvent(TrafficEvent event) {
        if ("CHANGE_STATE".equals(event.getEventType())) {
            this.state = event.getCurrentState();
        }
    }

    private TrafficLight getTrafficLightById(int id, TrafficLight[] allLights) {
        for (TrafficLight light : allLights) {
            if (light.getId() == id) {
                return light;
            }
        }
        return null;
    }

}

