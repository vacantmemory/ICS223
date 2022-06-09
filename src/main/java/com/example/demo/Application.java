package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		try {
			File log1 = new File("/Users/wangyangxu/IdeaProjects/223project/src/main/resources/log1.txt");
			if (log1.createNewFile()) {
				System.out.println("log1 created: ");
			} else {
				log1.delete();
				log1.createNewFile();
				System.out.println("recreate log1.");
			}

			File log2 = new File("/Users/wangyangxu/IdeaProjects/223project/src/main/resources/log2.txt");
			if (log2.createNewFile()) {
				System.out.println("log2 created: ");
			} else {
				log2.delete();
				log2.createNewFile();
				System.out.println("recreate log2.");
			}

			File log3 = new File("/Users/wangyangxu/IdeaProjects/223project/src/main/resources/log3.txt");
			if (log3.createNewFile()) {
				System.out.println("log3 created: ");
			} else {
				log3.delete();
				log3.createNewFile();
				System.out.println("recreate log3.");
			}

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		SpringApplication.run(Application.class, args);
	}

}
