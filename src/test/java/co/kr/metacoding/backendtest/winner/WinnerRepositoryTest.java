package co.kr.metacoding.backendtest.winner;

import co.kr.metacoding.backendtest.lotto.Lotto;
import co.kr.metacoding.backendtest.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(WinnerRepository.class)
@DataJpaTest
public class WinnerRepositoryTest {

    @Autowired
    private WinnerRepository winnerRepository;

    @Test
    public void save_test(){
        // given
        Long lottoId = 1L;
        Integer number_1 = 1;
        Integer number_2 = 2;
        Integer number_3 = 3;
        Integer number_4 = 4;
        Integer number_5 = 5;
        Integer number_6 = 6;

        Lotto lotto = Lotto.builder()
                .id(lottoId)
                .number_1(number_1)
                .number_2(number_2)
                .number_3(number_3)
                .number_4(number_4)
                .number_5(number_5)
                .number_6(number_6)
                .build();

        Integer rank = 1;
        Winner winner = Winner.builder()
                .lotto(lotto)
                .rank(rank)
                .build();

        // when
        winnerRepository.save(winner);

        // eye
        System.out.println(winner.getId());
        System.out.println(winner.getLotto().getId());
        System.out.println(winner.getRank());
    }
}
