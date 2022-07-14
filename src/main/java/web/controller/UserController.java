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
        //List<User> userList = userService.getAllUsers();
        List<User> userList = new ArrayList<>();
        userList.add(new User("Nata", 12));
        userList.add(new User("Pot", 12));
        userList.add(new User("Latoya", 12));
        userList.add(new User("Kata", 12));
        model.addAttribute("users", userList);
        return "/users";
    }

    @GetMapping(value = "/new")
    public String getNew(){
        return "/new";
    }

    @PostMapping()
   public String addUser(@ModelAttribute("new_user") @Valid User new_user,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "users";
        userService.add(new_user);
        return "redirect:/users";
    }

    @GetMapping("/user-delete/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/users";
    }

    @GetMapping("user-update/{id}")
    public String editUser(@PathVariable("id") Long id, Model model, Model model2) {
        User user = userService.getUser(id);
        model.addAttribute("new_user", user);
        model2.addAttribute("users", userService.getAllUsers());
        return "redirect:/users";
    }
}
