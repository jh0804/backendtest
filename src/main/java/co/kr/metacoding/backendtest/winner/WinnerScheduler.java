package co.kr.metacoding.backendtest.winner;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WinnerScheduler {
    private final WinnerService winnerService;

    @Scheduled(cron = "0 0 0 * * SUN") // 매주 일요일 0시
    public void runScheduler() {
        winnerService.checkWinners();
    }
}
