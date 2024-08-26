package org.suhodo.sb01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.suhodo.sb01.domain.Board;
import org.suhodo.sb01.dto.BoardDTO;
import org.suhodo.sb01.dto.PageRequestDTO;
import org.suhodo.sb01.dto.PageResponseDTO;
import org.suhodo.sb01.repository.BoardRepository;

import javax.transaction.Transactional;
import java.util.Optional;

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

    private final ModelMapper modelMapper;

    private final BoardRepository boardRepository;


    @Override
    public Long register(BoardDTO boardDTO) {

        Board board = modelMapper.map(boardDTO, Board.class);

        Long bno = boardRepository.save(board).getBno();

        return bno;
    }

    @Override
    public BoardDTO readOne(Long bno) {
        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);

        return boardDTO;
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        Optional<Board> result = boardRepository.findById(boardDTO.getBno());

        Board board = result.orElseThrow();

        board.change(boardDTO.getTitle(), boardDTO.getContent());

        boardRepository.save(board);
    }

    @Override
    public void remove(Long bno) {
        boardRepository.deleteById(bno);
    }

    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
        return null;
    }
}









