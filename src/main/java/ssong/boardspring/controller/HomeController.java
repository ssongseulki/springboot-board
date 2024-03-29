package ssong.boardspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    //    메인 화면
    @GetMapping({"/", "home"})
    public String home(Model model, HttpServletRequest request) {
        String user = request.getRemoteUser();
        model.addAttribute("user", user);
        return "home";
    }

    //    로그인 화면
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}

