package com.ndifreke.developers;


import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

import static org.junit.Assert.*;


public class DeveloperFactoryUnitTest {

    private static final String PACKAGE_PATH = "/src/test/java/com/ndifreke/developers";
    private DeveloperFactory developerFactory;
    private Map<String, Developer> developersList;

    static void log(Object object) {
        System.out.println(object);
    }

    @Before
    public void init() {
        StringBuilder sb = new StringBuilder();
        try {
            File json = new File(new File(".").getCanonicalPath().concat(PACKAGE_PATH)
                    .concat("/api.json"));
            BufferedReader bufferedReader =  new BufferedReader(new FileReader(json));
            String line;
            while( (line = bufferedReader.readLine()) != null)
                sb.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        developerFactory = DeveloperFactory.createDevelopers(sb.toString());
        developersList = developerFactory.getDevelopers();
    }

    @Test
    public void instantiateDevelopers() {
        assertNotNull("Instantiate Developer", developerFactory);
        assertNotNull(developerFactory.getDevelopers());
    }

    @Test
    public void containsDeveloper() {
        assertEquals("Has three Developer", developersList.size(), 3);

    }

    @Test
    public void developerInformation(){
        assertTrue(developersList.get("ndifreke") instanceof Developer);
        Developer ndifrekeDeveloper = developersList.get("ndifreke");
        assertEquals("Developer getName()", ndifrekeDeveloper.getName(), "ndifreke");
    }
}
