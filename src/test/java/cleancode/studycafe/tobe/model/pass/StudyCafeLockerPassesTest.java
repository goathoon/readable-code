package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeLockerPassesTest {

    @DisplayName("Locker Pass는 Seat Pass와 동일한 이용기간과 타입인 경우에만 이용 가능하다")
    @Test
    void StudyCafeLockerPassesTest() {
        // given
        StudyCafeLockerPasses lockerPasses = StudyCafeLockerPasses.of(List.of(
            StudyCafeLockerPass.of(StudyCafePassType.FIXED,4,0 ),
            StudyCafeLockerPass.of(StudyCafePassType.FIXED,7,0 )
        ));

        // when // then
        assertThat(lockerPasses.findLockerPassBy(StudyCafeSeatPass.of(StudyCafePassType.FIXED,4, 0, 0)))
            .isPresent();
        assertThat(lockerPasses.findLockerPassBy(StudyCafeSeatPass.of(StudyCafePassType.FIXED,5, 0, 0)))
            .isEmpty();
    }
}
