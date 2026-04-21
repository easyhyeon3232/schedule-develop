package com.example.scheduledevelop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String name;

//    @ManyToOne(fetch = FetchType.LAZY)  // 지연 로딩 (선택 -> 연관된 자식 엔티티를 바로 가져오지 않고 필요할 때만 가져온다.)
//    @JoinColumn(name = "user_id")  // DB 컬럼에 들어갈 때 user_Id로 하겠다. 유저 엔티티의 유저id는 DB와 관련없이 꺼낼때 getid로 됨
//    private User user;

    @Builder
    public Schedule(String title, String content, String name) {
        this.title = title;
        this.content = content;
        this.name = name;
    }

    @Builder
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
