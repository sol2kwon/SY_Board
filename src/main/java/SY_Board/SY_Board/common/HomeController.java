package SY_Board.SY_Board.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String board(){
        return "/board";
    }

    @RequestMapping("/createBoard")
    public String createBoard(){
        return "/createBoard";
    }
}
