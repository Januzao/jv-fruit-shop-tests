package core.basesyntax.strategy.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;
import core.basesyntax.strategy.OperationStrategy;
import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OperationStrategyImplTest {
    @Test
    void methodArgumentNull_throwsNullPointerException() {
        Map<FruitTransaction.Operation, OperationHandler> handlerMap
                = new EnumMap<>(FruitTransaction.Operation.class);

        OperationStrategy operationStrategy = new OperationStrategyImpl(handlerMap);
        assertThrows(NullPointerException.class,
                () -> operationStrategy.getOperationHandler(null));
    }

    @Test
    void operationHandlerNullCheck_throwsException() {
        Map<FruitTransaction.Operation, OperationHandler> handlerMap
                = new EnumMap<>(FruitTransaction.Operation.class);

        OperationStrategy operationStrategy = new OperationStrategyImpl(handlerMap);

        FruitTransaction.Operation operation = FruitTransaction.Operation.BALANCE;

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> operationStrategy.getOperationHandler(operation));
    }

    @Test
    void getOperationHandler_validOperation_returnsHandler() {
        Map<FruitTransaction.Operation, OperationHandler> handlerMap
                = new EnumMap<>(FruitTransaction.Operation.class);

        FruitTransaction.Operation operation = FruitTransaction.Operation.BALANCE;

        OperationHandler handler = new BalanceOperation();

        handlerMap.put(operation, handler);

        OperationStrategy operationStrategy = new OperationStrategyImpl(handlerMap);

        OperationHandler actual = operationStrategy.getOperationHandler(operation);

        assertEquals(handler, actual);
    }
}
