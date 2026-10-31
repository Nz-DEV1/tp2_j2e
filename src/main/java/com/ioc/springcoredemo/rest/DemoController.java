package com.ioc.springcoredemo.rest;

import com.ioc.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach aquaticCoach;
    private Coach myCoach;
    private Coach anotherCoach;

    @Autowired
    public DemoController(
            @Qualifier("cricketCoach") Coach theCoach,
            @Qualifier("cricketCoach") Coach theAnotherCoach,
            @Qualifier("aquatic") Coach theAquaticCoach) {

        myCoach = theCoach;
        anotherCoach = theAnotherCoach;
        aquaticCoach = theAquaticCoach;
}

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public boolean check() {
        return myCoach == anotherCoach;
    }
    @GetMapping("/aquatic")
public String getDailyAquaticWorkout() {
    return aquaticCoach.getDailyWorkout();
}
}