package com.test.controller;

import com.test.model.Item;
import com.test.model.Order;
import com.test.service.ItemService;
import com.test.service.OrderService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class OrderAction  extends ActionSupport {
    private List<Order> orders;
    protected String name;
    protected String orderNo;
    protected  Integer id;
    protected Integer item_id;
    protected Integer qty;
    private List<Item> items;

    private OrderService service = new OrderService();
    private ItemService itemService = new ItemService();

    public String showOrder()throws Exception{

        orders = service.getAllOrder();

        return "order";
    }

    public String saveOrder() throws Exception{

        try{
            service.saveDataOrder(item_id, qty);
            orders = service.getAllOrder();
        } catch (Exception e){
            addActionError(e.getMessage());
            orders = service.getAllOrder();
        }

        return "order";
    }


    public String editOrder() throws Exception{
        try {
            service.editDataOrder(id, orderNo,  item_id, qty);
            orders = service.getAllOrder();
        }
        catch (Exception e){
            addActionError(e.getMessage());
            orders = service.getAllOrder();
        }

        return "order";
    }

    public String deleteOrder() throws Exception{

        service.deleteDataItem(id);
        Thread.sleep(2000);
        orders = service.getAllOrder();

        return "order";
    }

    public List<Item> getItems() throws Exception{
        if (items == null) {
            items = itemService.getAllItem();
        }
        return items;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
