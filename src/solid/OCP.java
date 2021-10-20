package solid;

public class OCP {
    public static void main(String[] args) {
        Car car1 = new Car(2015, new Engine());
        System.out.println(car1);

        Car car2 = new Car(car1);// java does not provide copy constructor
        System.out.println(car2);
    }
}

class Engine {
    public Engine() {
    }

    protected Engine(Engine other) {
    }

    public Engine copy(){
        return new Engine(this);
    }

    @Override
    public String toString() {
        return getClass().getName() + ":" + hashCode();
    }
}

class TurboEngine extends Engine {
    public TurboEngine() {
        super();
    }

    public Engine copy(){
        return new TurboEngine(this);
    }

    public TurboEngine(TurboEngine other) {
        super(other);
    }
}

class PistonEngine extends Engine{
    public PistonEngine(){
        super();
    }

    public PistonEngine(PistonEngine other){
        super(other);
    }

    public Engine copy(){
        return new PistonEngine(this);
    }
}
class Car {
    private int year;
    private Engine engine;

    public Car(int theYear, Engine theEngine) {
        year = theYear;
        engine = theEngine;
    }

    public Car(Car other) {
        year = other.year;
//        engine = other.engine; // does not work . not copy engine we need copy of engine (deep copy)
//        if (other.engine instanceof TurboEngine)
//            engine = new TurboEngine((TurboEngine) (other.engine));
//        else
//            engine = new Engine(other.engine); // new is not polymorphic

        engine = other.engine.copy();
    }

    @Override
    public String toString() {
        return year + " - " + engine;
    }
}
// what will cost to change
//the cons of car has to change. when you create a new type of engine

// tight coupling
// car is depending on engine and turbo engine
// low cohesion