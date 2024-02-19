package racingcar.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RefereeTest {

    Car car1;
    Car car2;
    Car car3;

    @BeforeEach
    void setCars() {
        car1 = new Car("Car1", new FixNumberGenerator());
        car2 = new Car("Car2", new FixNumberGenerator());
        car3 = new Car("Car3", new FixNumberGenerator());
    }

    @Test
    @DisplayName("한 명의 우승자가 결과일 때 winner list에 추가되는지 테스트")
    void testSingleWinnerInRace() {
        Referee referee = new Referee();
        car1.move();
        car1.move();
        car1.move();
        car1.move();
        car1.move();

        Cars cars = new Cars(Arrays.asList(car1, car2, car3));

        List<String> winners = referee.determineWinnerNames(cars);
        assertEquals(1, winners.size());
        assertEquals(List.of("Car1"), winners);
    }

    @Test
    @DisplayName("다수의 우승자가 결과일 때 winner list에 추가되는지 테스트")
    void testMultipleWinnerInRace() {
        Referee referee = new Referee();
        car1.move();
        car1.move();
        car1.move();

        car2.move();
        car2.move();
        car2.move();

        Cars cars = new Cars(Arrays.asList(car1, car2, car3));

        List<String> winners = referee.determineWinnerNames(cars);
        assertEquals(2, winners.size());
        assertEquals(List.of("Car1", "Car2"), winners);
    }

    static class FixNumberGenerator implements NumberGenerator {
        @Override
        public int generateNumber(int bound) {
            return 4;
        }
    }
}