package co.kr.metacoding.backendtest.winner;

import co.kr.metacoding.backendtest.lotto.Lotto;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Table(name = "winner_tb")
@Entity
public class Winner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Lotto lotto;
    private Integer rank;

    @Builder
    public Winner(Long id, Lotto lotto, Integer rank) {
        this.id = id;
        this.lotto = lotto;
        this.rank = rank;
    }
}
