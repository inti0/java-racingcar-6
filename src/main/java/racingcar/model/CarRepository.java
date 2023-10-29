package racingcar.model;

import java.util.ArrayList;
import java.util.List;
import racingcar.model.car.Car;

public class CarRepository {

    private List<Car> carList;

    public CarRepository(List<Car> carList) {
        this.carList = carList;
    }

    public void save(Car car) {
        carList.add(car);
    }

    public Car getCar(int index){
        return carList.get(index);
    }

    public int size() {
        return carList.size();
    }
}
