package org.suhodo.sb01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.suhodo.sb01.dto.PageRequestDTO;
import org.suhodo.sb01.service.BoardService;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // /board/list
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

    }
}
