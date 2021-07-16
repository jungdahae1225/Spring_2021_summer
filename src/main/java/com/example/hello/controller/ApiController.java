//controller에서 요청을 받는다.
package com.example.hello.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //해당 class를 Rest API컨트롤러로 등록이 됨
@RequestMapping("/api") //URI를 지정해주는 annotation

public class ApiController {
    @GetMapping("/hello") //get방식으로 정보를 보내고 해당 URI를 저장해주는.
    //여기까지의 mapping이 끝나면 http://localhost:9090/api/hello 로 설정이 된 것.

    public String hello(){
        return "hello spring boot!";
    }
}
