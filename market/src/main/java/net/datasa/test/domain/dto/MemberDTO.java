package net.datasa.test.domain.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원정보 DTO
 */
@Builder//매개변수있는 생성자 만드는 메소드
@Data//getters/setters/toString
@NoArgsConstructor//기본생성자
@AllArgsConstructor//매개변수있는 생성자
public class MemberDTO {
	 //사용자 아이디
    String memberId;

    //비밀번호
    String memberPw;

    //사용자 이름
    String memberName;

    //전화번호
    String phone;


}
