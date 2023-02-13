package validation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarInfoValidation {

    private static final int CAR_NAME_LENGTH_MAX = 5;
    private static final int CAR_COUNT_MIN = 0;
    private static final String SPLIT_DELIMITER = ",";

    public static void validateCar(String input) {
        final String[] cars = input.split(SPLIT_DELIMITER);
        validateLength(cars);
        validateName(cars);
        validateDuplicatedCarName(cars);
    }

    private static void validateLength(final String[] cars) {
        if (cars.length - 1 == CAR_COUNT_MIN && isEmpty(cars[0])) {
            throw new IllegalArgumentException("자동차를 한 대 이상 작성해주세요.");
        }
    }

    private static boolean isEmpty(final String car) {
        if (car.equals("") || car.equals(" ")) {
            return true;
        }

        return false;
    }

    private static void validateName(final String[] cars) {
        for (final String car : cars) {
            if (car.length() > CAR_NAME_LENGTH_MAX) {
                throw new IllegalArgumentException("자동차 이름은 5자 이하로 작성해주세요.");
            }
        }
    }

    private static void validateDuplicatedCarName(final String[] cars) {
        Set<String> checkDuplicated = new HashSet<>(List.of(cars));

        if (checkDuplicated.size() != cars.length) {
            throw new IllegalArgumentException("자동차 이름은 중복되지 않아야합니다.");
        }
    }

}