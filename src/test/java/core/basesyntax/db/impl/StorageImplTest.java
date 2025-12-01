package core.basesyntax.db.impl;

import core.basesyntax.db.Storage;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StorageImplTest {
    @Test
    void get_existingFruit_returnsItsQuantity() {
        Storage storage = new StorageImpl();

        storage.put("apple", 10);
        int actual = storage.get("apple");
        Assertions.assertEquals(10, actual);
    }

    @Test
    void get_absentFruit_returnsZero() {
        Storage storage = new StorageImpl();

        int actual = storage.get("pineapple");
        Assertions.assertEquals(0, actual);
    }

    @Test
    void put_newFruit_savesQuantity() {
        Storage storage = new StorageImpl();

        storage.put("banana", 5);
        int actual = storage.get("banana");
        Assertions.assertEquals(5, actual);
    }

    @Test
    void put_existingFruit_overwritesQuantity() {
        Storage storage = new StorageImpl();

        storage.put("orange", 8);
        storage.put("orange", 12);

        int actual = storage.get("orange");
        Assertions.assertEquals(12, actual);
    }

    @Test
    void put_negativeQuantity_throwsException() {
        Storage storage = new StorageImpl();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> storage.put("orange", -5));
    }

    @Test
    void getFruitStore_returnsMapWithSameData() {
        Storage storage = new StorageImpl();

        storage.put("apple", 10);
        storage.put("banana", 5);

        Map<String, Integer> actual = storage.getFruitStore();

        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals(10, actual.get("apple"));
        Assertions.assertEquals(5, actual.get("banana"));

    }

    @Test
    void getFruitStore_returnsDefensiveCopy_notAReferenceToInternalMap() {
        Storage storage = new StorageImpl();

        storage.put("banana", 10);

        Map<String, Integer> actual = storage.getFruitStore();
        actual.put("banana", 999);

        Assertions.assertEquals(10, storage.get("banana"));
    }
}
