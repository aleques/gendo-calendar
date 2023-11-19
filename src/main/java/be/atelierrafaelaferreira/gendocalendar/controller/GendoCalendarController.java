package be.atelierrafaelaferreira.gendocalendar.controller;

import be.atelierrafaelaferreira.gendocalendar.model.EmployeeIcsEnum;
import be.atelierrafaelaferreira.gendocalendar.service.GendoCalendarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class GendoCalendarController {

    private GendoCalendarService gendoCalendarService;

    @GetMapping("/rafa.ics")
    public @ResponseBody ResponseEntity<byte[]> getFixedIcsRafa() throws IOException {
        return getIcsWithUpdatedDates(EmployeeIcsEnum.RAFA);
    }

    @GetMapping("/pammella.ics")
    public @ResponseBody ResponseEntity<byte[]> getFixedIcsPammella() throws IOException {
        return getIcsWithUpdatedDates(EmployeeIcsEnum.PAMMELLA);
    }

    @GetMapping("/maria.ics")
    public @ResponseBody ResponseEntity<byte[]> getFixedIcsMaria() throws IOException {
        return getIcsWithUpdatedDates(EmployeeIcsEnum.MARIA);
    }

    @GetMapping("/rhoxany.ics")
    public @ResponseBody ResponseEntity<byte[]> getFixedIcsRhoxany() throws IOException {
        return getIcsWithUpdatedDates(EmployeeIcsEnum.RHOXANY);
    }

    @GetMapping("/kheylla.ics")
    public @ResponseBody ResponseEntity<byte[]> getFixedIcsKheylla() throws IOException {
        return getIcsWithUpdatedDates(EmployeeIcsEnum.KHEYLLA);
    }

    @GetMapping("/roberta.ics")
    public @ResponseBody ResponseEntity<byte[]> getFixedIcsRoberta() throws IOException {
        return getIcsWithUpdatedDates(EmployeeIcsEnum.ROBERTA);
    }

    private ResponseEntity<byte[]> getIcsWithUpdatedDates(EmployeeIcsEnum employeeIcsEnum) throws IOException {
        final byte[] fileInBytes = gendoCalendarService.getIcsWithUpdatedDates(employeeIcsEnum)
                                    .getBytes(StandardCharsets.UTF_8);
        String fileName = employeeIcsEnum.getNewIcsName();
        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                // Content-Type
                .contentType(new MediaType("text", "x-vCalendar"))
                // Contet-Length
                .contentLength(fileInBytes.length)
                .body(fileInBytes);
    }
}
