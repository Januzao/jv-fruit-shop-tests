package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.basesyntax.db.Storage;
import core.basesyntax.db.impl.StorageImpl;
import core.basesyntax.service.ReportGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportGeneratorImplTest {
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
    void fileHeaderCheck_Ok() {
        String report = reportGenerator.getReport();
        String actual = report.split(System.lineSeparator())[0];
        String expected = "fruit,quantity";

        assertEquals(expected, actual);
    }

    @Test
    void reportWithData_Ok() {
        String header = "fruit,quantity";

        storage.put("apple", 10);
        storage.put("banana", 20);

        String report = reportGenerator.getReport();
        String[] lines = report.split(System.lineSeparator());

        assertEquals(header, lines[0]);

        assertTrue("apple,10".equals(lines[1]) || "apple,10".equals(lines[2]));
        assertTrue("banana,20".equals(lines[1]) || "banana,20".equals(lines[2]));
    }

    @Test
    void emptyStorage_reportContainsOnlyHeader() {
        String report = reportGenerator.getReport();
        String[] lines = report.split(System.lineSeparator());

        assertEquals("fruit,quantity", lines[0]);

        assertTrue(lines.length == 1 || (lines.length == 2 && lines[1].isEmpty()));
    }
}
