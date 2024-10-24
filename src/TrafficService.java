public class TrafficService {
    static void startTrafficLightProcessing(TrafficLight[] carLights, TrafficLight[] pedestrianLights) {
        for (TrafficLight carLight : carLights) {
            carLight.processEvents();
        }
        for (TrafficLight pedestrianLight : pedestrianLights) {
            pedestrianLight.processEvents();
        }
    }

    private static int maxBusyRoad(int[] carCounts, int[] pedestrianCounts) {
        int[] roadCars = {
                carCounts[0] + carCounts[2],
                carCounts[1] + carCounts[3]
        };
        int[] roadPedestrians = {
                pedestrianCounts[0] + pedestrianCounts[2] + pedestrianCounts[4] + pedestrianCounts[6],
                pedestrianCounts[1] + pedestrianCounts[3] + pedestrianCounts[5] + pedestrianCounts[7]
        };
        int[] totalRoads = {
                roadCars[0] + roadPedestrians[0],
                roadCars[1] + roadPedestrians[1]
        };
        int maxRoadIndex = (totalRoads[0] > totalRoads[1]) ? 0 : 1;
        System.out.println("Максимальная загруженность на " + (maxRoadIndex + 1) + " дороге");
        return maxRoadIndex + 1;
    }

    static int determineMaxBusyTraffic(int[] carCounts, int[] pedestrianCounts) {
        int maxTraffic = maxBusyRoad(carCounts, pedestrianCounts);

        int cars = (maxTraffic == 1) ? (carCounts[0] + carCounts[2]) : (carCounts[1] + carCounts[3]);
        int pedestrians = (maxTraffic == 1)
                ? (pedestrianCounts[0] + pedestrianCounts[2] + pedestrianCounts[4] + pedestrianCounts[6])
                : (pedestrianCounts[1] + pedestrianCounts[3] + pedestrianCounts[5] + pedestrianCounts[7]);

        if (cars > pedestrians) {
            System.out.println("Больше на этой дороге автомобилей");
            return maxTraffic;
        } else {
            System.out.println("Больше на этой дороге пешеходов");
            return (maxTraffic == 1) ? 2 : 1;
        }
    }

    static void processBusyRoad(int busyRoad, TrafficLight[] carLights, TrafficLight[] pedestrianLights) {
        if (busyRoad == 1) {
            processEventsFirst(carLights, pedestrianLights);
        } else {
            processEventsSecond(carLights, pedestrianLights);
        }
    }

    private static void processEventsFirst(TrafficLight[] carLights, TrafficLight[] pedestrianLights) {
        setGreenForCarLights(carLights[0], carLights[2], carLights[1], carLights[3], carLights);
        setPedestrianLightsState(pedestrianLights, true);
        TrafficManager.printTrafficLightStates(carLights, pedestrianLights);
        pause(2000);
        setYellowAll(carLights, pedestrianLights);
        System.out.println("Переоценка загруженности...");
        pause(2000);
    }

    private static void processEventsSecond(TrafficLight[] carLights, TrafficLight[] pedestrianLights) {
        setGreenForCarLights(carLights[1], carLights[3], carLights[0], carLights[2], carLights);
        setPedestrianLightsState(pedestrianLights, false);
        TrafficManager.printTrafficLightStates(carLights, pedestrianLights);
        pause(5000);
        setYellowAll(carLights, pedestrianLights);
        System.out.println("Переоценка загруженности...");
        pause(2000);
    }

    private static void setGreenForCarLights(TrafficLight greenLight1, TrafficLight greenLight2, TrafficLight redLight1,
                                             TrafficLight redLight2, TrafficLight[] allLights) {
        greenLight1.sendEvent(greenLight1.getId(), new TrafficEvent(greenLight1.getId(), greenLight1.getId(),
                "CHANGE_STATE", 0, TrafficLightState.GREEN), allLights);
        greenLight2.sendEvent(greenLight2.getId(), new TrafficEvent(greenLight2.getId(), greenLight2.getId(),
                "CHANGE_STATE", 0, TrafficLightState.GREEN), allLights);
        redLight1.sendEvent(redLight1.getId(), new TrafficEvent(redLight1.getId(), redLight1.getId(),
                "CHANGE_STATE", 0, TrafficLightState.RED), allLights);
        redLight2.sendEvent(redLight2.getId(), new TrafficEvent(redLight2.getId(), redLight2.getId(),
                "CHANGE_STATE", 0, TrafficLightState.RED), allLights);
    }

    public static void setPedestrianLightsState(TrafficLight[] pedestrianLights, boolean isFirstPhase) {
        for (int i = 0; i < pedestrianLights.length; i++) {
            if (isFirstPhase) {
                if (i == 0 || i == 1 || i == 4 || i == 5) {
                    pedestrianLights[i].setState(TrafficLightState.RED);
                }
                if (i == 2 || i == 3 || i == 6 || i == 7) {
                    pedestrianLights[i].setState(TrafficLightState.GREEN);
                }
            }
            if (!isFirstPhase) {
                if (i == 0 || i == 1 || i == 4 || i == 5) {
                    pedestrianLights[i].setState(TrafficLightState.GREEN);
                }
                if (i == 2 || i == 3 || i == 6 || i == 7) {
                    pedestrianLights[i].setState(TrafficLightState.RED);
                }
            }
        }

    }

    public static void setYellowAll(TrafficLight[] carLights, TrafficLight[] pedestrianLights) {
        for (TrafficLight carLight : carLights) {
            carLight.setState(TrafficLightState.YELLOW);
        }
        for (TrafficLight pedestrianLight : pedestrianLights) {
            pedestrianLight.setState(TrafficLightState.RED);
        }
        TrafficManager.printTrafficLightStates(carLights, pedestrianLights);
    }

    static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
