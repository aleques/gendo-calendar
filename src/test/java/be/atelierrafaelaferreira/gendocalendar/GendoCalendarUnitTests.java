package be.atelierrafaelaferreira.gendocalendar;

import be.atelierrafaelaferreira.gendocalendar.model.EmployeeIcsEnum;
import be.atelierrafaelaferreira.gendocalendar.service.GendoCalendarService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.stream.Collectors;

import static java.time.format.DateTimeFormatter.ISO_INSTANT;

class GendoCalendarUnitTests {

    @Test
    void testUpdate() throws IOException {
        final String icsCalendarFromGendo = new GendoCalendarService(new RestTemplate()).getIcsWithUpdatedDates(EmployeeIcsEnum.RAFA);
        System.out.println(icsCalendarFromGendo);
    }

    /*public String updateDatesOnIcsString(String icsCalendarFromGendo) throws IOException {

        Path path = Paths.get("src/main/resources/test.ics");

        final String fixedIcs = Files.readAllLines(path, StandardCharsets.ISO_8859_1).stream()
                .map(this::calculateLine)
                .collect(Collectors.joining("\r\n"));

        return fixedIcs;
    }*/
}
