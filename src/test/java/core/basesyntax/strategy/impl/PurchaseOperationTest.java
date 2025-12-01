package core.basesyntax.strategy.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PurchaseOperationTest {
    @Test
    void checkPurchase_Ok() {
        OperationHandler operationHandler = new PurchaseOperation();
        FruitTransaction fruitTransaction = new FruitTransaction(
                FruitTransaction.Operation.PURCHASE, "apple", 10);
        Assertions.assertEquals(0, operationHandler.handle(fruitTransaction, 10));
    }

    @Test
    void checkPurchase_transactionQuantity_isGreater_that_currentQuantity_throwsException() {
        OperationHandler operationHandler = new PurchaseOperation();
        FruitTransaction fruitTransaction = new FruitTransaction(
                FruitTransaction.Operation.PURCHASE, "apple", 10);
        Assertions.assertThrows(RuntimeException.class,
                () -> operationHandler.handle(fruitTransaction, 5));
    }
}
