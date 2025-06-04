package co.kr.metacoding.backendtest.winner;

import co.kr.metacoding.backendtest.lotto.Lotto;
import co.kr.metacoding.backendtest.lotto.LottoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class WinnerService {
    private final WinnerRepository winnerRepository;
    private final LottoRepository lottoRepository;

    public void checkWinners(){
        // 당첨 로또 번호
        Set<Integer> winningNumbers = new HashSet<>();
        Random random = new Random();
        while (winningNumbers.size() < 6){
            winningNumbers.add(random.nextInt(45) + 1); // 1~45
        }

        //
        List<Lotto> allLottos = lottoRepository.findAll();


    }

}
