package core.basesyntax.strategy.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;
import core.basesyntax.strategy.OperationStrategy;
import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OperationStrategyImplTest {
    private Map<FruitTransaction.Operation, OperationHandler> handlerMap;
    private OperationStrategy operationStrategy;

    @BeforeEach
    void setUp() {
        handlerMap = new EnumMap<>(FruitTransaction.Operation.class);
        operationStrategy = new OperationStrategyImpl(handlerMap);
    }

    @Test
    void operationHandlerNullCheck_throwsException() {
        FruitTransaction.Operation operation = FruitTransaction.Operation.BALANCE;

        assertThrows(IllegalArgumentException.class,
                () -> operationStrategy.getOperationHandler(operation));
    }

    @Test
    void getOperationHandler_validOperation_returnsHandler() {
        FruitTransaction.Operation operation = FruitTransaction.Operation.BALANCE;
        OperationHandler handler = new BalanceOperation();
        handlerMap.put(operation, handler);

        OperationHandler actual = operationStrategy.getOperationHandler(operation);

        assertEquals(handler, actual);
    }
}
