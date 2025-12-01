package core.basesyntax.service.impl;

import core.basesyntax.service.ReadCsvFile;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReadCsvFileImplTest {
    @Test
    void readCsvFile_validFile_ok() {
        ReadCsvFileImpl readCsvFile = new ReadCsvFileImpl();
        List<String> lines = readCsvFile.readCsvFile("fruits.csv");

        Assertions.assertEquals(8, lines.size());
        Assertions.assertEquals("b banana 20", lines.get(0));
        Assertions.assertEquals("b apple 100", lines.get(1));
        Assertions.assertEquals("s banana 100", lines.get(2));
        Assertions.assertEquals("p banana 13", lines.get(3));
        Assertions.assertEquals("r apple 10", lines.get(4));
        Assertions.assertEquals("p apple 20", lines.get(5));
        Assertions.assertEquals("p banana 5", lines.get(6));
        Assertions.assertEquals("s banana 50", lines.get(7));
    }

    @Test
    void readCsvFile_invalidFile_throwsException() {
        ReadCsvFile readCsvFile = new ReadCsvFileImpl();

        Assertions.assertThrows(RuntimeException.class,
                () -> readCsvFile.readCsvFile("fruits_invalid.csv"));
    }

    @Test
    void ifFile_contains_only_header_ok() {
        ReadCsvFile readCsvFile = new ReadCsvFileImpl();

        List<String> lines = readCsvFile.readCsvFile("TestFiles/OnlyHeaderFile.csv");

        Assertions.assertEquals(0, lines.size());
    }

    @Test
    void readCsvFile_trailingEmptyLine_ignored() {
        ReadCsvFileImpl readCsvFile = new ReadCsvFileImpl();

        List<String> lines = readCsvFile.readCsvFile("TestFiles/fruits_with_empty_line.csv");

        Assertions.assertEquals(8, lines.size());
        Assertions.assertEquals("b banana 20", lines.get(0));
        Assertions.assertEquals("b apple 100", lines.get(1));
        Assertions.assertEquals("s banana 100", lines.get(2));
        Assertions.assertEquals("p banana 13", lines.get(3));
        Assertions.assertEquals("r apple 10", lines.get(4));
        Assertions.assertEquals("p apple 20", lines.get(5));
        Assertions.assertEquals("p banana 5", lines.get(6));
        Assertions.assertEquals("s banana 50", lines.get(7));
    }
}
