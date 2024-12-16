package net.datasa.test.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
 * 판매글 Entity
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "market_board")
public class BoardEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_num", nullable =false)
	Integer boardNum;
	
	//작성자 정보(외래키)
	@ManyToOne(fetch = FetchType.LAZY) // n+1
	@JoinColumn(name="member_id",
	            referencedColumnName = "member_id")
	MemberEntity member;
	
	//카테고리
    @Column(name = "category")
    String category;
    
    //제목
    @Column(name = "title", nullable = false)
    String title;
    
  //본문
    @Column(name = "contents", nullable = false)
    String contents;
    
    //판매 가격
    @Column(name = "price", columnDefinition = "int default 0")
    Integer price;
    
    //판매완료
    @Column(name = "soldout", 
    		 columnDefinition = "tinyint(1) default 0")
    Boolean soldout;
    
   //구매자 정보(외래키)
  	@ManyToOne(fetch = FetchType.LAZY) // n+1
  	@JoinColumn(name="buyer_id",
  	            referencedColumnName = "member_id")
  	MemberEntity buyer;
  	
  //작성일
    @CreatedDate
    @Column(name = "input_date", 
       columnDefinition = "timestamp default current_timestamp")
    LocalDateTime inputDate;
  	

}
