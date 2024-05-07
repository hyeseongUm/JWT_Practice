package com.seong.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class BasicController {

    @GetMapping("/")
    String main(){

        return "index.html";
    }

    @GetMapping("/date")
    @ResponseBody
    String date(){
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(today);
    }
}
