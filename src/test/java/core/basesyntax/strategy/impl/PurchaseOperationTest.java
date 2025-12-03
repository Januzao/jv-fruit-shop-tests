package core.basesyntax.strategy.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PurchaseOperationTest {
    private OperationHandler operationHandler;

    @BeforeEach
    void setUp() {
        operationHandler = new PurchaseOperation();
    }

    @Test
    void checkPurchase_Ok() {
        FruitTransaction fruitTransaction = new FruitTransaction(
                FruitTransaction.Operation.PURCHASE, "apple", 10);
        assertEquals(0, operationHandler.handle(fruitTransaction, 10));
    }

    @Test
    void checkPurchase_transactionQuantity_isGreater_that_currentQuantity_throwsException() {
        FruitTransaction fruitTransaction = new FruitTransaction(
                FruitTransaction.Operation.PURCHASE, "apple", 10);
        assertThrows(RuntimeException.class,
                () -> operationHandler.handle(fruitTransaction, 5));
    }
}
