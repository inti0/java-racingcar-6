package racingcar.Controller;

import Config.AppConfig;
import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import racingcar.Model.Car.Car;
import racingcar.Model.Car.CarId;
import racingcar.Model.CarRepository;
import racingcar.View.RaceView;

public class MainController {
    private static RaceController raceController = AppConfig.raceControllerImplements();

    public static void process() {
        String NameInput = Console.readLine();
        String[] split = NameInput.split(AppConfig.INPUT_DELIMITER, Integer.MAX_VALUE);
        int carQuantity = split.length;
        CarId.validateRange(carQuantity);

        CarRepository carRepository = new CarRepository(new ArrayList<>());
        for(int i=0; i<carQuantity; i++) {
            Car participantCar = Car.of(i, split[i]);
            carRepository.save(participantCar);
        }

        String roundInput = Console.readLine();

        RaceView.processStartMessage();
        raceController.validateRoundInput(roundInput);
        raceController.processRace(roundInput, carRepository);
    }
}
