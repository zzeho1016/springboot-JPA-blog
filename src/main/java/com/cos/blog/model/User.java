package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//@DynamicInsert insert시 null인 필드를 제외시켜줌 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // 빌더패턴
@Entity // user클래스가 MySQL에 테이블이 생성된다.
public class User {

	@Id// primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; //시퀀스, auto_increment
	
	@Column(nullable=false, length=30)
	private String username; //아이디
	
	@Column(nullable=false, length=100)
	private String password;
	
	@Column(nullable=false, length=50)
	private String email; 
	
	// DB는 RoleType이라는게 없다
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다. // admin, user, manager //도메인설정 = 범위
	
	@CreationTimestamp // 시간이 자동으로 입력 
	private Timestamp createDate;
}
