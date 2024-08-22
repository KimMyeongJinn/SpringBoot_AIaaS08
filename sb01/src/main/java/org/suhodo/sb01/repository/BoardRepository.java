package org.suhodo.sb01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhodo.sb01.domain.Board;

/* JpaRepository<Board, Long>를 상속받은 interface를 선언하면
자동으로 jpa에 의해 Bean으로 생성되어 Spring의 관리를 받게 된다.
Board : 어떤 Entity를 crud할래
Long : @Id, pk의 자료형
* */
public interface BoardRepository extends JpaRepository<Board, Long> {
}
