package SY_Board.SY_Board.word.service;

import SY_Board.SY_Board.word.domain.Word;
import SY_Board.SY_Board.word.dto.WordResDto;
import SY_Board.SY_Board.word.repository.WordRepository;
import SY_Board.SY_Board.word.repository.WordRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class WordServiceImpl implements WordService{
    private final WordRepository wordRepository;
    private final WordRepositoryImpl wordRepositoryImpl;

    @Override
    public Long count() {
        return wordRepository.count();
    }

    @Override
    public void createWord(Map<String, Integer> map){
        int total = map.values().stream().mapToInt(Integer::intValue).sum();

        map.forEach((key, value) -> {
            Word initWord = Word.builder()
                    .word(key)
                    .frequency(value)
                    .fr((value.doubleValue()/total)*100)
                    .build();

            wordRepository.save(initWord);
        });
    }
    @Override
    public void createMapWord(Map<String, Integer> map, List<String> wordList,List<String> words){
        List<String> listWord = new ArrayList<>();
        List<Integer> listFrequency = new ArrayList<>();
//        int total = map.values().stream().mapToInt(Integer::intValue).sum();
        int total = wordRepositoryImpl.sumFrequency();

        //사용자가 전처리한 단어
//        val + dto. / total) * 100
            map.forEach((key, value) -> {
               for (int i = 0; i < wordList.size(); i++) {
                   if (map.containsKey(wordList.get(i))) {
                       listWord.add(key);
                       listFrequency.add(value);
                       // 동일한게 있으면, update
//                     wordRepositoryImpl.updateWord(key,value,total,wordDto);

                       } else {
                       //동일 한게 없으면 저장
                       Word initWord = Word.builder()
                                        .word(key)
                                        .frequency(value)
                                        .fr((value.doubleValue() / total) * 100)
                                        .build();

                                wordRepository.save(initWord);
                   }
                }
            });

       if(listWord.size() >0){
        List<WordResDto> wordDto = wordRepositoryImpl.selectFrequency(listWord);
           wordRepositoryImpl.updateWord(wordDto,total,listFrequency);

       }
            }
        }

