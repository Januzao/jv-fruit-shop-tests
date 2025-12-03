package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import core.basesyntax.db.impl.StorageImpl;
import core.basesyntax.service.FileWriter;
import core.basesyntax.service.ReportGenerator;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileWriterImplTest {
    private FileWriter fileWriter;
    private Storage storage;

    @BeforeEach
    void setUp() {
        fileWriter = new FileWriterImpl();
        storage = new StorageImpl();
    }

    @AfterEach
    void tearDown() {
        storage.clear();
    }

    @Test
    void validFileWrite_ok(@TempDir Path tempDir) throws Exception {
        storage.put("banana", 10);
        storage.put("apple", 15);

        ReportGenerator reportGenerator = new ReportGeneratorImpl(storage);
        String expectedReporting = reportGenerator.getReport();

        Path outputFile = tempDir.resolve("correct_file.csv");
        fileWriter.write(expectedReporting, outputFile.toString());

        String actualReport = Files.readString(outputFile);

        assertEquals(expectedReporting, actualReport);
    }

    @Test
    void invalidPath_throwsRuntimeException() {
        String report = "test";
        String invalidPath = "nonexistent_dir/subdir/correct_file.csv";

        assertThrows(RuntimeException.class, () -> fileWriter.write(report,
                invalidPath));
    }
}
