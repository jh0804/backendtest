package co.kr.metacoding.backendtest.winner;

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
    private Long lottoId;
    private Integer rank;
}
