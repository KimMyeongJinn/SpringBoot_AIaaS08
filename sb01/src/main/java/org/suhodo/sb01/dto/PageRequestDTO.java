package org.suhodo.sb01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    private int page = 1;       // 페이지 번호

    @Builder.Default
    private int size = 10;      // 1개 페이지의 개수

    private String type;        // 검색종류 : t, c, w, tc, tw, twc

    private String keyword;     // 검색어

    // ex) "twc"문자열을 각각 ["t", "w", "c"]로 저장한다.
    public String[] getTypes(){
        if(type==null || type.isEmpty())
            return null;

        return type.split("");
    }

    /* String...props 가변 매개변수
    매개변수의 개수가 정해지지 않고, 여러 개가 올 수 있다.
    getPageable("bno");
    getPageable("bno", "writer");
    getPageable("bno", "writer", content);

    Sort.by(props).descending() : props에 전달된 매개변수 순서대로 ORDER BY desc 하게 된다.
    * */
    public Pageable getPageable(String...props){
        return PageRequest.of(this.page-1, this.size, Sort.by(props).descending());
    }
}
