package daniel.sheppard.firefly;

import org.springframework.boot.SpringApplication;

public class Application{

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AppConfig.class);
		app.setShowBanner(false);
		app.run(args);
	}

}
