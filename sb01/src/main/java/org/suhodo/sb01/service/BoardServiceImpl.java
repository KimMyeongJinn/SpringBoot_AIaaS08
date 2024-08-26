package org.suhodo.sb01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.suhodo.sb01.dto.BoardDTO;
import org.suhodo.sb01.repository.BoardRepository;

import javax.transaction.Transactional;

/*
 bean을 주입받을 때
 1) @Autowired
 2) 생성자 주입 방식
   @RequiredArgsConstructor
   주입받을 클래스 변수를 반드시 private final로 선언해야 한다.
   private final BoardRepository boardRepository;
* */

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;


    @Override
    public Long register(BoardDTO boardDTO) {
        return 0;
    }
}









