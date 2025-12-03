package core.basesyntax.strategy.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BalanceOperationTest {
    private OperationHandler operationHandler;

    @BeforeEach
    void setUp() {
        operationHandler = new BalanceOperation();
    }

    @Test
    void checkBalance_Ok() {
        FruitTransaction fruitTransaction = new FruitTransaction(
                FruitTransaction.Operation.BALANCE, "apple", 10);
        assertEquals(10, operationHandler.handle(fruitTransaction, 0));
    }
}
