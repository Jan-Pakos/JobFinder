package com.jobfinder.domain;

import com.jobfinder.JobFinderApplication;
import com.jobfinder.TestcontainersConfiguration;
import org.springframework.boot.SpringApplication;

public class TestJobFinderApplication {

    public static void main(String[] args) {
        SpringApplication.from(JobFinderApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
