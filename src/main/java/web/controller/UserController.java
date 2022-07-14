package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String getUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "/users";
    }

    @GetMapping(value = "/new")
    public String getNew(Model model) {
        model.addAttribute("empty_user", new User());
        return "/new";
    }

    @PostMapping(value = "/new_user")
    public String addUser(@ModelAttribute("empty_user") @Valid User user) {
        if (user.getName() == null || user.getAge() == 0) { return "redirect:/users"; }
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/users";
    }

    @GetMapping("/update/{id}")
    public String getChange(@PathVariable("id") Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("process_user", user);
        return "/update";
    }

    @PostMapping(value = "/update_execute")
    public String changeUser(@ModelAttribute("process_user") @Valid User user) {
        if (user.getName() == null || user.getAge() == 0) { return "redirect:/users"; }
        userService.update(user);
        return "redirect:/users";
    }
}
