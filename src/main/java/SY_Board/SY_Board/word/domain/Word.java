package SY_Board.SY_Board.word.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "word")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Word {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "word_id")
    private Long wordId;

    @Column(name = "word")
    private String word;

    @Column(name = "frequency")
    private Integer frequency;

    @Column(name = "fr")
    private double fr;

}
