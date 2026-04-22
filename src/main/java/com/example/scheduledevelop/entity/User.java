package com.example.scheduledevelop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 100, nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    // 부모엔티티(유저)가 삭제 시 자식 엔티티(일정)도 삭제
    // mappedBy : Schedule엔티티에 있는 user 필드를 명시
    // cascade = CascadeType.ALL : 저장, 삭제, 수정 모드 부모가 변경되면 자식도 같이 변경된다.
    // CascadeType.REMOVE : 부모 엔티티가 삭제될 때만 자식 엔티티도 같이 삭제된다. 유저를 저장하거나 수정할 때는 자식엔티티에게 아무런 영향을 주지 않는다.
    // orphanRemoval = true : 리스트에서 특정 일정을 제거하면, DB에서도 해당 데이터를 실제로 삭제
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();


    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void update(String userName) {
        this.userName = userName;
    }
}
