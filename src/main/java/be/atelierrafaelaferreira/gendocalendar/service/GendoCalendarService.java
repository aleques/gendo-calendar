package be.atelierrafaelaferreira.gendocalendar.service;

import be.atelierrafaelaferreira.gendocalendar.model.DateType;
import be.atelierrafaelaferreira.gendocalendar.model.EmployeeIcsEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GendoCalendarService {

    private RestTemplate restTemplate;

    public String getIcsWithUpdatedDates(EmployeeIcsEnum employeeIcsEnum) throws IOException {
        final String icsCalendarFromGendo = getIcsCalendarFromGendo(employeeIcsEnum);
        return getIcsWithUpdatedDates(icsCalendarFromGendo);
    }
    private String getIcsCalendarFromGendo(EmployeeIcsEnum employeeIcsEnum) {
        String employeeGendoUrl = employeeIcsEnum.getUrl();
        final String ics = restTemplate.getForObject(employeeGendoUrl, String.class);
        return ics;
    }

    private String getIcsWithUpdatedDates(String icsCalendarFromGendo) throws IOException {
        final String fixedIcs = icsCalendarFromGendo.lines()
                .map(this::calculateLine)
                .collect(Collectors.joining("\r\n"));
        return fixedIcs;
    }

    private String calculateLine(String line) {
         return Arrays.stream(DateType.values())
            .filter(dt -> line.startsWith(dt.toString()))
            .findAny()
            .map(dt -> {
                final String[] lineKeyValuePair = line.split(":");
                final String wrongData = lineKeyValuePair[1];
                String fixedData = getFixedTimeZonedData(wrongData);
                String newLine = lineKeyValuePair[0] + ":" + fixedData;
                return newLine;
            })
            .orElse(line);
    }

    private String getFixedTimeZonedData(String wrongData) {
        final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
        final TemporalAccessor time = df.parse(wrongData);
        final LocalDateTime date = LocalDateTime.from(time);
        final long between = ChronoUnit.HOURS.between(date.atZone(ZoneId.of("Europe/Brussels")), date.atZone(ZoneId.of("America/Sao_Paulo")));

        final String finalDate = date.minusHours(between)
                .atZone(ZoneId.of("UTC"))
                .format(df);

        return finalDate;
    }
}
