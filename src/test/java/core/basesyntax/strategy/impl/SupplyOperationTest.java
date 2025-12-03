package core.basesyntax.strategy.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SupplyOperationTest {
    private OperationHandler operationHandler;

    @BeforeEach
    void setUp() {
        operationHandler = new SupplyOperation();
    }

    @Test
    void checkSupplyOperation_Ok() {
        FruitTransaction fruitTransaction = new FruitTransaction(
                FruitTransaction.Operation.SUPPLY, "apple", 10);
        assertEquals(20, operationHandler.handle(fruitTransaction, 10));
    }
}
