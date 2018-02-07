package com.example.spring5WebFluxTest;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/service")
public class TestController {

    @GetMapping("/token")
    public Mono<HashMap> token(
            ServerHttpRequest request,
            ServerHttpResponse response,
            @RequestParam String service,
            @RequestParam String scope) {
        System.out.println("-------------------------------------------");

        System.out.println("Headers:");
        request.getHeaders().forEach((k, v) -> System.out.println(k + " : " + v));

        System.out.println("#################################################");

        System.out.println("QueryParams:");
        request.getQueryParams().forEach((k, vl) -> System.out.println(k + " : " + vl));

        System.out.println("-------------------------------------------");

        String[] s = scope.split(":");


        Error error = new Error() {{
            setDetail(new ArrayList<Scope>() {{
                add(new Scope() {{
                    setType(s[0]);
                    setName(s[1]);
                    setAction("pull");
                }});
                add(new Scope() {{
                    setType(s[0]);
                    setName(s[1]);
                    setAction("push");
                }});
            }});
        }};

        HashMap m = new HashMap() {{
            put("errors", new ArrayList<Error>() {{
                add(error);
            }});
        }};
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().set("Www-Authenticate", "Bearer " +
                "realm=\"http://localhost:8081/service/token\"," +
                "service=\"" + service + "\"," +
                "scope=\"" + s[0] + ":" + s[1] + ":pull,push" + "\"");
        return Mono.just(m);
    }

    static class Error {
        String code = "UNAUTHORIZED";
        String message = "access to the requested resource is not authorized";
        private List<Scope> detail;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Scope> getDetail() {
            return detail;
        }

        public void setDetail(List<Scope> detail) {
            this.detail = detail;
        }
    }

    static class Scope {
        String Type;
        String Name;
        String Action;

        @JsonProperty("Type")
        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        @JsonProperty("Name")
        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        @JsonProperty("Action")
        public String getAction() {
            return Action;
        }

        public void setAction(String action) {
            Action = action;
        }
    }
}
