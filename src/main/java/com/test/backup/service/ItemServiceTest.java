package com.test.backup.service;

import com.test.dao.InventoryDAO;
import com.test.dao.ItemDAO;
import com.test.dao.OrderDAO;
import com.test.model.Item;

import java.util.List;

public class ItemServiceTest {

    final private String WITHDRAWAL = "W";
    final private String TOPUP = "T";

    private ItemDAO itemDAO;
    private OrderDAO orderDAO;
    private InventoryDAO inventoryDAO;

    // Constructor default untuk aplikasi
    public ItemServiceTest() {
        this(new ItemDAO(), new OrderDAO(), new InventoryDAO());
    }

    // Constructor untuk Unit Test
    public ItemServiceTest(ItemDAO itemDAO,
                           OrderDAO orderDAO,
                           InventoryDAO inventoryDAO) {
        this.itemDAO = itemDAO;
        this.orderDAO = orderDAO;
        this.inventoryDAO = inventoryDAO;
    }

    public List<Item> getAllItem() throws Exception{

        return itemDAO.getAllItems();

    }

    public void saveDataItem(String name,
                             Integer price,
                             Integer stock) throws Exception{

        itemDAO.saveData(name, price, stock);

    }

    public void editDataItem(Integer id,
                          String name,
                          Integer price,
                          Integer stock) throws Exception{

        Integer lastStock = itemDAO.getStock(id);

        if (!lastStock.equals(stock)){
            if (lastStock < stock){
                int hasil = Math.abs(lastStock - stock);
                inventoryDAO.saveData(id, hasil, WITHDRAWAL);
            } else {
                int hasil = Math.abs(lastStock - stock);
                inventoryDAO.saveData(id, hasil, TOPUP);
            }
            itemDAO.editData(id, name, price, stock);
        } else{
            itemDAO.editData(id, name, price, stock);
        }

    }

    public void deleteDataItem(Integer id) throws Exception{

        itemDAO.deleteData(id);
    }
}
