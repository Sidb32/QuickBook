package quickbooklookupweb.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
public class HelloWorldController{

	  @RequestMapping("/hello")
      protected ModelAndView HelloWorld() throws Exception {
        String hello = " ";
        return new ModelAndView("hello","hello",hello);
	  }

	
}
