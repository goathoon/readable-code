package cleancode.studycafe.tobe.model.order;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudyCafePassOrderTest {

    @DisplayName("주문의 전체 가격을 계산한다.")
    @Test
    void getTotalPrcie() {
        // given
        int seatPassPrice = 1000;
        int lockerPassPrice = 2000;
        double discountRateSeatPass = 0.1d;

        StudyCafePassOrder studyCafePassOrder = StudyCafePassOrder.of(
            StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 1000, discountRateSeatPass),
            StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 2000)
        );

        // when
        int totalPrice = studyCafePassOrder.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(seatPassPrice + lockerPassPrice - studyCafePassOrder.getDiscountPrice());
    }
}
