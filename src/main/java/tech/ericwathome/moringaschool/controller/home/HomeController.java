package tech.ericwathome.moringaschool.controller.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {
    @GetMapping
    public String homeEndpoint() {
        return "<div style='padding-left:48px;padding-right-48px;'>" +
                "<h1 style='text-align:center;margin-top:70px;'>Moringa School API Version 0.0.1</h1>" +
                "<p style='font-size:24px'>Below are the different end points that showcase how the API works</p>" +
                "<p style='font-size:24px;font-weight:bold;'>Students</p>" +
                "" +
                "</div>";
    }
}
