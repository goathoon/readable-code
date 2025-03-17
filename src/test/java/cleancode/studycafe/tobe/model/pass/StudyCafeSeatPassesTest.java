package cleancode.studycafe.tobe.model.pass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;


class StudyCafeSeatPassesTest {

    @DisplayName("스터디 카페의 이용권 타입과 일치하는 좌석 이용권을 가져온다.")
    @Test
    void filterSeatPassByPassType() {
        // given
        StudyCafeSeatPasses studyCafeSeatPasses = StudyCafeSeatPasses.of(List.of(
            StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 1, 1000, 10d),
            StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 2, 2000, 20d),
            StudyCafeSeatPass.of(StudyCafePassType.FIXED, 0, 0, 0),
            StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 0, 0, 0))
        );

        StudyCafePassType filteredType = StudyCafePassType.HOURLY;

        // when // then
        assertThat(studyCafeSeatPasses.findPassBy(filteredType)).hasSize(2)
            .extracting("duration", "price", "discountRate")
            .containsExactlyInAnyOrder(
                tuple(1, 1000, 10d),
                tuple(2, 2000, 20d)
            );
    }

    @DisplayName("스터디 카페의 이용권 타입과 일치하는 좌석 이용권이 없는 경우가 있다.")
    @Test
    void seatPassDoesNotExistWhenPassTypeNotMatched() {
        // given
        StudyCafeSeatPasses studyCafeSeatPasses = StudyCafeSeatPasses.of(List.of(
            StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 1, 1000, 10d),
            StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 2, 2000, 20d),
            StudyCafeSeatPass.of(StudyCafePassType.FIXED, 0, 0, 0)
        ));

        StudyCafePassType filteredType = StudyCafePassType.WEEKLY;

        // when // then
        assertThat(studyCafeSeatPasses.findPassBy(filteredType)).isEmpty();
    }
}
