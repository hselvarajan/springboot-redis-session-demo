package hello;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        Foo foo = new Foo();
        foo.setVar(value);
        session.setAttribute("foo", foo);

        System.out.println("session.getAttribute(\"test\") ==> "+session.getAttribute("test"));
        System.out.println("session.getAttribute(\"foo\")).getVar() ==>" +((Foo)session.getAttribute("foo")).getVar());


        return ("Set key: test to value: "+session.getAttribute("test"));
    }

    @RequestMapping("/get")
    @ResponseBody
    String get(HttpSession session) {

        System.out.println("session.getAttribute(\"test\") ==> "+session.getAttribute("test"));
        System.out.println("session.getAttribute(\"foo\")).getVar() ==>" +((Foo)session.getAttribute("foo")).getVar());

        return (String)session.getAttribute("test");
    }
}
