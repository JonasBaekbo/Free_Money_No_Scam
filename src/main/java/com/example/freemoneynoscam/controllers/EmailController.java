package com.example.freemoneynoscam.controllers;

import com.example.freemoneynoscam.services.EmailRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailController {
    @GetMapping("/list")
    public String mails_list(Model m){
        //m.addAttribute("mail", EmailRepository.fetchSingleEmail()); //Shows one mail from the database
        m.addAttribute("mails", EmailRepository.fetchAllEmails()); // Shows all mails from the database
        return "mails_list";
    }

}
