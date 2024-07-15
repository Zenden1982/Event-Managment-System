package com.zenden.task_management_system;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableScheduling
public class TaskManagementSystemApplication {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ввберите базу данных (H2, PostgreSQL): ");
		String database = sc.nextLine();

		if (database.equals("H2")) {
			System.setProperty("spring.profiles.active", "h2");
		}

		if (database.equals("PostgreSQL")) {
			System.setProperty("spring.profiles.active", "postgresql");
		}
		else if (!database.equals("H2") && !database.equals("PostgreSQL")) {
			System.out.println("По умолчанию выставленна база данных H2");
			System.setProperty("spring.profiles.active", "h2");
		}
		SpringApplication.run(TaskManagementSystemApplication.class, args);
	}

}
