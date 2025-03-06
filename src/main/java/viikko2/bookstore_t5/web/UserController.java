package viikko2.bookstore_t5.web;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import viikko2.bookstore_t5.domain.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;


@Controller
public class UserController {

    private AppUserRepository repository;

    public UserController(AppUserRepository appUserRepository){
        this.repository = appUserRepository;
    }

    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public String addUser(Model model){
        model.addAttribute("signupform", new SignupForm());
        return "signup";
    }

    @RequestMapping(value = "saveuser", method=RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()){
            if (signupForm.getPassword().equals(signupForm.getPasswordCheck())){
                String pwd = signupForm.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPwd = bc.encode(pwd);

                AppUser newUser = new AppUser();
                newUser.setPasswordHash(hashPwd);
                newUser.setUsername(signupForm.getUsername());
                newUser.setRole("USER");
                if (repository.findByUsername(signupForm.getUsername()) == null) {
                    repository.save(newUser);
                } else {
                    bindingResult.rejectValue("username", "err.username", "Username already exits");
                    return "signup";
                }
            } 
           else {
            bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
            return "signup";
           }
        }
        else {
            return "signup";
        }
        return "redirect:/login";
    }
    


    
}
