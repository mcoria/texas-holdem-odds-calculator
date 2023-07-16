package poker.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/th")
public class Calculator {

    @GetMapping("/odds")
    public String hello() {
        return "Hello World!";
    }
}
