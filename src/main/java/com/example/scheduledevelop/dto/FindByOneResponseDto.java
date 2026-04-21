package com.example.scheduledevelop.dto;

import com.example.scheduledevelop.entity.Schedule;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.processing.Find;

import java.time.LocalDateTime;

/**
 * 특정 일정 단건 조회 시 사용되는 응답 데이터 전송 객체(DTO)입니다.
 * {@link Schedule} 엔티티의 데이터를 클라이언트에게 필요한 정보로 변환하여 전달합니다.
 * 빌더 패턴을 내부적으로 사용하며, 외부에서는 {@link #from(Schedule)} 메서드를
 * 통해 객체를 생성하도록 제한되어 있습니다.
 */
@Getter
public class FindByOneResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String name;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;


    /**
     * 빌더 패턴을 이용한 내부 생성자입니다.
     * 외부 인스턴스화를 막기 위해서 접근 제어자가 {@link AccessLevel#PRIVATE}으로 설정되어 있습니다.
     * @param id 일정 식별자
     * @param title 제목
     * @param content 내용
     * @param name 작성자명
     * @param createAt 등록 일시
     * @param updateAt 수정 일시
     */
    @Builder(access = AccessLevel.PRIVATE)
    public FindByOneResponseDto(Long id, String title, String content, String name, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    /**
     * {@link Schedule} 엔티티 객체를 {@code FindByOneResponseDto}로 변환하는 정적 팩토리 메서드입니다.
     * @param schedule 변환할 일정 엔티티 객체
     * @return 변환된 응답 DTO 객체
     */
    public static FindByOneResponseDto from(Schedule schedule) {
        return FindByOneResponseDto.builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .name(schedule.getName())
                .createAt(schedule.getCreateAt())
                .updateAt(schedule.getUpdateAt())
                .build();

    }
}
