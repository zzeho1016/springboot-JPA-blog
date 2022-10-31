package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 넘버링 전략을 따라감
	private int id;
	
	@Column(nullable=false, length=100)
	private String title;

	@Lob // 대용량
	private String content; // 섬머노트 라이브러리 <html>태그 섞여서 디자인됨
	
	@ColumnDefault("0")
	private int count; //조회수
	
	@ManyToOne(fetch=FetchType.EAGER) // Many = board one = user
	@JoinColumn(name="userId")
	private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
	
	@OneToMany(mappedBy="board", fetch=FetchType.EAGER) 
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
