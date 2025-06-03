package co.kr.metacoding.backendtest.lotto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class LottoService {
    private final LottoRepository lottoRepository;


    @Transactional
    public LottoResponse.SaveDTO save() {
        Set<Integer> numberSet = new HashSet<>();

        Random random = new Random();

        while (numberSet.size() < 6) {
            numberSet.add(random.nextInt(45) + 1);
        }

        // List로 변환 및 정렬해서 db 저장
        List<Integer> sorted = new ArrayList<>(numberSet);
        Collections.sort(sorted);

        Lotto lotto = Lotto.builder()
                .number_1(sorted.get(0))
                .number_2(sorted.get(1))
                .number_3(sorted.get(2))
                .number_4(sorted.get(3))
                .number_5(sorted.get(4))
                .number_6(sorted.get(5))
                .build();

        Lotto lottoPS = lottoRepository.save(lotto);

        return new LottoResponse.SaveDTO(lottoPS);
    }
}
