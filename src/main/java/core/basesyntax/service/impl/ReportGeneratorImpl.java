package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.service.ReportGenerator;
import java.util.Map;
import java.util.Objects;

public class ReportGeneratorImpl implements ReportGenerator {
    private static final String HEADER = "fruit,quantity";
    private final Storage storage;

    public ReportGeneratorImpl(Storage storage) {
        this.storage = Objects
                .requireNonNull(storage, "Storage cannot be null");
    }

    @Override
    public String getReport() {
        Map<String, Integer> fruitStore = storage.getFruitStore();
        StringBuilder report = new StringBuilder(HEADER);
        report.append(System.lineSeparator());

        for (Map.Entry<String, Integer> entry : fruitStore.entrySet()) {
            String fruit = entry.getKey();
            int quantity = entry.getValue();
            report.append(fruit)
                    .append(",")
                    .append(quantity)
                    .append(System.lineSeparator());
        }
        return report.toString();
    }
}
