package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

@Controller
public class AdminController {

    private UserService userService;
    private RoleService roleService;
    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String listUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/admin/admin";
    }

    @GetMapping(value="/admin/create")
    public String createUser(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "/admin/create";
    }

    @PostMapping("/admin/create")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/user/{id}")
    public String editUser(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute(userService.getUser(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "/admin/edit";
    }

    @PatchMapping("/admin/edit/user/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete/user/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        User user = userService.getUser(id);
        userService.deleteUser(user);
        return "redirect:/admin";
    }
}
