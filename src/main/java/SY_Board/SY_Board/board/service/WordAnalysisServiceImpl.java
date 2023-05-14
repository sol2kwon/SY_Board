package SY_Board.SY_Board.board.service;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;

import kr.co.shineware.nlp.komoran.model.KomoranResult;


import org.springframework.stereotype.Service;


import java.util.*;
import java.util.logging.Logger;

@Service("WordAnalysisServiceImpl")
public class WordAnalysisServiceImpl implements WordAnalysisService{

    private Logger log = Logger.getLogger(String.valueOf(this.getClass()));

    //자연어 처리 - 형태소 분석기인 Komoran를 메모리에 올리기 위해 WordAnalysisService 클래스 내 전역 변수로 설정합니다.
    Komoran nlp = null;

    public WordAnalysisServiceImpl() {

        log.info(this.getClass().getName() + ".WordAnalysisService creator Start !");

        //NLP 분석 객체 메모리 로딩합니다.
        this.nlp = new Komoran(DEFAULT_MODEL.LIGHT); // 학습데이터 경량화 버전
        //this.nlp = new Komoran(DEFAULT_MODEL.FULL); // 학습데이터 전체 버전

        log.info("톰캣 -> 스프링부트, 스프링 실행될 때 nlp 변수에 Komoran 객체를 생성하여 저장.");

        log.info(this.getClass().getName() + ".WordAnalysisService creator End !");

    }


    @Override
    public List<String> doWordNouns(String text) throws Exception {
        log.info(this.getClass().getName() + ".doWordAnalysis Start !");

        log.info("분석할 문장 : " + text);

        //분석할 문장에 대해 정제(쓸데없는 특수문자 제거)
        String replace_text = text.replace("[^가-힣a-zA-Z0-9", " ");

        log.info("한국어, 영어, 숫자 제외 단어 모두 한 칸으로 변환시킨 문장 : " + replace_text);

        //분석할 문장의 앞, 뒤에 존재할 수 있는 필요없는 공백 제거
        String trim_text = replace_text.trim();

        log.info("분석할 문장 앞, 뒤에 존재할 수 있는 필요 없는 공백 제거 : " + trim_text);

        //형태소 분석 시작
        KomoranResult analyzeResultList = this.nlp.analyze(trim_text);

        //형태소 분석 결과 중 명삼나 가져오기
        List<String> rList = analyzeResultList.getNouns();

        if (rList == null) {
            rList = new ArrayList<String>();
        }

        //분석 결과 확인을 위한 로그 찍기
        Iterator<String> it = rList.iterator();

        while (it.hasNext()) {
            //추출된 명서
            String word = it.next();
        }


        log.info(this.getClass().getName() + ".doWordAnalysis End !");

        return rList;
    }

    @Override
    public Map<String, Integer> doWordCount(List<String> pList) throws Exception {

        log.info(this.getClass().getName() + ".doWordCount Start !");

        if (pList ==null) {
            pList = new ArrayList<String>();
        }

        //단어 빈도수(사과, 3) 결과를 저장하기 위해 Map객체 생성합니다.
        Map<String, Integer> rMap = new HashMap<>();

        //List에 존재하는 중복되는 단어들의 중복제거를 위해 set 데이터타입에 데이터를 저장합니다.
        //rSet 변수는 중복된 데이터가 저장되지 않기 떄문에 중복되지 않은 단어만 저장하고 나머지는 자동 삭제합니다.
        Set<String> rSet = new HashSet<String>(pList);

        //중복이 제거된 단어 모음에 빈도수를 구하기 위해 반복문을 사용합니다.
        Iterator<String> it = rSet.iterator();

        while(it.hasNext()) {
            //중복 제거된 단어
//            String word = CmmUtil.nvl(it.next());
            String word = it.next();
            //단어가 중복 저장되어 있는 pList로부터 단어의 빈도수 가져오기
            int frequency = Collections.frequency(pList, word);

            log.info("word :" + word);
            log.info("frequency : " + frequency);

            rMap.put(word, frequency);
        }

        log.info(this.getClass().getName() + ".doWordCount End !");

        return rMap;
    }

    @Override
    public Map<String, Integer> doWordAnalysis(String text) throws Exception {

        //문장의 명사를 추출하기 위한 형태소 분석 실행
        List<String> rList = this.doWordNouns(text);

        if(rList == null) {
            rList = new ArrayList<String>();
        }

        //추출된 명사 모음(리스트)의 명사 단어별 빈도수 계산
        Map<String, Integer> rMap = this.doWordCount(rList);
        for(String data : rMap.keySet()) {
            System.out.println(data + ":" + rMap.get(data) );
        }

        if(rMap == null) {
            rMap = new HashMap<String, Integer>();
        }

        return rMap;
    }

}
