package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudyCafePassTypeTest {

    @DisplayName("시간, 주단위 이용권 타입은 락커를 사용할 수 없다.")
    @Test
    void HourlyAndWeeklyCannotUserLockerTypeExceptFixed() {
        // given
        StudyCafePassType hourlyType = StudyCafePassType.HOURLY;
        StudyCafePassType weeklyType = StudyCafePassType.WEEKLY;
        StudyCafePassType fixedType = StudyCafePassType.FIXED;

        // when // then
        assertThat(hourlyType.isNotLockerType()).isTrue();
        assertThat(weeklyType.isNotLockerType()).isTrue();
        assertThat(fixedType.isLockerType()).isTrue();

    }
}
