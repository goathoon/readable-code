package cleancode.mission.day4;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day4Main {
    public boolean validateOrder(Order order) {
        if (order.doesNotIncludeItem()) {
            log.info("주문 항목이 없습니다.");
            return false;
        }

        if (order.isNotValidTotalPrice()) {
            log.info("올바르지 않은 총 가격입니다.");
            return false;
        }

        if(order.hasNotAnyCustomerInfo()) {
            log.info("사용자 정보가 없습니다.");
            return false;
        }

        return true;
    }


    /* 이전 코드
    public boolean validateOrder(Order order) {
        if (order.getItems().size() == 0) {
            log.info("주문 항목이 없습니다.");
            return false;
        } else {
            if (order.getTotalPrice() > 0) {
                if (!order.hasCustomerInfo()) {
                    log.info("사용자 정보가 없습니다.");
                    return false;
                } else {
                    return true;
                }
            } else if (!(order.getTotalPrice() > 0)) {
                log.info("올바르지 않은 총 가격입니다.");
                return false;
            }
        }
        return true;
    }
     */
}
