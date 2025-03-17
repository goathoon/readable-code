package cleancode.studycafe.tobe.io.provider;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SeatPassFileReaderTest {

    /**
     * SeatPassFileReader에서 읽어오는 csv의 Path가 박혀있어서.. Path경로에 파일이 없는 경우에 테스트는 하지 못했다.
     * 만약 인자로 받아오거나 Mocking을 사용한다면 받아올 수 있을 것 같다.
     */
    @DisplayName("Seat Pass가 나열된 CSV 파일 전체를 읽으면 주어진 Seat Pass의 개수만큼 이용권이 등록된다.")
    @Test
    void wrongPassListCsvPath() {
        // given
        SeatPassFileReader seatPassFileReader = new SeatPassFileReader();
        int numberOfTypesInCsv = 13;

        // when
        StudyCafeSeatPasses seatPasses = seatPassFileReader.getSeatPasses();

        // then
        int countOfFixedTypes = seatPasses.findPassBy(StudyCafePassType.FIXED).size();
        int countOfHourlyTypes = seatPasses.findPassBy(StudyCafePassType.HOURLY).size();
        int countOfWeeklyTypes = seatPasses.findPassBy(StudyCafePassType.WEEKLY).size();

        int countOfAllTypes = countOfFixedTypes + countOfHourlyTypes + countOfWeeklyTypes;
        assertThat(countOfAllTypes).isEqualTo(numberOfTypesInCsv);

    }
}
