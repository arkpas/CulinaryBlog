package arkpas.culinaryblog.controller;

import arkpas.culinaryblog.domain.Cattegory;
import arkpas.culinaryblog.domain.User;
import arkpas.culinaryblog.service.CattegoryService;
import arkpas.culinaryblog.service.UserService;
import arkpas.culinaryblog.utils.CattegoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private CattegoryService cattegoryService;
    private UserService userService;

    @Autowired
    public UserController(CattegoryService cattegoryService, UserService userService) {
        this.cattegoryService = cattegoryService;
        this.userService = userService;
    }




    // this attribute is used in menu on every page
    @ModelAttribute("timeCattegories")
    public List<Cattegory> getTimeCattegories () {
        return cattegoryService.getCattegories(CattegoryType.TIME);
    }

    // this attribute is used in menu on every page
    @ModelAttribute ("dietCattegories")
    public List<Cattegory> getDietCattegories () {
        return cattegoryService.getCattegories(CattegoryType.DIET);
    }

    // this attribute is used in menu on every page
    @ModelAttribute ("mealCattegories")
    public List<Cattegory> getMealCattegories () {
        return cattegoryService.getCattegories(CattegoryType.MEAL);
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login () {
        return "login";
    }

    @RequestMapping(value = "/loginfail", method = RequestMethod.GET)
    public String loginFail (RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", true);
        return "redirect:/login";
    }

    @RequestMapping(value = "/loginsuccess", method = RequestMethod.GET)
    public String loginSuccess () {
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterForm (Model model) {
        User emptyUser = new User();
        model.addAttribute("user", emptyUser);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register (@Valid User user, BindingResult result, String passwordRepeat, Model model) {
        if (result.hasErrors())
            return "register";
        else {
            String errorMessage = userService.addUser(user.getUsername(), user.getPassword(), passwordRepeat);
            if (errorMessage != null) {
                model.addAttribute("errorMessage", errorMessage);
                return "register";
            }
            else {
                return "registerSuccess";
            }

        }
    }


}
