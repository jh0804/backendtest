package co.kr.metacoding.backendtest.winner;

import co.kr.metacoding.backendtest.lotto.Lotto;
import co.kr.metacoding.backendtest.lotto.LottoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class WinnerService {
    private final WinnerRepository winnerRepository;
    private final LottoRepository lottoRepository;

    @Transactional
    public void checkWinners(){
        // 당첨 로또 번호
        Set<Integer> winningNumbers = new HashSet<>();
        Random random = new Random();
        while (winningNumbers.size() < 6){
            winningNumbers.add(random.nextInt(45) + 1); // 1~45
        }

        // 전체 로또 조회
        List<Lotto> lottoList = lottoRepository.findAll();

        // 로또 for문 돌면서 당첨 검사
        for (Lotto lotto : lottoList) {
            List<Integer> numbers = Arrays.asList(
                    lotto.getNumber_1(), lotto.getNumber_2(), lotto.getNumber_3(),
                    lotto.getNumber_4(), lotto.getNumber_5(), lotto.getNumber_6()
            );

            long matchCount = numbers.stream()
                    .filter(winningNumbers::contains)
                    .count();

            int rank = getRankByMatchCount((int) matchCount);
            if (rank > 0) {
                Winner winner = Winner.builder()
                        .lotto(lotto)
                        .rank(rank)
                        .build();
                winnerRepository.save(winner);
            }
        }
    }

    private int getRankByMatchCount(int count) {
        return switch (count) {
            case 6 -> 1;
            case 5 -> 2;
            case 4 -> 3;
            case 3 -> 4;
            case 2 -> 5;
            default -> 0;
        };
    }

}
