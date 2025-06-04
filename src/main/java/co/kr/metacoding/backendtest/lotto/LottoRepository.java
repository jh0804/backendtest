package co.kr.metacoding.backendtest.lotto;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class LottoRepository {
    private final EntityManager em;

    public Lotto save(Lotto lotto) {
        em.persist(lotto);
        return lotto;
    }

    public List<Lotto> findAll() {

    }
}
