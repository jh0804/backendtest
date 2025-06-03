package co.kr.metacoding.backendtest.lotto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

public class LottoResponse {

    @Data
    public static class SaveDTO {
        Set<Integer> numbers;

        public SaveDTO(Lotto lotto) {
            this.numbers = new HashSet<>();
            numbers.add(lotto.getNumber_1());
            numbers.add(lotto.getNumber_2());
            numbers.add(lotto.getNumber_3());
            numbers.add(lotto.getNumber_4());
            numbers.add(lotto.getNumber_5());
            numbers.add(lotto.getNumber_6());
        }
    }
}
