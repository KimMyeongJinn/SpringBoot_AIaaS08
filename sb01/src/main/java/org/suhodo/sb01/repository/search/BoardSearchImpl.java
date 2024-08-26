package org.suhodo.sb01.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.suhodo.sb01.domain.Board;
import org.suhodo.sb01.domain.QBoard;

import java.util.List;

/* BoardSearch인터페이스를 구현하는 클래스로
반드시 '인터페이스명' + Impl이어야 한다.

QuerydslRepositorySupport는 JpaRepository를 상속받은
repository와 연결해주는 역할을 한다.

[QueryDsl을 사용하기 위해 해야하는 일]
0)전단계
 Gradle에서 clean -> compileJava
 build폴더에 QBoard가 생성되었는지 확인
1) 인터페이스 선언
2) 인터페이스 자식 클래스 선언 (반드시 '인터페이스명' + Impl)
3) 인터페이스 자식 클래스는 extends QuerydslRepositorySupport 해야 한다.
4) 인터페이스 자식 클래스의 생성자를 통해 어떤 Entity클래스를 사용할 것인지 적용
5) QueryDsl 구현 메서드가 호출될 Repository에  인터페이스를 상속시켜야 한다.
* */

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);       // select.. from board

        query.where(board.title.contains("1"));     // where title like ...

        this.getQuerydsl().applyPagination(pageable, query); // ORDER BY bno DESC limit 1, 10;

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        log.info(list);
        log.info("count=" + count);

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);       // select... from board

        if((types != null) && (types.length > 0) && keyword != null) {
            // and나 or로 여러 개 조건을 만들 때 사용
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            /*
            title LIKE '%1%'
            OR content LIKE '%1%'
            OR writer LIKE '%1%'
            * */

            for(String type : types){
                switch(type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }
            /*
            WHERE (
                    title LIKE '%1%'
                    OR content LIKE '%1%'
                    OR writer LIKE '%1%'
            )
            * */
            query.where(booleanBuilder);            // WHERE (...)
        }
        query.where(board.bno.gt(0L));          // AND bno > 0L

        this.getQuerydsl().applyPagination(pageable, query);    // ORDER BY bno DESC limit 1, 10;

        /*
        SELECT *
         FROM board
         WHERE (
                title LIKE '%1%'
                OR content LIKE '%1%'
                OR writer LIKE '%1%'
         )
         AND bno > 0L
         ORDER BY bno DESC limit 1, 10;
        * */
        List<Board> list = query.fetch();


        /*
        SELECT COUNT(bno)
         FROM board
         WHERE (
                title LIKE '%1%'
                OR content LIKE '%1%'
                OR writer LIKE '%1%'
         )
         AND bno > 0L
         ORDER BY bno DESC limit 1, 10;
        * */
        long count = query.fetchCount();

        /*
        * JPA는 클라이언트에 Pagination기능을 제공하기 위해 Page<E>를 상속받은 PageImpl<E>를 만들어놨다.
        * PageImpl(실제 목록 데이터, 페이지 관련 정보 객체, 전체 개수);
        * */
        return new PageImpl<Board>(list, pageable, count);
    }
}














