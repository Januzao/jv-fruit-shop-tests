package core.basesyntax.strategy.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReturnOperationTest {
    @Test
    void checkReturnOperation_Ok() {
        OperationHandler operationHandler = new ReturnOperation();
        FruitTransaction fruitTransaction = new FruitTransaction(
                FruitTransaction.Operation.RETURN, "apple", 10);
        Assertions.assertEquals(20, operationHandler.handle(fruitTransaction, 10));
    }
}
