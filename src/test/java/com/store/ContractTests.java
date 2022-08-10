package com.store;

import com.store.model.DB;
import in.specmatic.test.SpecmaticJUnitSupport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ContractTests extends SpecmaticJUnitSupport {
    @BeforeAll
    public static void setup() {
        System.setProperty("host", "localhost");
        System.setProperty("port", "8080");

        System.setProperty("environment", "staging");

        DB.INSTANCE.initializeTestData();

        System.setProperty("ENABLE_NEGATIVE_TESTING", "true");
    }
}
