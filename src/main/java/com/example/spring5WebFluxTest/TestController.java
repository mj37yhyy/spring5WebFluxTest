package com.example.spring5WebFluxTest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/service")
public class TestController {

	@GetMapping("/token")
	public Mono<HashMap> token(
			ServerHttpRequest request,
			ServerHttpResponse response,
			@RequestParam
			String service,
			@RequestParam
			String scope,
			@RequestParam(required = false)
			String account) {
		System.out.println("-------------------------------------------");

		System.out.println("Headers:");
		request.getHeaders()
				.forEach((k, v) -> System.out.println(k + " : " + v));

		System.out.println("#################################################");

		System.out.println("QueryParams:");
		request.getQueryParams()
				.forEach((k, vl) -> System.out.println(k + " : " + vl));

		System.out.println("-------------------------------------------");

		String[] s = scope.split(":");
		List<Scope> scopes = new ArrayList<Scope>() {
			{
				add(new Scope() {
					{
						setType(s[0]);
						setName(s[1]);
						setAction("pull");
					}
				});
				add(new Scope() {
					{
						setType(s[0]);
						setName(s[1]);
						setAction("push");
					}
				});
			}
		};

		List<String> authorization = request.getHeaders().get("Authorization");

		if (authorization != null && !authorization.isEmpty()) {
			HashMap m = new HashMap();
			authorization.forEach(a -> {
				if (a.indexOf("Basic ") == 0) {
					String sc = a.replace("Basic ", "");
					sc = new String(Base64.decodeBase64(sc));
					System.out.println(
							"Authorization Base64.decodeBase64: " + sc);
					String[] up = sc.split(":");
					if (up[0].equals("test") && up[1].equals("test")) {

						String pem = "";
						try {
							pem = IOUtils.toString(
									Thread.currentThread()
											.getContextClassLoader()
											.getResourceAsStream(
													"private_key.pem"),
									"UTF-8");
						} catch (IOException e) {
							e.printStackTrace();
						}
						String token = JwtUtils.createJWT(
								account,
								service,
								"harbor-token-issuer",
								getRandomString(16),
								scopes,
								60 * 60 * 1000,
								pem);

						System.out.println("token=" + token);

						m.put("token", token);
						m.put("expires_in", "300");
						//RFC3339 格式
						m.put("issued_at", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
								.format(new Date()));
					}
				}
			});
			return Mono.just(m);
		}


		HashMap m = new HashMap() {
			{
				put("errors", new ArrayList<Error>() {
					{
						add(new Error() {
                            {
                                setDetail(scopes);
                            }
                        });
					}
				});
			}
		};
		response.setStatusCode(HttpStatus.UNAUTHORIZED);
		response.getHeaders().set("Www-Authenticate", "Bearer " +
				"realm=\"http://localhost:8081/service/token\"," +
				"service=\"" + service + "\"," +
				"scope=\"" + s[0] + ":" + s[1] + ":pull,push" + "\"");
		return Mono.just(m);

	}

	public static String getRandomString(int length) { // length 字符串长度
		StringBuffer buffer = new StringBuffer(
				"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
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
