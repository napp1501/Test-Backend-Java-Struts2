package com.test.service;

import com.test.dao.InventoryDAO;
import com.test.dao.ItemDAO;
import com.test.dao.OrderDAO;
import com.test.model.Item;
import com.test.model.Order;

import java.util.List;

public class OrderService {

    private final String WITHDRAWAL = "W";
    private final String TOPUP = "T";

    private ItemDAO itemDAO;
    private OrderDAO orderDAO;
    private InventoryDAO inventoryDAO;

    // Constructor default (dipakai aplikasi)
    public OrderService() {
        this(new ItemDAO(), new OrderDAO(), new InventoryDAO());
    }

    // Constructor untuk Unit Test
    public OrderService(ItemDAO itemDAO,
                        OrderDAO orderDAO,
                        InventoryDAO inventoryDAO) {
        this.itemDAO = itemDAO;
        this.orderDAO = orderDAO;
        this.inventoryDAO = inventoryDAO;
    }

    public List<Order> getAllOrder() throws Exception {
        return orderDAO.getDataOrder();
    }

    public void saveDataOrder(Integer itemId,
                              Integer qty) throws Exception {

        Integer lastStock = itemDAO.getStock(itemId);
        Item item = itemDAO.getNameById(itemId);

        if (lastStock < qty) {
            throw new Exception("Stock tidak cukup");
        } else {
            int hasil = Math.abs(lastStock - qty);

            orderDAO.saveDataOrder(itemId, qty);
            inventoryDAO.saveData(itemId, qty, WITHDRAWAL);
            itemDAO.editData(itemId, item.getName(), item.getPrice(), hasil);
        }
    }

    public void editDataOrder(Integer id,
                              String orderNo,
                              Integer itemId,
                              Integer qty) throws Exception {

        Integer lastStock = itemDAO.getStock(itemId);
        Item item = itemDAO.getNameById(itemId);
        Order order = orderDAO.getDetailById(id);

        if (order.getQty() < qty) {

            int hasil = Math.abs(order.getQty() - qty);

            if (lastStock < hasil) {
                throw new Exception("Stock tidak cukup");
            } else {

                Integer lastCount = Math.abs(lastStock - hasil);

                orderDAO.editDataOrder(id, orderNo, itemId, qty);
                inventoryDAO.saveData(itemId, hasil, WITHDRAWAL);
                itemDAO.editData(itemId,
                        item.getName(),
                        item.getPrice(),
                        lastCount);
            }

        } else if (order.getQty() > qty) {

            int hasil = Math.abs(order.getQty() - qty);

            Integer lastCount = lastStock + hasil;

            orderDAO.editDataOrder(id, orderNo, itemId, qty);
            inventoryDAO.saveData(itemId, hasil, TOPUP);
            itemDAO.editData(itemId,
                    item.getName(),
                    item.getPrice(),
                    lastCount);
        }
    }

    public void deleteDataItem(Integer id) throws Exception {
        orderDAO.deleteDataOrder(id);
    }
}