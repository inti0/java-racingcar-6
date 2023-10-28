package racingcar.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import racingcar.model.car.Car;
import racingcar.model.CarRepository;
import racingcar.view.View;

public class RaceControllerIntegerVer implements RaceController {

    private static final int MOVE_CRITERIA = 4;
    private static final int MOVE_START_RANGE = 0;
    private static final int MOVE_END_RANGE = 9;
    private static View view = new View();


    @Override
    public void processRace(String input, CarRepository carRepository) {

        raceRepeatByInput(input, carRepository);

        Map<Integer, List<Car>> rankingMap = PartitioningByRank(carRepository);
        List<Car> winnerCarList = getWinnerList(rankingMap);
        List<String> winners = ConvertCarToString(winnerCarList);

        view.printWinner(winners);
    }

    private void raceRepeatByInput(String input, CarRepository carRepository) {
        int round = Integer.parseInt(input);
        int size = carRepository.size();

        for (int i = 0; i < round; i++) {
            for (int j = 0; j < size; j++) {
                Car car = carRepository.getCar(j);

                int randomNumber = pickRandomNumber();
                boolean canMove = randomNumber >= MOVE_CRITERIA;
                car.move(randomNumber, canMove);

                view.print(car, randomNumber);
            }
            System.out.println();
        }
    }

    private static Map<Integer, List<Car>> PartitioningByRank(CarRepository carRepository) {
        HashMap<Integer, List<Car>> map = new HashMap<>();
        int size = carRepository.size();
        for (int i = 0; i < size; i++) {
            Car car = carRepository.getCar(i);

            int position = car.getPosition();
            List<Car> samePositionList = map.getOrDefault(position, new ArrayList<Car>());
            samePositionList.add(car);
            map.put(position, samePositionList);
        }
        return map;
    }

    private static List<Car> getWinnerList(Map<Integer, List<Car>> rankMap) {
        return rankMap.keySet()
                .stream()
                .collect(Collectors.maxBy(Comparator.naturalOrder()))
                .map(key -> rankMap.get(key))
                .get();


    }

    private static List<String> ConvertCarToString(List<Car> winnerCarList) {
        return winnerCarList
                .stream()
                .map(car -> car.getCarName().getName())
                .toList();

    }
    private int pickRandomNumber() {
        return Randoms.pickNumberInRange(MOVE_START_RANGE, MOVE_END_RANGE);
    }

    @Override
    public void validateRoundInput(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
