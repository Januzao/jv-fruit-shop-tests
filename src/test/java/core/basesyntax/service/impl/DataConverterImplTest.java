package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConverter;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataConverterImplTest {
    private DataConverter dataConverter;

    @BeforeEach
    void setUp() {
        dataConverter = new DataConverterImpl();
    }

    @Test
    void testCheckParts_ofFile_must_be_three_OK() {
        List<String> lines = List.of("b banana 20");

        List<FruitTransaction> fruitTransactions = dataConverter.convertToTransaction(lines);
        assertEquals(1, fruitTransactions.size());

        FruitTransaction fruitTransaction = fruitTransactions.get(0);
        assertEquals(FruitTransaction.Operation.BALANCE,
                fruitTransaction.getOperation());
        assertEquals("banana", fruitTransaction.getFruit());
        assertEquals(20, fruitTransaction.getQuantity());
    }

    @Test
    void convertToTransaction_lessColumns_throwsException() {
        List<String> lines = List.of("b banana");

        assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(lines));

    }

    @Test
    void convertToTransaction_moreColumns_throwsException() {
        List<String> lines = List.of("b banana 20 e");

        assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(lines));

    }

    @Test
    void convertToTransaction_invalidQuantityFormat_throwsException() {
        List<String> lines = List.of("b banana20 e");

        assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(lines));

    }

    @Test
    void convertToTransaction_comaSeparator_throwsException() {
        List<String> lines = List.of("b, banana 20");

        assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(lines));

    }

    @Test
    void testOperation_Ok() {
        List<String> lines = List.of("b banana 20");

        List<FruitTransaction> fruitTransactions = dataConverter.convertToTransaction(lines);
        FruitTransaction fruitTransaction = fruitTransactions.get(0);
        assertEquals(FruitTransaction.Operation.BALANCE,
                fruitTransaction.getOperation());
    }

    @Test
    void testOperation_invalidOperation_throwsException() {
        List<String> lines = List.of("x banana 20");

        assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(lines));
    }

    @Test
    void testFruit_Ok() {
        List<String> lines = List.of("b banana 20");

        List<FruitTransaction> fruitTransactions = dataConverter.convertToTransaction(lines);
        FruitTransaction fruitTransaction = fruitTransactions.get(0);
        assertEquals("banana", fruitTransaction.getFruit());
    }

    @Test
    void testQuantity_Ok() {
        List<String> lines = List.of("b banana 20");

        List<FruitTransaction> fruitTransactions = dataConverter.convertToTransaction(lines);
        FruitTransaction fruitTransaction = fruitTransactions.get(0);
        assertEquals(20, fruitTransaction.getQuantity());
    }

    @Test
    void testQuantity_negative_throwsException() {
        List<String> lines = List.of("b banana -1");

        assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(lines));
    }
}
