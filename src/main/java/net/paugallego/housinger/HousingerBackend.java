package net.paugallego.housinger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "net.paugallego.housinger")
public class HousingerBackend {

	public static void main(String[] args) {
		SpringApplication.run(HousingerBackend.class, args);
	}
}