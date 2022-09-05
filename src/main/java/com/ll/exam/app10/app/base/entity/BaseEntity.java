package com.ll.exam.app10.app.base.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@SuperBuilder
@MappedSuperclass // Entity 상속 시 부모 Entity 에 표시 -> 테이블이 따로 생성되지 않는다.
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class) // 추가
@ToString
public class BaseEntity {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @CreatedDate // 추가
  private LocalDateTime createDate;
  @LastModifiedDate // 추가
  private LocalDateTime modifyDate;
}