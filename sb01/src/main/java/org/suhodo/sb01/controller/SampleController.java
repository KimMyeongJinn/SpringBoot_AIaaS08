package org.suhodo.sb01.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

/* Facade 패턴
FrontController - PageController의 이중 구조
FrontController : tomcat에서 데이터를 우선 처리/공통 처리하는 부분
                  주소와 매핑된 PageController로 전달한다.
                  스프링에서는 DispatcherServlet이 담당한다.
PageController :  FrontController에서 넘겨받은 데이터를 처리
                  이곳에서 jsp/thymeleaf로 데이터를 전달
jsp/thymeleaf :  PageController에서 넘겨받은 데이터를
                html코드 부분에 배치시켜서
                브라우저에 렌더링 될 화면
                내부적으로는 java로 변환된다.
* */

// PageController라는 어노테이션
@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public void hello(Model model){
        log.info("hello....................");

        // templates/hello.html로 아래 데이터 전달
        model.addAttribute("msg", "HELLO WORLD");
    }

    @GetMapping("/ex/ex1")
    public void ex1(Model model){
        List<String> list = Arrays.asList("AAA", "BBB", "CCC", "DDD");

        model.addAttribute("list", list);
    }
}