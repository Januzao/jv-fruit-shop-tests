package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.service.ReadCsvFile;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReadCsvFileImplTest {
    private ReadCsvFile readCsvFile;

    @BeforeEach
    void setUp() {
        readCsvFile = new ReadCsvFileImpl();
    }

    @Test
    void readCsvFile_validFile_ok() {
        List<String> lines = readCsvFile.readCsvFile("fruits.csv");

        assertEquals(8, lines.size());
        assertEquals("b banana 20", lines.get(0));
        assertEquals("b apple 100", lines.get(1));
        assertEquals("s banana 100", lines.get(2));
        assertEquals("p banana 13", lines.get(3));
        assertEquals("r apple 10", lines.get(4));
        assertEquals("p apple 20", lines.get(5));
        assertEquals("p banana 5", lines.get(6));
        assertEquals("s banana 50", lines.get(7));
    }

    @Test
    void readCsvFile_invalidFile_throwsException() {
        assertThrows(RuntimeException.class,
                () -> readCsvFile.readCsvFile("fruits_invalid.csv"));
    }

    @Test
    void ifFile_contains_only_header_ok() {
        List<String> lines = readCsvFile.readCsvFile("TestFiles/OnlyHeaderFile.csv");

        assertEquals(0, lines.size());
    }

    @Test
    void readCsvFile_trailingEmptyLine_ignored() {
        List<String> lines = readCsvFile.readCsvFile("TestFiles/fruits_with_empty_line.csv");

        assertEquals(8, lines.size());
        assertEquals("b banana 20", lines.get(0));
        assertEquals("b apple 100", lines.get(1));
        assertEquals("s banana 100", lines.get(2));
        assertEquals("p banana 13", lines.get(3));
        assertEquals("r apple 10", lines.get(4));
        assertEquals("p apple 20", lines.get(5));
        assertEquals("p banana 5", lines.get(6));
        assertEquals("s banana 50", lines.get(7));
    }
}
