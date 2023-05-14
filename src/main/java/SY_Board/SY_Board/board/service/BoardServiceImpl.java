package SY_Board.SY_Board.board.service;

import SY_Board.SY_Board.board.domain.Board;
import SY_Board.SY_Board.board.dto.BoardListAllResponseDto;
import SY_Board.SY_Board.board.dto.BoardRequestDto;
import SY_Board.SY_Board.board.repository.BoardRepository;
import SY_Board.SY_Board.board.repository.BoardRepositoryImpl;
import SY_Board.SY_Board.word.repository.WordRepository;
import SY_Board.SY_Board.word.repository.WordRepositoryImpl;
import SY_Board.SY_Board.word.service.WordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardRepositoryImpl boardRepositoryImpl;
    private final WordAnalysisService wordAnalysisService;
    private final WordService wordService;
    private final WordRepository wordRepository;
    private final WordRepositoryImpl wordRepositoryImpl;

    @Override
    public void save(BoardRequestDto board)  {
        //Word 테이블 단어 데이터 확인
        Long wordCnt = wordRepository.count();
        String text = "";

        try{
            //받아온 데이터 전처리
            Map<String, Integer> map = wordAnalysisService.doWordAnalysis(board.getContent());
            //Word 테이블이 카운트가 0이면 사용자가 작성한 내용 중 단어 정보 저장
            if (wordCnt == 0){

                wordService.createWord(map);
//
            } else {
                //1. 받아온 map에서 word 테이블과 공통되지 않는 단어 정보를 word 테이블에 저장
                List<String> words = new ArrayList<>(map.keySet());
                List<String> wordList = wordRepositoryImpl.wordList(words);

                wordService.createMapWord(map,wordList,words);
            }

//            List<String> boardText = boardRepositoryImpl.boardText();
//
//            for (String s: boardText) {
//               text += String.join(",",s);
//            }
//
//            Map<String, Integer> boardTextTotal = wordAnalysisService.doWordAnalysis(text);

        }catch (Exception e) {
            //에러시 수행
            e.printStackTrace();
        }

        Board createBoard = Board.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .boardDate(LocalDateTime.now())
                .build();

        boardRepository.save(createBoard);
    }

    @Override
    public List<BoardListAllResponseDto> boardListAll() {
        List<Board> boards = boardRepositoryImpl.boardListAll();
        List<BoardListAllResponseDto> returnList = new ArrayList<>();

        for (Board b : boards) {

            BoardListAllResponseDto list = BoardListAllResponseDto.builder()
                    .boardId(b.getBoardId())
                    .title(b.getTitle())
                    .content(b.getContent())
                    .boardDate(b.getBoardDate())
                    .build();

            returnList.add(list);
        }
        return returnList;

    }

    public double totalFr (Integer cnt, Integer totalCnt){
        return cnt / totalCnt * 100;

    }





}
