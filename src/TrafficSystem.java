
public class TrafficSystem {

    public static void main(String[] args) {
        TrafficLight[] carLights = TrafficLightFactory.createCarLights();
        TrafficLight[] pedestrianLights = TrafficLightFactory.createPedestrianLights();
        TrafficService.startTrafficLightProcessing(carLights, pedestrianLights);

        while (true) {
            int[] carCounts = TrafficManager.generateRandomCountsCars(carLights.length);
            int[] pedestrianCounts = TrafficManager.generateRandomCountsPedestrians(pedestrianLights.length);

            TrafficManager.printInitialConditions(carCounts, pedestrianCounts);

            int busyRoad = TrafficService.determineMaxBusyTraffic(carCounts, pedestrianCounts);
            TrafficService.processBusyRoad(busyRoad, carLights, pedestrianLights);

            TrafficService.pause(5000);
        }
    }
}


