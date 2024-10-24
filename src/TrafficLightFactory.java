public class TrafficLightFactory {
    static TrafficLight[] createCarLights() {
        return new TrafficLight[]{
                new TrafficLight(1, "Car Light 1", TrafficLightType.CAR),
                new TrafficLight(2, "Car Light 2", TrafficLightType.CAR),
                new TrafficLight(3, "Car Light 3", TrafficLightType.CAR),
                new TrafficLight(4, "Car Light 4", TrafficLightType.CAR)
        };
    }

    static TrafficLight[] createPedestrianLights() {
        return new TrafficLight[]{
                new TrafficLight(1, "Pedestrian Light 1", TrafficLightType.PEDESTRIAN),
                new TrafficLight(2, "Pedestrian Light 2", TrafficLightType.PEDESTRIAN),
                new TrafficLight(3, "Pedestrian Light 3", TrafficLightType.PEDESTRIAN),
                new TrafficLight(4, "Pedestrian Light 4", TrafficLightType.PEDESTRIAN),
                new TrafficLight(5, "Pedestrian Light 5", TrafficLightType.PEDESTRIAN),
                new TrafficLight(6, "Pedestrian Light 6", TrafficLightType.PEDESTRIAN),
                new TrafficLight(7, "Pedestrian Light 7", TrafficLightType.PEDESTRIAN),
                new TrafficLight(8, "Pedestrian Light 8", TrafficLightType.PEDESTRIAN)
        };
    }
}
