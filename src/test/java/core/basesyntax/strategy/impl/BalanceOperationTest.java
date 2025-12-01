package core.basesyntax.strategy.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BalanceOperationTest {
    @Test
    void checkBalance_Ok() {
        OperationHandler operationHandler = new BalanceOperation();
        FruitTransaction fruitTransaction = new FruitTransaction(
                FruitTransaction.Operation.BALANCE, "apple", 10);
        Assertions.assertEquals(10, operationHandler.handle(fruitTransaction, 0));
    }
}
