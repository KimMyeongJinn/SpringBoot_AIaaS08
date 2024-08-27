package org.suhodo.sb01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.suhodo.sb01.dto.BoardDTO;
import org.suhodo.sb01.dto.PageRequestDTO;
import org.suhodo.sb01.dto.PageResponseDTO;
import org.suhodo.sb01.service.BoardService;

import javax.validation.Valid;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // /board/list
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);


        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/register")
    public void registerGET(){

    }

    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        log.info("board POST register.....");

        // @Valid처리를 통해 BoardDTO의 제약사항에 위배되면
        // 아래 에러가 발생한다.
        if(bindingResult.hasErrors()){
            log.info("has errors..........");
            // redirect전송 시 처음 1번 "errors"값을 꺼내도록 전송한다.
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/board/register";
        }

        log.info(boardDTO);

        Long bno = boardService.register(boardDTO);
        redirectAttributes.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public void read(Long bno, PageRequestDTO pageRequestDTO, Model model){
        BoardDTO boardDTO = boardService.readOne(bno);

        log.info(boardDTO);

        model.addAttribute("dto", boardDTO);
    }
}














