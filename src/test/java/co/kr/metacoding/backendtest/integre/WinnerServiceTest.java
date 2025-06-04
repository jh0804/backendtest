package co.kr.metacoding.backendtest.integre;

import co.kr.metacoding.backendtest.lotto.Lotto;
import co.kr.metacoding.backendtest.lotto.LottoRepository;
import co.kr.metacoding.backendtest.winner.Winner;
import co.kr.metacoding.backendtest.winner.WinnerRepository;
import co.kr.metacoding.backendtest.winner.WinnerService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class WinnerServiceTest {

    @Autowired
    private WinnerService winnerService;

    @Autowired
    private LottoRepository lottoRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setUp(){
        // 테스트 시작 전에 실행할 코드
    }

    @AfterEach
    public void tearDown(){
        // 테스트 후 정리할 코드
    }

    @Test
    public void check_winners_test(){
        // given
        lottoRepository.save(Lotto.builder().number_1(1).number_2(2).number_3(3).number_4(4).number_5(5).number_6(6).build());
        lottoRepository.save(Lotto.builder().number_1(7).number_2(8).number_3(9).number_4(10).number_5(11).number_6(12).build());
        lottoRepository.save(Lotto.builder().number_1(13).number_2(14).number_3(15).number_4(16).number_5(17).number_6(18).build());

        // when
        winnerService.checkWinners();

        // eye
        List<Winner> winners = em.createQuery("SELECT w FROM Winner w", Winner.class).getResultList();
        System.out.println("당첨자 수: " + winners.size());
        for (Winner winner : winners) {
            System.out.println(" 당첨자 ID: " + winner.getId());
            System.out.println(" 당첨 로또 ID: " + winner.getLotto().getId());
            System.out.println(" 당첨 등수: " + winner.getRank());
        }
        // then : 당첨자 0명 이상 & 등수 1~5 사이
        assertThat(winners.size()).isGreaterThanOrEqualTo(0);
        assertThat(winners).allMatch(w -> w.getRank() >= 1 && w.getRank() <= 5);
    }

}
