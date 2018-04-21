package com.oukingtim;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.jta.JtaTransactionManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KingAdminJavaApplicationTests {

	@Test
	public void contextLoads() {
		JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
		System.out.println();
	}

}
