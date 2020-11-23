package web.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.UserService;

@Data
@NoArgsConstructor

@Controller
@RequestMapping("/")
public class HomeController {

    private UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

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
}
