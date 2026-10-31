package com.ioc.springcoredemo.common;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {
    public CricketCoach() {
    System.out.println(">> Constructor: " + getClass().getSimpleName());
}
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }



    @PostConstruct
public void doMyStartupStuff() {
    System.out.println(">> CricketCoach: inside doMyStartupStuff()");
}

@PreDestroy
public void doMyCleanupStuff() {
    System.out.println(">> CricketCoach: inside doMyCleanupStuff()");
}

    
}