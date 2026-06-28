package com.test.backup.service;

import com.test.dao.InventoryDAO;
import com.test.dao.ItemDAO;
import com.test.dao.OrderDAO;
import com.test.model.Inventory;
import com.test.model.Item;

import java.util.List;

public class InventoryService {

    final private String WITHDRAWAL = "W";
    final private String TOPUP = "T";

    private ItemDAO itemDAO;
    private OrderDAO orderDAO;
    private InventoryDAO inventoryDAO;

    // Constructor default untuk aplikasi
    public InventoryService() {
        this(new ItemDAO(), new OrderDAO(), new InventoryDAO());
    }

    // Constructor untuk Unit Test
    public InventoryService(ItemDAO itemDAO,
                       OrderDAO orderDAO,
                       InventoryDAO inventoryDAO) {
        this.itemDAO = itemDAO;
        this.orderDAO = orderDAO;
        this.inventoryDAO = inventoryDAO;
    }

    public List<Inventory> getAllInventory() throws Exception{

        return inventoryDAO.getDataInventory();

    }

    public void saveDataInventory(Integer itemId,
                                    Integer qty,
                                    String type) throws Exception{

        Integer lastStock = itemDAO.getStock(itemId);

        if ("W".equals(type)){
            if(lastStock < qty){
                throw new Exception("Stock tidak cukup");
            } else{
                int hasil = Math.abs(lastStock - qty);
                inventoryDAO.saveData(itemId, qty, type);
                Item item = itemDAO.getNameById(itemId);
                itemDAO.editData(itemId, item.getName(), item.getPrice(), hasil);
            }
        } else{
            int hasil = lastStock + qty;
            inventoryDAO.saveData(itemId, qty, type);
            Item item = itemDAO.getNameById(itemId);
            itemDAO.editData(itemId, item.getName(), item.getPrice(), hasil);
        }

    }

    public void editDataInventory(Integer id,
                                             Integer itemId,
                                             Integer qty,
                                             String type) throws Exception{

        Integer lastStock = itemDAO.getStock(itemId);
        Inventory inventory = inventoryDAO.getDetailById(id);
        Item item = itemDAO.getNameById(itemId);

        if (!qty.equals(inventory.getQty()) && WITHDRAWAL.equals(type)){
            int hasil = Math.abs(inventory.getQty() - qty);

            if(lastStock < hasil){
                throw new Exception("Stock tidak cukup");
            } else{
                inventoryDAO.editData(id, itemId, qty, type);
                itemDAO.editData(itemId, item.getName(), item.getPrice(), hasil);
            }
        } else if (!qty.equals(inventory.getQty()) && TOPUP.equals(type)){
            int hasil = qty + lastStock;

            inventoryDAO.editData(id, itemId, qty, type);
            itemDAO.editData(itemId, item.getName(), item.getPrice(), hasil);
        } else{
            inventoryDAO.editData(id, itemId, qty, type);
            itemDAO.editData(itemId, item.getName(), item.getPrice(), qty);
        }

    }

    public void deleteDataItem(Integer id) throws Exception{

        inventoryDAO.deleteData(id);
    }

}
