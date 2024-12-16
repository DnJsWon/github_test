package net.datasa.test.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 리플 DTO
 */
@Builder//매개변수있는 생성자 만드는메소드
@Data//getter/setter/toString
@NoArgsConstructor//default 생성자
@AllArgsConstructor//매개변수있는 생성자
public class ReplyDTO {

    //리플번호
    Integer replyNum;

    //본문글 번호
    Integer boardNum;

    //작성자 ID
    String memberId;

    //리플 내용
    String replyText;


}
