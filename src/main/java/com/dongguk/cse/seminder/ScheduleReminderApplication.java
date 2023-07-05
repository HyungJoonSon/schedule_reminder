package com.dongguk.cse.seminder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class ScheduleReminderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleReminderApplication.class, args);
	}

}
