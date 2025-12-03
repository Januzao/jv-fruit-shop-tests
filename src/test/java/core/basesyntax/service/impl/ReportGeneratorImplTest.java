package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.db.Storage;
import core.basesyntax.db.impl.StorageImpl;
import core.basesyntax.service.ReportGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportGeneratorImplTest {
    private static final String HEADER = "fruit,quantity";
    private Storage storage;
    private ReportGenerator reportGenerator;

    @BeforeEach
    void setUp() {
        storage = new StorageImpl();
        reportGenerator = new ReportGeneratorImpl(storage);
    }

    @AfterEach
    void tearDown() {
        storage.clear();
    }

    @Test
    void getReport_withData_ok() {
        storage.put("apple", 10);
        storage.put("banana", 20);
        String expected = HEADER + System.lineSeparator()
                + "apple,10" + System.lineSeparator()
                + "banana,20";
        String actual = reportGenerator.getReport();
        assertEquals(sortReport(expected), sortReport(actual));
    }

    @Test
    void getReport_emptyStorage_ok() {
        String expected = HEADER;
        String actual = reportGenerator.getReport();
        assertEquals(expected, actual.trim());
    }

    private String sortReport(String report) {
        String[] lines = report.split(System.lineSeparator());
        if (lines.length <= 1) {
            return report;
        }
        List<String> dataLines = Arrays
                .stream(lines, 1, lines.length)
                .sorted()
                .collect(Collectors.toList());
        List<String> allLines = new ArrayList<>();
        allLines.add(lines[0]);
        allLines.addAll(dataLines);
        return String.join(System.lineSeparator(), allLines);
    }
}
