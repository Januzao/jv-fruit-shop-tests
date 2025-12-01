package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.db.impl.StorageImpl;
import core.basesyntax.service.FileWriter;
import core.basesyntax.service.ReportGenerator;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileWriterImplTest {
    @Test
    void validFileWrite_ok(@TempDir Path tempDir) throws Exception {
        Storage storage = new StorageImpl();
        storage.put("banana", 10);
        storage.put("apple", 15);

        ReportGenerator reportGenerator = new ReportGeneratorImpl(storage);

        FileWriterImpl fileWriter = new FileWriterImpl();
        String expectedReporting = reportGenerator.getReport();

        Path outputFile = tempDir.resolve("correct_file.csv");
        fileWriter.write(expectedReporting, outputFile.toString());

        String actualReport = Files.readString(outputFile);

        Assertions.assertEquals(expectedReporting, actualReport);
    }

    @Test
    void invalidPath_throwsRuntimeException() {
        FileWriter fileWriter = new FileWriterImpl();

        String report = "test";
        String invalidPath = "nonexistent_dir/subdir/correct_file.csv";

        Assertions.assertThrows(RuntimeException.class, () -> fileWriter.write(report,
                invalidPath));
    }
}
