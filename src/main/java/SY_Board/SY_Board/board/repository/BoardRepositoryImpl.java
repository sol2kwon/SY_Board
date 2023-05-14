package SY_Board.SY_Board.board.repository;

import SY_Board.SY_Board.board.domain.Board;
import SY_Board.SY_Board.board.domain.QBoard;
import SY_Board.SY_Board.board.dto.BoardListAllResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BoardRepositoryImpl {
    private final JPAQueryFactory queryFactory;
    public List<Board> boardListAll(){

        return queryFactory
                .selectFrom(QBoard.board)
                .orderBy(QBoard.board.boardId.asc())
                .limit(10)
                .fetch();
    }

    public List<String> boardText(){

        return queryFactory
                .select(QBoard.board.content)
                .from(QBoard.board)
                .fetch();
    }
}
