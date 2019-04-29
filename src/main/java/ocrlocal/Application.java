package ocrlocal;

import javax.imageio.ImageIO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	static {
		ImageIO.scanForPlugins();
	}

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
