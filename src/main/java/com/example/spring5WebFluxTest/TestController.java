package com.example.spring5WebFluxTest;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
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


        List<String> authorization = request.getHeaders().get("Authorization");

        if (authorization != null && !authorization.isEmpty()) {
            HashMap m = new HashMap();
            authorization.forEach(a -> {
                System.out.println("Authorization: " + a);
                if (a.indexOf("Basic ") == 0) {
                    String sc = a.replace("Basic ", "");
                    System.out.println("Authorization replace: " + sc);
                    sc = new String(Base64.decodeBase64(sc));
                    System.out.println("Authorization Base64.decodeBase64: " + sc);
                    String[] up = sc.split(":");
                    if (up[0].equals("test") && up[1].equals("test")) {

                        m.put("token", "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCIsIng1YyI6WyJNSUlDTHpDQ0FkU2dBd0lCQWdJQkFEQUtCZ2dxaGtqT1BRUURBakJHTVVRd1FnWURWUVFERXp0Uk5Gb3pPa2RYTjBrNldGUlFSRHBJVFRSUk9rOVVWRmc2TmtGRlF6cFNUVE5ET2tGU01rTTZUMFkzTnpwQ1ZrVkJPa2xHUlVrNlExazFTekFlRncweE56QTFNREl5TWpBME5UZGFGdzB4T0RBMU1ESXlNakEwTlRkYU1FWXhSREJDQmdOVkJBTVRPMDFPTms0NlJraFVWenBKV0VWSE9rOUpOMUU2UVRWWFJqcFpSRVUwT2pkRE4wNDZSMWRKVVRvMVZ6STNPa2hPTlVvNlZVNURRVG95U0UxQ01Ga3dFd1lIS29aSXpqMENBUVlJS29aSXpqMERBUWNEUWdBRU5KRklhQ1hHNWYxSk9BZnZSaTJDU081K1Q5RVpKd2doai9SUXgzNW9Uc3Q4RnhXY0dRc3ZOMG5sdW5DVVdIbENxN2I4NFJRTXV0WUVIUnY4MVhweTU2T0JzakNCcnpBT0JnTlZIUThCQWY4RUJBTUNCNEF3RHdZRFZSMGxCQWd3QmdZRVZSMGxBREJFQmdOVkhRNEVQUVE3VFU0MlRqcEdTRlJYT2tsWVJVYzZUMGszVVRwQk5WZEdPbGxFUlRRNk4wTTNUanBIVjBsUk9qVlhNamM2U0U0MVNqcFZUa05CT2pKSVRVSXdSZ1lEVlIwakJEOHdQWUE3VVRSYU16cEhWemRKT2xoVVVFUTZTRTAwVVRwUFZGUllPalpCUlVNNlVrMHpRenBCVWpKRE9rOUdOemM2UWxaRlFUcEpSa1ZKT2tOWk5Vc3dDZ1lJS29aSXpqMEVBd0lEU1FBd1JnSWhBSTJVUlpMQVRTM3R4bjNpNTY0SXVQSFEwQU1Mb1g5cTZCMmdnN01KSHJuTkFpRUE0Q3lzbmtENHhjQm42amdobVdnQzczQjdGVkszenFnOTV4ZjNRK2xGVHlrPSJdfQ.eyJhY2Nlc3MiOltdLCJhdWQiOiJyZWdpc3RyeS5kb2NrZXIuaW8iLCJleHAiOjE1MTgwMTE5OTQsImlhdCI6MTUxODAxMTY5NCwiaXNzIjoiYXV0aC5kb2NrZXIuaW8iLCJqdGkiOiI0ckRBam9PYWJxQm5lRVhXWUtHeSIsIm5iZiI6MTUxODAxMTM5NCwic3ViIjoiIn0.rt8lz6LWTRVeKc1AzU-Wu3v4KVroGqut-ePZK_KNTpYUDRJa-MXbd910ehxXHQU2kcvhQAnV90SkQx9D7Ed9aw\",\"access_token\":\"eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCIsIng1YyI6WyJNSUlDTHpDQ0FkU2dBd0lCQWdJQkFEQUtCZ2dxaGtqT1BRUURBakJHTVVRd1FnWURWUVFERXp0Uk5Gb3pPa2RYTjBrNldGUlFSRHBJVFRSUk9rOVVWRmc2TmtGRlF6cFNUVE5ET2tGU01rTTZUMFkzTnpwQ1ZrVkJPa2xHUlVrNlExazFTekFlRncweE56QTFNREl5TWpBME5UZGFGdzB4T0RBMU1ESXlNakEwTlRkYU1FWXhSREJDQmdOVkJBTVRPMDFPTms0NlJraFVWenBKV0VWSE9rOUpOMUU2UVRWWFJqcFpSRVUwT2pkRE4wNDZSMWRKVVRvMVZ6STNPa2hPTlVvNlZVNURRVG95U0UxQ01Ga3dFd1lIS29aSXpqMENBUVlJS29aSXpqMERBUWNEUWdBRU5KRklhQ1hHNWYxSk9BZnZSaTJDU081K1Q5RVpKd2doai9SUXgzNW9Uc3Q4RnhXY0dRc3ZOMG5sdW5DVVdIbENxN2I4NFJRTXV0WUVIUnY4MVhweTU2T0JzakNCcnpBT0JnTlZIUThCQWY4RUJBTUNCNEF3RHdZRFZSMGxCQWd3QmdZRVZSMGxBREJFQmdOVkhRNEVQUVE3VFU0MlRqcEdTRlJYT2tsWVJVYzZUMGszVVRwQk5WZEdPbGxFUlRRNk4wTTNUanBIVjBsUk9qVlhNamM2U0U0MVNqcFZUa05CT2pKSVRVSXdSZ1lEVlIwakJEOHdQWUE3VVRSYU16cEhWemRKT2xoVVVFUTZTRTAwVVRwUFZGUllPalpCUlVNNlVrMHpRenBCVWpKRE9rOUdOemM2UWxaRlFUcEpSa1ZKT2tOWk5Vc3dDZ1lJS29aSXpqMEVBd0lEU1FBd1JnSWhBSTJVUlpMQVRTM3R4bjNpNTY0SXVQSFEwQU1Mb1g5cTZCMmdnN01KSHJuTkFpRUE0Q3lzbmtENHhjQm42amdobVdnQzczQjdGVkszenFnOTV4ZjNRK2xGVHlrPSJdfQ.eyJhY2Nlc3MiOltdLCJhdWQiOiJyZWdpc3RyeS5kb2NrZXIuaW8iLCJleHAiOjE1MTgwMTE5OTQsImlhdCI6MTUxODAxMTY5NCwiaXNzIjoiYXV0aC5kb2NrZXIuaW8iLCJqdGkiOiI0ckRBam9PYWJxQm5lRVhXWUtHeSIsIm5iZiI6MTUxODAxMTM5NCwic3ViIjoiIn0.rt8lz6LWTRVeKc1AzU-Wu3v4KVroGqut-ePZK_KNTpYUDRJa-MXbd910ehxXHQU2kcvhQAnV90SkQx9D7Ed9aw");
                        m.put("expires_in", "300");
                        m.put("issued_at", new Date());
                    }
                }
            });
            return Mono.just(m);
        }
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

    class Tokon {
        String token;
        int expires_in = 300;
        Date issued_at = new Date();

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public Date getIssued_at() {
            return issued_at;
        }

        public void setIssued_at(Date issued_at) {
            this.issued_at = issued_at;
        }
    }
}
