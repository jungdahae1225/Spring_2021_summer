package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/get")

public class GetApiController {

    @GetMapping(path = "/hello")
    //여기까지의 mapping이 끝나면 http://localhost:9090/api/get/hello 로 설정이 된 것.

    public String getHello() {
        return "Hello";
    }

    /****************************여기부터는 예전 방식*********************************/
    //@RequestMapping("/hi") //예전에 쓰던 방법으로, GET방식 뿐만 아니라 모든 방식을 쓸 수 있음
    @RequestMapping(path = "/hi", method = RequestMethod.GET) //윗줄처럼하면 모든 방식을 받고, 특정 방식을 사용할 경우에는 해당 줄처럼 수동으로 맞추어주기.
    //여기까지의 mapping이 끝나면 http://localhost:9090/api/get/hi 로 설정이 된 것.
    public String Hi() {
        return "Hi";
    }


    /****************************PathVariable*********************************/
    //http://localhost:9090/api/get/path-variable/{여기가 변동}
    @GetMapping("/path-variable/{name}") //주소에 대문자와 언더바 사용하지 않는다.
    public String pathVariable(@PathVariable String name) { //윗 줄 주소에 쓴 변수와 해당 메소드의 변수명이 같아야 함
        System.out.println("PathVariable: " + name); //콘솔에 짝어보기 위함
        return name;
    }

    /*****************만일, 주소와 @PathVariable의 변수명을 다르게 설정해야 할 때****************************/
            /*
                @GetMapping("/path-variable/{id}") //주소에 대문자와 언더바 사용하지 않는다.
                public String pathVariable(@PathVariable(name = "id") String newname){ //윗 줄 주소에 쓴 변수와 해당 메소드의 변수명이 같아야 함
                    System.out.println("PathVariable: " + newname); //콘솔에 짝어보기 위함
                    return newname;
                }
            */

    /****************************Query Parameter-Map을 사용하는 경우. => 파람으로 어떤 데이터가 들어올지 정해주지 않는 효과. (명시적이지 않다.)*********************************/

    //주소 설계 http://localhost:9090/api/get/query-param?user=steve&email=steve@gmail.com&age=30
    //기본 적으로 Query Parameter주소를 설계 할 때 처음 ? + { } + & + { } = & 의 구조로 이루어진다.
    @GetMapping("query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam) {
        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
        });

        return sb.toString();
    }

    /****************************Query Parameter => 파람으로 어떠한 데이터가 들어와야 하는지 명시 해주는 방법*********************************/
    // name에서 404에러가 계속 뜸 - 해결필요
    @GetMapping(path = "query-param02")
    public String queryParam02(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age
    ) {

        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name + " " + email + " " + age;
    }

    /****************************현업애서 가장 많이 사용하게 될 것이다. Query Parameter => 파람으로 어떠한 데이터가 들어와야 하는지 명시 해주는 방법*********************************/
    @GetMapping(path = "query-param03")
    public String queryParam03(UserRequest userRequest) {

        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }
}



