package web.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

@Data
@NoArgsConstructor

@Controller
public class UserControllerNew {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/new_user")
    public String newUser() {
        return "new_user";
    }

    @PostMapping("/new_user")
    public String createNewUser(@RequestParam("name") String name,
                                @RequestParam("lastname") String lastname,
                                @RequestParam("age") int age,
                                @RequestParam("login") String login,
                                @RequestParam("password") String password,
                                @RequestParam("role") String role) {
        userService.createNewUser(name, lastname, age, login, password, role);
        return "redirect:/new_user";
    }

    @GetMapping("/user")
    public ModelAndView showUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user_page");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

}
