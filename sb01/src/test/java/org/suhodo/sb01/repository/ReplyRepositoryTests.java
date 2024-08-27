package org.suhodo.sb01.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.suhodo.sb01.domain.Board;
import org.suhodo.sb01.domain.Reply;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsert(){
        Long bno = 101L;

        Board board = Board.builder()
                        .bno(bno)
                        .build();

        // bno가 100인 게시글의 자식으로 댓글을 추가
        Reply reply = Reply.builder()
                .board(board)
                .replyText("댓글......")
                .replyer("replyer1")
                .build();

        replyRepository.save(reply);
    }
}
