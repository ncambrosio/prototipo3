package yes.share.library;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import yes.share.library.services.PasswordService;

@RunWith(SpringRunner.class)
public class UtilitiesTests {

	@Ignore
	@Test
	public void passwordTest() {
		char[] cleanPassword = "123456789".toCharArray();
		byte[] hashPassword = PasswordService.hashPassword(cleanPassword);
		char[] resultado = hashPassword.toString().toCharArray();
        System.out.println("HashPassword (as byte[]): " + hashPassword);
        System.out.println("HashPassword (as string): " + hashPassword.toString());
        System.out.println("HashPassword:(as char[]): " + resultado.toString());
	}
	
	@Test
	public void springSecurityTest() {
		BCryptPasswordEncoder BCryptPassEncoder = new BCryptPasswordEncoder();
		String[] encriptados = new String[10];
		for (int i=0;i<10;i++) {
			encriptados[i] = BCryptPassEncoder.encode("123456");
		}
		for (int i=0;i<10;i++) {
			System.out.print(encriptados[i] + " testing...");
			if (BCryptPassEncoder.matches("123456", encriptados[i])){
				System.out.println("OK");
			} else {
				System.out.println("FAIL");
			}
		}
	}
}