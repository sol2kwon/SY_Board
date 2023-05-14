package SY_Board.SY_Board.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class BoardListAllResponseDto {

    private Long boardId;

    private String title;

    private String content;

    private LocalDateTime boardDate;

    private String correlationBoard;

}
