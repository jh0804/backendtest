package co.kr.metacoding.backendtest.user;

import co.kr.metacoding.backendtest.core.error.ex.ExceptionApi400;
import co.kr.metacoding.backendtest.core.error.ex.ExceptionApi404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse.JoinDTO join(UserRequest.JoinDTO reqDTO) {
        // 이미 존재하는 name인지 검사
        Optional<User> userOP = userRepository.findByName(reqDTO.getName());
        if(userOP.isPresent()) throw new ExceptionApi400("중복된 유저네임이 존재합니다.");
        
        // 유저 등록
        User userPS = userRepository.save(reqDTO.toEntity());

        return new UserResponse.JoinDTO(userPS);
    }

    public UserResponse.DTO getDetail(Integer id) {
        User userOP = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionApi404("해당 유저를 찾을 수 없습니다"));
        return new UserResponse.DTO(userOP);
    }
}
