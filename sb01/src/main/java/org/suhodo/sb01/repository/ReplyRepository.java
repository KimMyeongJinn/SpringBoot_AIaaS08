package org.suhodo.sb01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhodo.sb01.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
