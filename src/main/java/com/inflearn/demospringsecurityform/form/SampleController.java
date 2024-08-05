package com.inflearn.demospringsecurityform.form;

import com.inflearn.demospringsecurityform.account.Account;
import com.inflearn.demospringsecurityform.account.AccountRepository;
import com.inflearn.demospringsecurityform.account.UserAccount;
import com.inflearn.demospringsecurityform.book.BookRepository;
import com.inflearn.demospringsecurityform.common.CurrentUser;
import com.inflearn.demospringsecurityform.common.SecurityLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.concurrent.Callable;

@Controller
public class SampleController {

    @Autowired
    SampleService sampleService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/")
    public String index(Model model, @CurrentUser Account account){
        if (account == null) {
            model.addAttribute("message", "Hello, Spring  Security");
        } else {
            model.addAttribute("message", "Hello, " + account.getUsername());
        }
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model){
        model.addAttribute("message", "Info");
        return "info";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @CurrentUser Account account){
        model.addAttribute("message", "Hello, " + account.getUsername());
        sampleService.dashboard();
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, @CurrentUser Account account){
        model.addAttribute("message", "Hello Admin, " + account.getUsername());
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model, @CurrentUser Account account){
        model.addAttribute("message", "Hello User, " + account.getUsername());
        model.addAttribute("books", bookRepository.findCurrenUserBooks());
        return "user";
    }

    @GetMapping("/async-handler")
    @ResponseBody
    public Callable<String> asyncHandler() {
        SecurityLogger.log("MVC");
        return () -> {
            SecurityLogger.log("Callable");
            return "Async Handler";
        };
    }

    @GetMapping("/async-service")
    @ResponseBody
    public String asyncService() {
        SecurityLogger.log("MVC, before async service");
        sampleService.asyncService();
        SecurityLogger.log("MVC, after async service");
        return "Async Service";
    }

}
