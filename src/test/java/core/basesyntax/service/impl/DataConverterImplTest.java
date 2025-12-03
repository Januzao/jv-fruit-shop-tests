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
    void convertToTransaction_validInput_ok() {
        List<String> lines = List.of(
                "b,banana,20",
                "s,apple,100"
        );
        List<FruitTransaction> expected = List.of(
                new FruitTransaction(FruitTransaction.Operation.BALANCE, "banana", 20),
                new FruitTransaction(FruitTransaction.Operation.SUPPLY, "apple", 100)
        );
        List<FruitTransaction> actual = dataConverter.convertToTransaction(lines);
        assertEquals(expected, actual);
    }

    @Test
    void convertToTransaction_lessColumns_throwsException() {
        List<String> lines = List.of("b,banana");
        assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(lines));
    }

    @Test
    void convertToTransaction_moreColumns_throwsException() {
        List<String> lines = List.of("b,banana,20,e");
        assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(lines));
    }

    @Test
    void convertToTransaction_invalidQuantityFormat_throwsException() {
        List<String> lines = List.of("b,banana,twenty");
        assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(lines));
    }

    @Test
    void convertToTransaction_invalidOperation_throwsException() {
        List<String> lines = List.of("x,banana,20");
        assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(lines));
    }

    @Test
    void convertToTransaction_negativeQuantity_throwsException() {
        List<String> lines = List.of("b,banana,-1");
        assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(lines));
    }
}
