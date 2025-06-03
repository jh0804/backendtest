package co.kr.metacoding.backendtest.user;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserRepository.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    // name 존재 여부 test
    @Test
    public void findByName_test(){
        // given
        String name = "ssar";

        // when
        Optional<User> userOP = userRepository.findByName(name);

        // eye
        System.out.println(userOP.get().getId());
        System.out.println(userOP.get().getName());
    }

    // 유저 등록 test
    @Test
    public void save_test(){
        // given
        String name = "haha";
        User user = User.builder()
                .name(name)
                .build();

        // when
        userRepository.save(user);

        // eye
        System.out.println(user.getId());
    }

    // 유저 id로 조회 test
    @Test
    public void findById_test(){
        // given
        Integer id = 1;

        // when
        Optional<User> userOP = userRepository.findById(id);

        // eye
        System.out.println(userOP.get().getId());
        System.out.println(userOP.get().getName());
    }

}
