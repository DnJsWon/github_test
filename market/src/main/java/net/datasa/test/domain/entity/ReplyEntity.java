package net.datasa.test.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 리플 Entity
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "market_reply")
public class ReplyEntity {
	 //리플번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reply_num", nullable = false)
	Integer replyNum;
	
	//본문글 정보 (외래키)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="board_num",
	            referencedColumnName = "board_num")
	BoardEntity board;
	
	//작성자 정보 (외래키)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_id",
	 referencedColumnName = "member_id")
	MemberEntity member;
	
	//리플 내용
	@Column(name ="reply_text", nullable = false) 
	String replyText;
	

}
