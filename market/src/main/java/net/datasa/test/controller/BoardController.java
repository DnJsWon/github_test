package net.datasa.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.test.domain.dto.BoardDTO;
import net.datasa.test.domain.dto.MemberDTO;
import net.datasa.test.security.AuthenticatedUser;
import net.datasa.test.service.BoardService;
import net.datasa.test.service.MemberService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 거래 게시판 관련 콘트롤러
 */

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("board")
public class BoardController {
	//게시판 처리 서비스
    private final BoardService boardService;
    /**
    * 글목록으로 이동
    * @return 글목록 HTML 파일
    */
    @GetMapping("list")
    public String list() {
    	return "board/list";
    }
    /**
     * 글쓰기 폼으로 이동
     * @return 글쓰기폼 HTML 파일
     */
    @GetMapping("write")
    public String write() {
    	return "board/writeForm";
    }
    
    @PostMapping("write")
    public String write(
    		BoardDTO board,
    		@AuthenticationPrincipal AuthenticatedUser user) {
    	//로그인된 사용자 정보
    	board.setMemberId(user.getUsername());
    	//저장
    	boardService.write(board);
    	
    	return "redirect:list";
    }
    
    @GetMapping("read")
    public String read(@RequestParam("boardNum") int boardNum,
    		           Model model) {
    	BoardDTO boardDTO = boardService.getBoard(boardNum);
    	
    	model.addAttribute("board", boardDTO);
    	return "board/read";
    }
    
    /**
     * 판매글 삭제
     * @param boardNum  삭제할 글 번호
     * @param user      사용자 정보
     * @return          글목록으로 리다이렉트
     */
    @GetMapping("delete")
    public String delete(@RequestParam("boardNum") int boardNum
            , @AuthenticationPrincipal AuthenticatedUser user) {
        log.debug("삭제할 판매글 : {}, 사용자 아이디 : {}", boardNum, user.getUsername());
        boardService.delete(boardNum, user.getUsername());
        return "redirect:list";
    }
    /**
     * 구매
     * @param boardNum  구매할 글 번호
     * @param user      구매자 정보
     * @return          판매글로 리다이렉트
     */
    @GetMapping("buy")
    public String buy(@RequestParam("boardNum") int boardNum
            , @AuthenticationPrincipal AuthenticatedUser user) {
        log.debug("구매할 판매글 : {}, 구매자 아이디 : {}", boardNum, user.getUsername());
        boardService.buy(boardNum, user.getUsername());
        return "redirect:read?boardNum=" + boardNum;
    }
    
    
    

   
}
