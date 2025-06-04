package co.kr.metacoding.backendtest.winner;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class WinnerRepository {
    private final EntityManager em;

    public void save(Winner winner) {
        em.persist(winner);
    }
}
