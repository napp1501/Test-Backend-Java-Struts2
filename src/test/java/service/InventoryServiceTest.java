package service;

import com.test.dao.InventoryDAO;
import com.test.dao.ItemDAO;
import com.test.dao.OrderDAO;
import com.test.model.Inventory;
import com.test.model.Item;
import com.test.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InventoryServiceTest {

    private ItemDAO itemDAO;
    private OrderDAO orderDAO;
    private InventoryDAO inventoryDAO;

    private InventoryService service;

    @BeforeEach
    void setUp() {
        itemDAO = mock(ItemDAO.class);
        orderDAO = mock(OrderDAO.class);
        inventoryDAO = mock(InventoryDAO.class);

        service = new InventoryService(itemDAO, orderDAO, inventoryDAO);
    }

    @Test
    void testGetAllInventory() throws Exception {
        service.getAllInventory();
        verify(inventoryDAO).getDataInventory();
    }

    @Test
    void testSaveDataInventoryTopUp() throws Exception {

        Item item = new Item();
        item.setName("Laptop");
        item.setPrice(10000000);

        when(itemDAO.getStock(1)).thenReturn(10);
        when(itemDAO.getNameById(1)).thenReturn(item);

        service.saveDataInventory(1, 5, "T");

        verify(inventoryDAO).saveData(1, 5, "T");
        verify(itemDAO).editData(1, "Laptop", 10000000, 15);
    }

    @Test
    void testSaveDataInventoryWithdrawal() throws Exception {

        Item item = new Item();
        item.setName("Laptop");
        item.setPrice(10000000);

        when(itemDAO.getStock(1)).thenReturn(20);
        when(itemDAO.getNameById(1)).thenReturn(item);

        service.saveDataInventory(1, 5, "W");

        verify(inventoryDAO).saveData(1, 5, "W");
        verify(itemDAO).editData(1, "Laptop", 10000000, 15);
    }

    @Test
    void testSaveDataInventoryStockNotEnough() throws Exception{

        when(itemDAO.getStock(1)).thenReturn(2);

        Exception exception = assertThrows(Exception.class, () -> {
            service.saveDataInventory(1, 5, "W");
        });

        assertEquals("Stock tidak cukup", exception.getMessage());

        verify(inventoryDAO, never())
                .saveData(anyInt(), anyInt(), anyString());
    }

    @Test
    void testEditDataInventoryWithdrawal() throws Exception {

        Item item = new Item();
        item.setName("Laptop");
        item.setPrice(10000000);

        Inventory inventory = new Inventory();
        inventory.setQty(5);

        when(itemDAO.getStock(1)).thenReturn(20);
        when(itemDAO.getNameById(1)).thenReturn(item);
        when(inventoryDAO.getDetailById(1)).thenReturn(inventory);

        service.editDataInventory(1, 1, 10, "W");

        verify(inventoryDAO).editData(1, 1, 10, "W");
        verify(itemDAO).editData(1, "Laptop", 10000000, 5);
    }

    @Test
    void testEditDataInventoryTopUp() throws Exception {

        Item item = new Item();
        item.setName("Laptop");
        item.setPrice(10000000);

        Inventory inventory = new Inventory();
        inventory.setQty(5);

        when(itemDAO.getStock(1)).thenReturn(20);
        when(itemDAO.getNameById(1)).thenReturn(item);
        when(inventoryDAO.getDetailById(1)).thenReturn(inventory);

        service.editDataInventory(1, 1, 10, "T");

        verify(inventoryDAO).editData(1, 1, 10, "T");
        verify(itemDAO).editData(1, "Laptop", 10000000, 30);
    }

    @Test
    void testEditDataInventorySameQty() throws Exception {

        Item item = new Item();
        item.setName("Laptop");
        item.setPrice(10000000);

        Inventory inventory = new Inventory();
        inventory.setQty(10);

        when(itemDAO.getStock(1)).thenReturn(20);
        when(itemDAO.getNameById(1)).thenReturn(item);
        when(inventoryDAO.getDetailById(1)).thenReturn(inventory);

        service.editDataInventory(1, 1, 10, "T");

        verify(inventoryDAO).editData(1, 1, 10, "T");
        verify(itemDAO).editData(1, "Laptop", 10000000, 10);
    }

    @Test
    void testDeleteDataItem() throws Exception {
        service.deleteDataItem(1);
        verify(inventoryDAO).deleteData(1);
    }
}