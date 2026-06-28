package service;

import com.test.dao.InventoryDAO;
import com.test.dao.ItemDAO;
import com.test.dao.OrderDAO;
import com.test.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ItemServiceTest {

    private ItemDAO itemDAO;
    private OrderDAO orderDAO;
    private InventoryDAO inventoryDAO;

    private ItemService service;

    @BeforeEach
    void setUp() {

        itemDAO = mock(ItemDAO.class);
        orderDAO = mock(OrderDAO.class);
        inventoryDAO = mock(InventoryDAO.class);

        service = new ItemService(itemDAO, orderDAO, inventoryDAO);
    }

    @Test
    void testSaveDataItem() throws Exception {

        service.saveDataItem("Laptop", 10000000, 10);

        verify(itemDAO).saveData("Laptop", 10000000, 10);
    }

    @Test
    void testGetAllItem() throws Exception {

        service.getAllItem();

        verify(itemDAO).getAllItems();
    }

    @Test
    void testDeleteItem() throws Exception {

        service.deleteDataItem(1);

        verify(itemDAO).deleteData(1);
    }

    @Test
    void testEditItemStockIncrease() throws Exception {

        when(itemDAO.getStock(1)).thenReturn(10);

        service.editDataItem(1,
                "Laptop",
                10000000,
                20);

        verify(inventoryDAO).saveData(1,10,"W");
        verify(itemDAO).editData(1,"Laptop",10000000,20);
    }

    @Test
    void testEditItemStockDecrease() throws Exception {

        when(itemDAO.getStock(1)).thenReturn(20);

        service.editDataItem(1,
                "Laptop",
                10000000,
                10);

        verify(inventoryDAO).saveData(1,10,"T");
        verify(itemDAO).editData(1,"Laptop",10000000,10);
    }

    @Test
    void testEditItemSameStock() throws Exception {

        when(itemDAO.getStock(1)).thenReturn(10);

        service.editDataItem(1,
                "Laptop",
                10000000,
                10);

        verify(itemDAO).editData(1,"Laptop",10000000,10);

        verify(inventoryDAO,never())
                .saveData(anyInt(),anyInt(),anyString());
    }

}