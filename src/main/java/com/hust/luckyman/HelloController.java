package com.hust.luckyman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 返回json 相当于 @ResponseBody + @Controller
@RequestMapping("/Enter") //访问到该Controller中的url都要加上/Enter
public class HelloController {

    @Autowired  //自动注入
    private LimitConfig limitConfig;

    @GetMapping("/say")
    public String say(){
        return "说话： " + limitConfig.getDescription();
    }

    //通过url传一个id
    @GetMapping("/GetId")
    public String getId(@RequestParam(value = "id",required = false,defaultValue = "0")Integer myId){

        return "id :" + myId;
    }
}
