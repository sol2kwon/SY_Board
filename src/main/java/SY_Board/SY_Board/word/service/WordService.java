package SY_Board.SY_Board.word.service;

import SY_Board.SY_Board.word.domain.Word;

import java.util.List;
import java.util.Map;

public interface WordService {
    Long count();

    void createWord(Map<String, Integer> map);

    void createMapWord(Map<String, Integer> map, List<String> wordList, List<String> words);

}
