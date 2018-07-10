package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/student")
@Controller
public class StudentController {



    @RequestMapping ("/")
    public String showSchedule () {
        return "schedule";
    }

    @RequestMapping("/enroll")
    public String enroll(){
        return "enroll";
    }

    @RequestMapping("/transcript")
    public String viewTranscript (){
        return "transcript";
    }

}

