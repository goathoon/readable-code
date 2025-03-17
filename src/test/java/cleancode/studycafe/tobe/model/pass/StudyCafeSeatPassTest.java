package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StudyCafeSeatPassTest {

    @DisplayName("특정 Pass Type에 따라 Seat Pass가 Locker를 사용하지 못할 수 있다.")
    @Test
    void SeatPassCannotUserLocker() {
        // given
        StudyCafeSeatPass fixedTypePass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 0, 0, 0d);
        StudyCafeSeatPass hourlyTypePass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 0, 0, 0d);
        StudyCafeSeatPass weeklyTypePass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 0, 0, 0d);

        // when // then
        assertThat(fixedTypePass.cannotUseLocker()).isFalse();
        assertThat(hourlyTypePass.cannotUseLocker()).isTrue();
        assertThat(weeklyTypePass.cannotUseLocker()).isTrue();

    }

    @DisplayName("Locker Pass와 동일한 기간, Pass Type인지 확인한다.")
    @Test
    void sameDurationAndPassTypeAsLockerPass() {
        // given
        StudyCafePassType fixedType = StudyCafePassType.FIXED;
        int duration = 4;

        StudyCafeLockerPass fixTypeLockerPass = StudyCafeLockerPass.of(fixedType, duration, 0);
        StudyCafeLockerPass weeklyTypeLockerPass = StudyCafeLockerPass.of(StudyCafePassType.WEEKLY, duration, 0);

        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(fixedType, duration, 0, 0d);

        // when // then
        assertThat(studyCafeSeatPass.isSameDurationType(fixTypeLockerPass)).isTrue();
        assertThat(studyCafeSeatPass.isSameDurationType(weeklyTypeLockerPass)).isFalse();
    }

    @DisplayName("Seat Pass의 가격을 계산한다.")
    @Test
    void getDiscountPrice() {
        // given
        int price = 1000;
        double discountRate = 0.1;
        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 0, price, discountRate);

        // when
        int calculatedDiscountPrice = studyCafeSeatPass.getDiscountPrice();

        // then
        assertThat(calculatedDiscountPrice).isEqualTo((int) (price * discountRate));
    }

}
