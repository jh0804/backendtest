package co.kr.metacoding.backendtest.lotto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "lotto_tb")
@Entity
public class Lotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number_1;
    private Integer number_2;
    private Integer number_3;
    private Integer number_4;
    private Integer number_5;
    private Integer number_6;

    @Builder
    public Lotto(Long id, Integer number_1, Integer number_2, Integer number_3, Integer number_4, Integer number_5, Integer number_6) {
        this.id = id;
        this.number_1 = number_1;
        this.number_2 = number_2;
        this.number_3 = number_3;
        this.number_4 = number_4;
        this.number_5 = number_5;
        this.number_6 = number_6;
    }
}
