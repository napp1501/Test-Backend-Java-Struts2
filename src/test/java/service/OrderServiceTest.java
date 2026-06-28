package service;

import com.test.dao.InventoryDAO;
import com.test.dao.ItemDAO;
import com.test.dao.OrderDAO;
import com.test.model.Item;
import com.test.model.Order;
import com.test.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private ItemDAO itemDAO;
    private OrderDAO orderDAO;
    private InventoryDAO inventoryDAO;

    private OrderService service;

    @BeforeEach
    void setUp() {
        itemDAO = mock(ItemDAO.class);
        orderDAO = mock(OrderDAO.class);
        inventoryDAO = mock(InventoryDAO.class);

        service = new OrderService(itemDAO, orderDAO, inventoryDAO);
    }

    @Test
    void testGetAllOrder() throws Exception {
        service.getAllOrder();
        verify(orderDAO).getDataOrder();
    }

    @Test
    void testSaveDataOrderSuccess() throws Exception {

        Item item = new Item();
        item.setName("Laptop");
        item.setPrice(10000000);

        when(itemDAO.getStock(1)).thenReturn(20);
        when(itemDAO.getNameById(1)).thenReturn(item);

        service.saveDataOrder(1, 5);

        verify(orderDAO).saveDataOrder(1, 5);
        verify(inventoryDAO).saveData(1, 5, "W");
        verify(itemDAO).editData(1, "Laptop", 10000000, 15);
    }

    @Test
    void testSaveDataOrderStockNotEnough() throws Exception{

        when(itemDAO.getStock(1)).thenReturn(2);

        Exception ex = assertThrows(Exception.class, () -> {
            service.saveDataOrder(1, 5);
        });

        assertEquals("Stock tidak cukup", ex.getMessage());

        verify(orderDAO, never()).saveDataOrder(anyInt(), anyInt());
    }

    @Test
    void testEditDataOrderIncreaseQty() throws Exception {

        Item item = new Item();
        item.setName("Laptop");
        item.setPrice(10000000);

        Order order = new Order();
        order.setQty(5);

        when(itemDAO.getStock(1)).thenReturn(20);
        when(itemDAO.getNameById(1)).thenReturn(item);
        when(orderDAO.getDetailById(1)).thenReturn(order);

        service.editDataOrder(1, "ORD001", 1, 10);

        verify(orderDAO).editDataOrder(1, "ORD001", 1, 10);
        verify(inventoryDAO).saveData(1, 5, "W");
        verify(itemDAO).editData(1, "Laptop", 10000000, 15);
    }

    @Test
    void testEditDataOrderDecreaseQty() throws Exception {

        Item item = new Item();
        item.setName("Laptop");
        item.setPrice(10000000);

        Order order = new Order();
        order.setQty(10);

        when(itemDAO.getStock(1)).thenReturn(15);
        when(itemDAO.getNameById(1)).thenReturn(item);
        when(orderDAO.getDetailById(1)).thenReturn(order);

        service.editDataOrder(1, "ORD001", 1, 5);

        verify(orderDAO).editDataOrder(1, "ORD001", 1, 5);
        verify(inventoryDAO).saveData(1, 5, "T");
        verify(itemDAO).editData(1, "Laptop", 10000000, 20);
    }

    @Test
    void testDeleteDataItem() throws Exception {
        service.deleteDataItem(1);
        verify(orderDAO).deleteDataOrder(1);
    }
}