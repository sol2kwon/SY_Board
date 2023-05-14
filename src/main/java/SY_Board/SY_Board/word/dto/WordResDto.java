package SY_Board.SY_Board.word.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class WordResDto {
    private String word;
    private Integer frequency;
}
