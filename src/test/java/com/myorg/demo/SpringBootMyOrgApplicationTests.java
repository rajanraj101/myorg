package com.myorg.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myorg.rest.SpringBootMyOrgApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMyOrgApplication.class)
public class SpringBootMyOrgApplicationTests {

	@Test
	public void contextLoads() {
		Assert.assertTrue(true);
	}

}
