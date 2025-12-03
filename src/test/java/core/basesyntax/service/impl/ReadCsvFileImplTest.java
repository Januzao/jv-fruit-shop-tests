package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.service.ReadCsvFile;
import java.util.Collections;
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
        List<String> expected = List.of(
                "b,banana,20",
                "b,apple,100",
                "s,banana,100",
                "p,banana,13",
                "r,apple,10",
                "p,apple,20",
                "p,banana,5",
                "s,banana,50"
        );
        List<String> actual = readCsvFile.readCsvFile("fruits.csv");
        assertEquals(expected, actual);
    }

    @Test
    void readCsvFile_invalidFile_throwsException() {
        assertThrows(RuntimeException.class,
                () -> readCsvFile.readCsvFile("fruits_invalid.csv"));
    }

    @Test
    void readCsvFile_emptyFile_ok() {
        List<String> actual = readCsvFile.readCsvFile("TestFiles/only_header_file.csv");
        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    void readCsvFile_trailingEmptyLine_ignored() {
        List<String> expected = List.of(
                "b,banana,20",
                "b,apple,100",
                "s,banana,100",
                "p,banana,13",
                "r,apple,10",
                "p,apple,20",
                "p,banana,5",
                "s,banana,50"
        );
        List<String> actual = readCsvFile.readCsvFile("TestFiles/fruits_with_empty_line.csv");
        assertEquals(expected, actual);
    }
}
