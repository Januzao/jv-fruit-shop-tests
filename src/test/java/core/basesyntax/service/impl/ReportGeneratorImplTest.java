package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.db.impl.StorageImpl;
import core.basesyntax.service.ReportGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReportGeneratorImplTest {
    @Test
    void constructor_nullStorage_throwsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> new ReportGeneratorImpl(null));
    }

    @Test
    void fileHeaderCheck_Ok() {
        Storage storage = new StorageImpl();
        ReportGenerator reportGenerator = new ReportGeneratorImpl(storage);

        String report = reportGenerator.getReport();
        String actual = report.split(System.lineSeparator())[0];
        String expected = "fruit,quantity";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void reportWithData_Ok() {
        String header = "fruit,quantity";

        Storage storage = new StorageImpl();
        storage.put("apple", 10);
        storage.put("banana", 20);
        ReportGenerator reportGenerator = new ReportGeneratorImpl(storage);

        String report = reportGenerator.getReport();
        String[] lines = report.split(System.lineSeparator());

        Assertions.assertEquals(header, lines[0]);

        Assertions.assertTrue("apple,10".equals(lines[1]) || "apple,10".equals(lines[2]));
        Assertions.assertTrue("banana,20".equals(lines[1]) || "banana,20".equals(lines[2]));
    }

    @Test
    void emptyStorage_reportContainsOnlyHeader() {
        Storage storage = new StorageImpl();
        ReportGenerator reportGenerator = new ReportGeneratorImpl(storage);

        String report = reportGenerator.getReport();
        String[] lines = report.split(System.lineSeparator());

        Assertions.assertEquals("fruit,quantity", lines[0]);

        Assertions.assertTrue(lines.length == 1 || (lines.length == 2 && lines[1].isEmpty()));
    }
}
