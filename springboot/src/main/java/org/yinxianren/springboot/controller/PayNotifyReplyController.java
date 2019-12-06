package org.yinxianren.springboot.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/pay")
public class PayNotifyReplyController {

   @RequestMapping("/notify")
    public String reply(HttpServletRequest request, @RequestBody(required = false) String param){
       log.info("param==>[{}]",param);
       return "SUCCESS";
    }

}
