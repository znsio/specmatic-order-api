package com.store;

import com.store.model.DB;
import in.specmatic.test.SpecmaticJUnitSupport;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class ContractTests extends SpecmaticJUnitSupport {
    private static ConfigurableApplicationContext context;

    @BeforeAll
    public static void setUp() {
        System.setProperty("host", "localhost");
        System.setProperty("port", "8080");

        System.setProperty("environment", "staging");
        System.setProperty("SPECMATIC_GENERATIVE_TESTS", "true");

        DB.INSTANCE.resetDB();

        context = SpringApplication.run(Application.class);
    }

    @AfterAll
    public static void tearDown() {
        SpringApplication.exit(context, () -> 0);
//        context.close();
    }
}
