package co.kr.metacoding.backendtest.lotto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LottoController {
    private final LottoService lottoService;

    @PostMapping("/lottos")
    public ResponseEntity<?> save (){
        LottoResponse.SaveDTO respDTO = lottoService.save();
        return ResponseEntity.ok(respDTO);
    }
}
