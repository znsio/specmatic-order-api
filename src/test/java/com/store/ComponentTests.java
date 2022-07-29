package com.store;

import com.intuit.karate.junit5.Karate;
import com.store.model.DB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ComponentTests {
    @BeforeAll
    public static void setup() {
        DB.INSTANCE.initializeTestData();
    }

    @Karate.Test
    Karate runTests() {
        return new Karate().path("classpath:com/store/componentTests.feature");
    }
}
