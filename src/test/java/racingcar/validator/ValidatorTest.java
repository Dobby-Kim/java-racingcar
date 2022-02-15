package racingcar.validator;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import racingcar.controller.RacingCarController;

@SuppressWarnings("NonAsciiCharacters")
public class ValidatorTest {
	public RacingCarController racingCarController;

	@BeforeEach
	public void beforeEach() {
		racingCarController = new RacingCarController();
	}

	@Test
	public void 빈_문자열_입력_테스트() {
		assertThatThrownBy(() -> racingCarController.getCarNames(""))
			.isInstanceOf(RuntimeException.class)
			.hasMessageContaining("빈 문자열을 자동차 이름으로 입력할 수 없습니다.");
	}

	@Test
	public void 자동차_이름_글자수_테스트() {
		assertThatThrownBy(() -> racingCarController.getCarNames("이브,클레이클레이,포비"))
			.isInstanceOf(RuntimeException.class)
			.hasMessageContaining("자동차 이름은 5자 이하여야 합니다.");
	}

	@Test
	public void 자동차_이름이_존재하지_않는_경우_테스트() {
		assertThatThrownBy(() -> racingCarController.getCarNames("이브,,포비"))
			.isInstanceOf(RuntimeException.class)
			.hasMessageContaining("모든 자동차 이름은 반드시 존재해야 합니다.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"이브, ,포비", "이브,   ,포비"})
	public void 자동차_이름이_공백인_경우_테스트(String carNamesLine) {
		assertThatThrownBy(() -> racingCarController.getCarNames(carNamesLine))
			.isInstanceOf(RuntimeException.class)
			.hasMessageContaining("자동차 이름은 공백으로 설정할 수 없습니다.");
	}

	@Test
	public void 자동차_이름_중복_테스트() {
		assertThatThrownBy(() -> racingCarController.getCarNames("이브,이브,포비"))
			.isInstanceOf(RuntimeException.class)
			.hasMessageContaining("자동차 이름은 중복될 수 없습니다.");
	}

	@Test
	public void 시도_횟수_입력_테스트() {
		int gameCount = racingCarController.getTrialCount("3");
		assertThat(gameCount).isEqualTo(3);
	}

	@Test
	public void 숫자가_아닌_시도_횟수_테스트() {
		assertThatThrownBy(() -> racingCarController.getTrialCount("이브"))
			.isInstanceOf(RuntimeException.class)
			.hasMessageContaining("시도 횟수가 숫자가 아닙니다.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"-1", "0"})
	public void 시도_횟수_음수_혹은_0_테스트(String trial) {
		assertThatThrownBy(() -> racingCarController.getTrialCount(trial))
			.isInstanceOf(RuntimeException.class)
			.hasMessageContaining("시도 횟수가 음수나 0이 될 수 없습니다.");
	}
}