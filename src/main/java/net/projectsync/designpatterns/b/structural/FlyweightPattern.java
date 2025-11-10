package net.projectsync.designpatterns.b.structural;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

public class FlyweightPattern {
	@Configuration
	static class FlyweightConfig {

        // Singleton scope (shared object)
        @Bean
        @Scope("singleton")
        HeavyService heavyService() {
			return new HeavyService();
		}
	}

	static class HeavyService {
		public void serve(String extrinsicData) {
			System.out.println("Serving with data: " + extrinsicData);
		}
	}

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(FlyweightConfig.class);

		HeavyService service1 = context.getBean("heavyService", HeavyService.class);
		HeavyService service2 = context.getBean("heavyService", HeavyService.class);

		System.out.println(service1 == service2); // true → same shared object

		service1.serve("Request 1");
		service2.serve("Request 2");
	}
}
