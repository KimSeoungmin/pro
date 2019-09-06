package com.test.web.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.web.vo.TestVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
    
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        
        String formattedDate = dateFormat.format(date);
        
        model.addAttribute("serverTime", formattedDate );
        
        return "main";
    }
    
    /**
     * Tiles를 사용하지 않은 일반적인 형태
     */    
    @RequestMapping("/test.do")
    public String test() {
        return "test";
    }    
    
    /**
     * Tiles를 사용(header, left, footer 포함)
     */        
    @RequestMapping("/testPage.do")
    public String testPage() {
        return "test.page";
    }
    
    /**
     * Tiles를 사용(header, left, footer 제외)
     */    
    @RequestMapping("/testPart.do")
    public String testPart() {
        return "test.part";
    }        
    
}    

//	@RequestMapping(value = "send1", method = RequestMethod.GET)
//	public String send1(String a, int b) {
//		System.out.println("a "+a);
//		System.out.println("b "+b);
//		
//		return "index";		
//	}
//	
//	
//	@RequestMapping(value = "send2", method = {RequestMethod.GET,RequestMethod.POST})
//	public String send2(String a, int b) {
//		System.out.println("a "+a);
//		System.out.println("b "+b);
//		
//		return "index";		
//	}
//	@RequestMapping(value = "send3", method = RequestMethod.POST)
//	public String send3(String a, int b) {
//		System.out.println("a "+a);
//		System.out.println("b "+b);
//		
//		return "index";		
//	}
//	
//	@RequestMapping(value = "send4", method = RequestMethod.GET)
//	public String send4(TestVO vo) {
//		System.out.println(vo);
//		return "index";		
//	}
//	
//	@RequestMapping(value = "send5", method = RequestMethod.GET)
//	public String send5(TestVO vo) {
//		System.out.println(vo);
//		return "index";
//	}
//	
//	@RequestMapping(value = "send6", method = RequestMethod.POST)	
//	public String send6(TestVO vo) {
//		System.out.println(vo);
//		return "index";		
//	}
//	
//	@RequestMapping(value = "send7", method = RequestMethod.GET)	
//	public String send7(Model model) {
//		model.addAttribute("data","테스트테스트");
//		model.addAttribute("vo", new TestVO("aaaa",2222));
//		return "index";		
//	}
	
//	@RequestMapping(value = "singupForm", method = RequestMethod.GET)	
//	public String singupform(Model model) {
//		return "singupForm";		
//	}

