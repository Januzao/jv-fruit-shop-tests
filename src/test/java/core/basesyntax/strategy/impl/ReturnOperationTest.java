package core.basesyntax.strategy.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReturnOperationTest {
    private OperationHandler operationHandler;

    @BeforeEach
    void setUp() {
        operationHandler = new ReturnOperation();
    }

    @Test
    void checkReturnOperation_Ok() {
        FruitTransaction fruitTransaction = new FruitTransaction(
                FruitTransaction.Operation.RETURN, "apple", 10);
        assertEquals(20, operationHandler.handle(fruitTransaction, 10));
    }
}
