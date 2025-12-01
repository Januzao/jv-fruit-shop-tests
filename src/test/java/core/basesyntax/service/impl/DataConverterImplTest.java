package core.basesyntax.service.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConverter;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DataConverterImplTest {
    @Test
    void testCheckParts_ofFile_must_be_three_OK() {
        DataConverter dataConverter = new DataConverterImpl();
        List<String> lines = List.of("b banana 20");

        List<FruitTransaction> fruitTransactions = dataConverter.convertToTransaction(lines);
        Assertions.assertEquals(1, fruitTransactions.size());

        FruitTransaction fruitTransaction = fruitTransactions.get(0);
        Assertions.assertEquals(FruitTransaction.Operation.BALANCE,
                fruitTransaction.getOperation());
        Assertions.assertEquals("banana", fruitTransaction.getFruit());
        Assertions.assertEquals(20, fruitTransaction.getQuantity());
    }

    @Test
    void convertToTransaction_lessColumns_throwsException() {
        DataConverter dataConverter = new DataConverterImpl();
        List<String> lines = List.of("b banana");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(lines));

    }

    @Test
    void convertToTransaction_moreColumns_throwsException() {
        DataConverter dataConverter = new DataConverterImpl();
        List<String> lines = List.of("b banana 20 e");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(lines));

    }

    @Test
    void convertToTransaction_invalidQuantityFormat_throwsException() {
        DataConverter dataConverter = new DataConverterImpl();
        List<String> lines = List.of("b banana20 e");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(lines));

    }

    @Test
    void convertToTransaction_comaSeparator_throwsException() {
        DataConverter dataConverter = new DataConverterImpl();
        List<String> lines = List.of("b, banana 20");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(lines));

    }

    @Test
    void testOperation_Ok() {
        DataConverter dataConverter = new DataConverterImpl();
        List<String> lines = List.of("b banana 20");

        List<FruitTransaction> fruitTransactions = dataConverter.convertToTransaction(lines);
        FruitTransaction fruitTransaction = fruitTransactions.get(0);
        Assertions.assertEquals(FruitTransaction.Operation.BALANCE,
                fruitTransaction.getOperation());
    }

    @Test
    void testOperation_invalidOperation_throwsException() {
        DataConverter dataConverter = new DataConverterImpl();
        List<String> lines = List.of("x banana 20");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(lines));
    }

    @Test
    void testFruit_Ok() {
        DataConverter dataConverter = new DataConverterImpl();
        List<String> lines = List.of("b banana 20");

        List<FruitTransaction> fruitTransactions = dataConverter.convertToTransaction(lines);
        FruitTransaction fruitTransaction = fruitTransactions.get(0);
        Assertions.assertEquals("banana", fruitTransaction.getFruit());
    }

    @Test
    void testQuantity_Ok() {
        DataConverter dataConverter = new DataConverterImpl();
        List<String> lines = List.of("b banana 20");

        List<FruitTransaction> fruitTransactions = dataConverter.convertToTransaction(lines);
        FruitTransaction fruitTransaction = fruitTransactions.get(0);
        Assertions.assertEquals(20, fruitTransaction.getQuantity());
    }

    @Test
    void testQuantity_negative_throwsException() {
        DataConverter dataConverter = new DataConverterImpl();
        List<String> lines = List.of("b banana -1");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(lines));
    }
}
