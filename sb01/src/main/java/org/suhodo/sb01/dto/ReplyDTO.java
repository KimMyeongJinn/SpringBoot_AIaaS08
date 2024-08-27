package org.suhodo.sb01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
    private Long rno;                           // 댓글 번호(자식)
    private Long bno;                           // 게시판 글 번호(부모)
    private String replyText;                   // 댓글
    private String replyer;                     // 댓글 작성자
    private LocalDateTime regDate, modDate;     // 처음 등록 시간, 수정 시간 
}
