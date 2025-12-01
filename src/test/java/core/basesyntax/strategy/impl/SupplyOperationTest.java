package core.basesyntax.strategy.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SupplyOperationTest {
    @Test
    void checkSupplyOperation_Ok() {
        OperationHandler operationHandler = new SupplyOperation();
        FruitTransaction fruitTransaction = new FruitTransaction(
                FruitTransaction.Operation.SUPPLY, "apple", 10);
        Assertions.assertEquals(20, operationHandler.handle(fruitTransaction, 10));
    }
}
