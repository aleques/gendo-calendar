package be.atelierrafaelaferreira.gendocalendar;

import be.atelierrafaelaferreira.gendocalendar.model.EmployeeIcsEnum;
import be.atelierrafaelaferreira.gendocalendar.service.GendoCalendarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootTest
class GendoCalendarApplicationTests {

    @Autowired
    private GendoCalendarService gendoCalendarService;

    @Test
    void contextLoads() {
    }

    @Test
    void testUpdate() throws IOException {
        final String icsCalendarFromGendo = gendoCalendarService.getIcsWithUpdatedDates(EmployeeIcsEnum.RAFA);
        System.out.println(icsCalendarFromGendo);
    }
}
