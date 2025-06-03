package co.kr.metacoding.backendtest.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> save(@RequestBody UserRequest.SaveDTO reqDTO) {
        UserResponse.SaveDTO respDTO = userService.save(reqDTO);
        return ResponseEntity.ok(respDTO);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getDetail(@PathVariable("id") Integer id) {
        UserResponse.DTO respDTO = userService.getDetail(id);
        return ResponseEntity.ok(respDTO);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody UserRequest.UpdateDTO reqDTO){
        UserResponse.DTO respDTO = userService.update(id, reqDTO);
        return ResponseEntity.ok(respDTO);
    }
}
