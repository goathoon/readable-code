package cleancode.studycafe.mine;

import cleancode.studycafe.mine.exception.AppException;
import cleancode.studycafe.mine.io.InputHandler;
import cleancode.studycafe.mine.io.OutputHandler;
import cleancode.studycafe.mine.io.StudyCafeFileHandler;
import cleancode.studycafe.mine.model.StudyCafeLockerPass;
import cleancode.studycafe.mine.model.StudyCafePass;
import cleancode.studycafe.mine.model.StudyCafePassType;
import cleancode.studycafe.mine.pass.LockerPasses;
import cleancode.studycafe.mine.pass.StudyCafePasses;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();
            outputHandler.askPassTypeSelection();

            // 중복로직 공통으로 빼기
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();
            // fileHandler에서 parse까지 하는 역할은 과할까?
            StudyCafePasses allStudyCafePasses = studyCafeFileHandler.readStudyCafePasses();
            StudyCafePasses filteredStudyCafePasses = allStudyCafePasses.filterBy(studyCafePassType);

            outputHandler.showPassListForSelection(filteredStudyCafePasses);
            StudyCafePass selectedPass = inputHandler.getSelectPass(filteredStudyCafePasses);

            if (studyCafePassType == StudyCafePassType.HOURLY) {
                outputHandler.showPassOrderSummary(selectedPass, null);
            } else if (studyCafePassType == StudyCafePassType.WEEKLY) {
                outputHandler.showPassOrderSummary(selectedPass, null);
            } else if (studyCafePassType == StudyCafePassType.FIXED) {
                LockerPasses lockerPasses = studyCafeFileHandler.readLockerPasses();
                Optional<StudyCafeLockerPass> optionalCandidateLockerPass = lockerPasses.filterMatchingBy(selectedPass);

                boolean lockerSelection = false;
                if(optionalCandidateLockerPass.isPresent()) {
                    StudyCafeLockerPass candidateLockerPass = optionalCandidateLockerPass.get();
                    outputHandler.askLockerPass(candidateLockerPass);
                    lockerSelection = doesUserSelectLocker(candidateLockerPass);
                }

                if (lockerSelection) {
                    outputHandler.showPassOrderSummary(selectedPass, optionalCandidateLockerPass.get());
                } else {
                    outputHandler.showPassOrderSummary(selectedPass, null);
                }
            }
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private boolean doesUserSelectLocker(StudyCafeLockerPass lockerPass) {
        return inputHandler.getLockerSelection();
    }

}
