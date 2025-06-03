package co.kr.metacoding.backendtest.lotto;

import co.kr.metacoding.backendtest.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(LottoRepository.class)
@DataJpaTest
public class LottoRepositoryTest {
    @Autowired
    private LottoRepository lottoRepository;

    @Test
    public void findByName_test(){
        // given
        Integer number_1 = 1;
        Integer number_2 = 2;
        Integer number_3 = 3;
        Integer number_4 = 4;
        Integer number_5 = 5;
        Integer number_6 = 6;

        Lotto lotto = Lotto.builder()
                .number_1(number_1)
                .number_2(number_2)
                .number_3(number_3)
                .number_4(number_4)
                .number_5(number_5)
                .number_6(number_6)
                .build();

        // when
        Lotto lottoPS = lottoRepository.save(lotto);

        // eye
        System.out.println(lottoPS.getId());
        System.out.println(lottoPS.getNumber_1());
        System.out.println(lottoPS.getNumber_2());
        System.out.println(lottoPS.getNumber_3());
        System.out.println(lottoPS.getNumber_4());
        System.out.println(lottoPS.getNumber_5());
        System.out.println(lottoPS.getNumber_6());
    }
}
