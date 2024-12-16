package net.datasa.test.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.datasa.test.domain.dto.MemberDTO;
import net.datasa.test.domain.entity.MemberEntity;
import net.datasa.test.repository.MemberRepository;

/**
 * 회원정보 서비스
 */
@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    //WebSecurityConfig에서 생성한 암호화 인코더
    private final BCryptPasswordEncoder passwordEncoder;
    
    //회원정보 관련 리포지토리
    private final MemberRepository memberRepository;
    /**
     * 회원 가입 처리
     * @param dto 회원 정보
     */
    public void join(MemberDTO dto) {
    	//전달된 회원정보를 테이블에 저장한다.
     MemberEntity entity = MemberEntity.builder()
    		 .memberId(dto.getMemberId())
    		 .memberPw(passwordEncoder.encode(dto.getMemberPw()))
    		 .memberName(dto.getMemberName())
    		 .phone(dto.getPhone())
    		 .build();
     
        memberRepository.save(entity);
    }
    


}