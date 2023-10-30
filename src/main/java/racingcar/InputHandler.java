package racingcar;

import camp.nextstep.edu.missionutils.Console;

public class InputHandler {
    public static String readInput() {
        return Console.readLine();
    }

    public static String[] StringToArray(String string){
        return string.split(AppConfig.INPUT_DELIMITER, Integer.MAX_VALUE);
    }

    public static int StringToInteger(String input) {
        int round = validInteger(input);
        validateRange(round);
        return round;
    }

    private static void validateRange(int round) {
        if (round <= 0) {
            throw new IllegalArgumentException();
        }
    }

    private static int validInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
