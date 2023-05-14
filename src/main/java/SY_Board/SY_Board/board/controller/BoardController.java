package SY_Board.SY_Board.board.controller;

import SY_Board.SY_Board.board.domain.Board;
import SY_Board.SY_Board.board.dto.BoardListAllResponseDto;
import SY_Board.SY_Board.board.dto.BoardRequestDto;
import SY_Board.SY_Board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;


    @PostMapping("/save")
    public void save(@Valid @RequestBody BoardRequestDto board) {
        log.info("컨트롤러{}",board);
        boardService.save(board);
    }
    @GetMapping("/boards")
    public List<BoardListAllResponseDto> boardListAll() {
        log.info("컨트롤러 boardListAll{}");
       return boardService.boardListAll();
    }

}
