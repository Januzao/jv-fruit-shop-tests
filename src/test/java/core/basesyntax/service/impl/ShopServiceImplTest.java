package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.db.impl.StorageImpl;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.ShopService;
import core.basesyntax.strategy.OperationHandler;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.impl.BalanceOperation;
import core.basesyntax.strategy.impl.OperationStrategyImpl;
import core.basesyntax.strategy.impl.PurchaseOperation;
import core.basesyntax.strategy.impl.ReturnOperation;
import core.basesyntax.strategy.impl.SupplyOperation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ShopServiceImplTest {
    @Test
    void constructor_nullStrategy_throwsNullPointerException() {
        Storage storage = new StorageImpl();

        Assertions.assertThrows(NullPointerException.class,
                () -> new ShopServiceImpl(null, storage));
    }

    @Test
    void constructor_nullStorage_throwsNullPointerException() {
        OperationStrategy operationStrategy = Mockito.mock(OperationStrategy.class);

        Assertions.assertThrows(NullPointerException.class,
                () -> new ShopServiceImpl(operationStrategy, null));
    }

    @Test
    void process_nullList_throwsNullPointerException() {
        OperationStrategy operationStrategy = operation -> null;
        Storage storage = new StorageImpl();
        ShopService shopService = new ShopServiceImpl(operationStrategy, storage);

        Assertions.assertThrows(NullPointerException.class, () -> shopService.process(null));
    }

    @Test
    void process_listContainsNull_throwsNullPointerException() {
        Map<FruitTransaction.Operation, OperationHandler> operationHandlers = new HashMap<>();
        operationHandlers.put(FruitTransaction.Operation.BALANCE, new BalanceOperation());
        operationHandlers.put(FruitTransaction.Operation.PURCHASE, new PurchaseOperation());
        operationHandlers.put(FruitTransaction.Operation.RETURN, new ReturnOperation());
        operationHandlers.put(FruitTransaction.Operation.SUPPLY, new SupplyOperation());

        OperationStrategy operationStrategy = new OperationStrategyImpl(operationHandlers);
        Storage storage = new StorageImpl();

        ShopService shopService = new ShopServiceImpl(operationStrategy, storage);

        List<FruitTransaction> list = Arrays.asList(
                new FruitTransaction(FruitTransaction.Operation.BALANCE, "apple", 100),
                null
        );

        Assertions.assertThrows(NullPointerException.class, () -> shopService.process(list));
    }

    @Test
    void process_validTransactions_updatesStorageCorrectly() {
        Storage storage = new StorageImpl();
        storage.put("banana", 10);

        Map<FruitTransaction.Operation, OperationHandler> operationHandlers = new HashMap<>();
        operationHandlers.put(FruitTransaction.Operation.BALANCE, new BalanceOperation());
        operationHandlers.put(FruitTransaction.Operation.PURCHASE, new PurchaseOperation());
        operationHandlers.put(FruitTransaction.Operation.RETURN, new ReturnOperation());
        operationHandlers.put(FruitTransaction.Operation.SUPPLY, new SupplyOperation());

        OperationStrategy operationStrategy = new OperationStrategyImpl(operationHandlers);

        ShopService shopService = new ShopServiceImpl(operationStrategy, storage);

        List<FruitTransaction> list = Arrays.asList(
                new FruitTransaction(FruitTransaction.Operation.BALANCE, "banana", 10),
                new FruitTransaction(FruitTransaction.Operation.SUPPLY, "banana", 5),
                new FruitTransaction(FruitTransaction.Operation.PURCHASE, "banana", 3)
        );

        shopService.process(list);
        Assertions.assertEquals(12, storage.get("banana"));
    }
}
