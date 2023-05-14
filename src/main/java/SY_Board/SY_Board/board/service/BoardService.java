package SY_Board.SY_Board.board.service;

import SY_Board.SY_Board.board.domain.Board;
import SY_Board.SY_Board.board.dto.BoardListAllResponseDto;
import SY_Board.SY_Board.board.dto.BoardRequestDto;

import java.util.List;

public interface BoardService  {
    void save(BoardRequestDto board);
    List<BoardListAllResponseDto> boardListAll();


}
