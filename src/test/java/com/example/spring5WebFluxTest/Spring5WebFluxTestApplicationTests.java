package com.example.spring5WebFluxTest;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class Spring5WebFluxTestApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'07:00")
				.format(new Date()));
	}

}
