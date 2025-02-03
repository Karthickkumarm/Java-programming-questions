class Traffic {
    public static void main(String[] args) {
        TrafficLightSystem trafficLight = new TrafficLightSystem();

        while (true) {
            trafficLight.showState();
            trafficLight.changeState();
        }
    }
}

class TrafficLightSystem {
    private LightState[] states;
    private LightState currentState;
    private int currentIndex;

    public TrafficLightSystem() {
        states = new LightState[]{new Red(), new Yellow(), new Green()};
        currentState = states[0]; // Initial state is Red
        currentIndex = 0;
    }

    public void changeState() {
        currentIndex = (currentIndex + 1) % states.length;
        currentState = states[currentIndex];
    }

    public void showState() {
        currentState.show();
    }
}

abstract class LightState {
    public abstract void show();
}

class Red extends LightState {
    @Override
    public void show() {
        System.out.println("Red Light");
        try {
            Thread.sleep(3000); // 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Green extends LightState {
    @Override
    public void show() {
        System.out.println("Green Light");
        try {
            Thread.sleep(3000); // 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Yellow extends LightState {
    @Override
    public void show() {
        System.out.println("Yellow Light");
        try {
            Thread.sleep(2000); // 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}