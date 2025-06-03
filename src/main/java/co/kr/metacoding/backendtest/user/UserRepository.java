package co.kr.metacoding.backendtest.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public Optional<User> findByName(String name) {
        try {
            User userPS = em.createQuery("select u from User u where u.name = :name", User.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return Optional.of(userPS);
        } catch (Exception e) {
            return Optional.ofNullable(null);
        }
    }

    public User save(User user) {
        em.persist(user);
        return user;
    }
}
