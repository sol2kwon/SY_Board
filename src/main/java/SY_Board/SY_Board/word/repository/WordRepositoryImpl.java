package SY_Board.SY_Board.word.repository;

import SY_Board.SY_Board.word.domain.QWord;
import SY_Board.SY_Board.word.dto.WordResDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class WordRepositoryImpl {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public List<String> wordList (List<String> words){
        return queryFactory
                .select(QWord.word1.word)
                .from(QWord.word1)
                .where(QWord.word1.word.in(words).and(QWord.word1.fr.loe(40)))
                .fetch();
    }

    public void updateWord (List<WordResDto> wordDto,int total, List<Integer> listFrequency){
        List<String> dtoWord = new ArrayList<>();
        List<Integer> fr = new ArrayList<>();
        int add = 0;
        for (int i: listFrequency) {
            add += i;
        }

        for (WordResDto s:wordDto) {
            dtoWord.add(s.getWord());
            fr.add(s.getFrequency();

        queryFactory.
                update(QWord.word1)
                .set(QWord.word1.frequency,s.getFrequency())
                .set(QWord.word1.fr,(add+s.getFrequency().intValue()) /total * 100)
                .where(QWord.word1.word.in(dtoWord))
                .execute();
        }

        em.clear();
        em.flush();

    }
    public Integer sumFrequency () {

        return  queryFactory
                .select(QWord.word1.frequency.sum())
                .from(QWord.word1)
                .fetchOne();
    }
    public List<WordResDto> selectFrequency (List<String> words) {

        return  queryFactory
                .select(Projections.constructor(WordResDto.class,
                        QWord.word1.word,
                        QWord.word1.frequency))
                .from(QWord.word1)
                .where(QWord.word1.word.in(words))
                .fetch();
    }


}
