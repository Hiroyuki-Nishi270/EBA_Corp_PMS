package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class EbAcorpPmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(EbAcorpPmsApplication.class, args);
	}
}
