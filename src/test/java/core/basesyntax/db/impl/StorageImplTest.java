package core.basesyntax.db.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StorageImplTest {
    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = new StorageImpl();
    }

    @AfterEach
    void tearDown() {
        storage.clear();
    }

    @Test
    void get_existingFruit_returnsItsQuantity() {
        storage.put("apple", 10);
        int actual = storage.get("apple");
        assertEquals(10, actual);
    }

    @Test
    void get_absentFruit_returnsZero() {
        int actual = storage.get("pineapple");
        assertEquals(0, actual);
    }

    @Test
    void put_newFruit_savesQuantity() {
        storage.put("banana", 5);
        int actual = storage.get("banana");
        assertEquals(5, actual);
    }

    @Test
    void put_existingFruit_overwritesQuantity() {
        storage.put("orange", 8);
        storage.put("orange", 12);

        int actual = storage.get("orange");
        assertEquals(12, actual);
    }

    @Test
    void put_negativeQuantity_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> storage.put("orange", -5));
    }

    @Test
    void getFruitStore_returnsMapWithSameData() {
        storage.put("apple", 10);
        storage.put("banana", 5);

        Map<String, Integer> actual = storage.getFruitStore();

        assertEquals(2, actual.size());
        assertEquals(10, actual.get("apple"));
        assertEquals(5, actual.get("banana"));

    }

    @Test
    void getFruitStore_returnsDefensiveCopy_notAReferenceToInternalMap() {
        storage.put("banana", 10);

        Map<String, Integer> actual = storage.getFruitStore();
        actual.put("banana", 999);

        assertEquals(10, storage.get("banana"));
    }
}
