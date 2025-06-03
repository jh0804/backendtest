package co.kr.metacoding.backendtest.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO reqDTO) {
        UserResponse.JoinDTO respDTO = userService.join(reqDTO);
        return ResponseEntity.ok(respDTO);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getDetail(@PathVariable("id") Integer id) {
        UserResponse.DTO respDTO = userService.getDetail(id);
        return ResponseEntity.ok(respDTO);
    }
}
