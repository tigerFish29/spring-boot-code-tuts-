package com.spring.springuser.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.spring.springuser.Service.UserService;
import com.spring.springuser.model.UserInfo;

@Controller
public class UserController {

    private final  UserService userService;

    // constructor 
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // crud paths 
    @RequestMapping(path="/", method = RequestMethod.GET)
    public String getUsers(Model model) {
       List<UserInfo> users = userService.getUsers();
       model.addAttribute("users", users);
       model.addAttribute("userInfo", new UserInfo());
       return "users";
    }

    // crud path for one user {} 
    @RequestMapping(path="/{id}", method= RequestMethod.GET)
    public String getUser(Model model, @PathVariable("id") Integer id) {
        UserInfo userInfo = userService.getUser(id);
        model.addAttribute("userInfo", userInfo);
        return "edit";
    }

    // crud path create [post ]
    @RequestMapping(path="/", method = RequestMethod.POST)
    public RedirectView createUser(RedirectAttributes redirects, @ModelAttribute UserInfo userInfo) {
        userService.createUser(userInfo);
        String message = "Created user <b>" + userInfo.getFirstName() + "  " + userInfo.getLastName() + "</b> .";
        RedirectView redirectView = new RedirectView("/", true);
        redirects.addFlashAttribute("userMessage", message);
        return redirectView;
    }

    
}
