import java.util.Random;

public class TrafficManager {
    static int[] generateRandomCountsCars(int length) {
        Random random = new Random();
        int[] counts = new int[length];
        for (int i = 0; i < length; i++) {
            counts[i] = random.nextInt(20);
        }
        return counts;
    }

    static int[] generateRandomCountsPedestrians(int length) {
        Random random = new Random();
        int[] counts = new int[length];
        for (int i = 0; i < length; i++) {
            counts[i] = random.nextInt(10);
        }
        return counts;
    }

    static void printInitialConditions(int[] carCounts, int[] pedestrianCounts) {
        System.out.println("Начальные условия:");
        System.out.println("Текущая загруженность автомобилями:");
        for (int i = 0; i < carCounts.length; i++) {
            System.out.println("Дорога " + (i + 1) + ": " + carCounts[i] + " машин");
        }

        System.out.println("Текущая загруженность пешеходами:");
        for (int i = 0; i < pedestrianCounts.length; i++) {
            System.out.println("Переход " + (i + 1) + ": " + pedestrianCounts[i] + " пешеходов");
        }
    }

    static void printTrafficLightStates(TrafficLight[] carLights, TrafficLight[] pedestrianLights) {
        System.out.println("Состояние автомобильных светофоров:");
        for (int i = 0; i < carLights.length; i++) {
            System.out.println("Светофор " + (i + 1) + ": " + carLights[i].getState());
        }

        System.out.println("Состояние пешеходных светофоров:");
        for (int i = 0; i < pedestrianLights.length; i++) {
            System.out.println("Пешеходный светофор " + (i + 1) + ": " + pedestrianLights[i].getState());
        }
    }
}
