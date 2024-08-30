package org.suhodo.sb01.domain;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// 실행될 때 JPA에 의해 테이블로 생성된다.
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity{
    // @Id : primary key
    // GenerationType.IDENTITY : auto_increment 일련번호 자동증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length=500, nullable=false)
    private String title;

    @Column(length=2000, nullable=false)
    private String content;

    @Column(length=50, nullable=false)
    private String writer;

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }

    /* @OneToMany에 mappedBy설정을 하지 않으면
       BoardImage와 사이에 중간 테이블을 만든다
       아래처럼 설정해야 1:n 관계로 양방향 연결이
       된다.
       
       cascade설정
       부모가 변경/삭제될 때 자식도 변경/삭제되는 설정
       그러나 실제 삭제되지 않는다.
       왜냐하면 부모가 없다고 자식 데이터를 삭제하는 문제는 신중해야 하기 때문에

       orphanRemoval = true 까지 추가해야 진짜 삭제가 된다.

       @BatchSize(size = 20)
       1개의 게시물당 종속된 자식을 모두 조회하게 되므로 성능저하가 일어난다.
       이것을 'N+1'문제라고 한다.
       이 때 자식을 여러 개를 모아서 한번에 실행하게 할 수 있다.
       위처럼 size를 지정해주면 모아서 처리하므로 성능저하를 막을 수 있다.
    * */
    @OneToMany(mappedBy = "board",
                cascade = {CascadeType.ALL},
                fetch = FetchType.LAZY,
                orphanRemoval = true
                )
    @Builder.Default
    @BatchSize(size = 20)
    private Set<BoardImage> imageSet = new HashSet<>();

    public void addImage(String uuid, String fileName){
        BoardImage boardImage = BoardImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .board(this)
                .ord(imageSet.size())
                .build();
        imageSet.add(boardImage);
    }

    public void clearImages(){
        imageSet.forEach(boardImage -> boardImage.changeBoard(null));
        this.imageSet.clear();
    }
}












