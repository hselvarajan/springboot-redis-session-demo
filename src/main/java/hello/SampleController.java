package hello;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@EnableAutoConfiguration
    public class SampleController {

    @RequestMapping("/")
    @ResponseBody
	String home() {
        return "Hello World!";
    }

    @RequestMapping("set")
    @ResponseBody
    String set(@RequestParam(name="value") String value, HttpSession session) {
        session.setAttribute("test", value);
        return ("Set key: test to value: "+session.getAttribute("test"));
    }

    @RequestMapping("/get")
    @ResponseBody
    String get(HttpSession session) {
        return (String)session.getAttribute("test");
    }
}
