package study.spring.start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName      : study.spring.start.controller
 * date             : 2022-03-26
 * author           : SuJeong Gong
 * version	        : 1.0
 * description      : 홈 화면 컨드롤러
 * ================================================================
 * DATE             AUTHOR              NOTE
 * ----------------------------------------------------------------
 * 2022-03-26       SuJeong Gong        최초작성
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

}
