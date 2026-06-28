package com.test.controller;

import com.test.model.Inventory;
import com.test.model.Item;
import com.test.service.InventoryService;
import com.test.service.ItemService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InventoryAction  extends ActionSupport {

    private List<Inventory> inventories;
    private List<Item> items;
    private Map<String, String> typeList;
    protected Integer item_id;
    protected Integer qty;
    protected String type;
    protected String name;
    protected  Integer id;

    private InventoryService service = new InventoryService();

    private ItemService itemService = new ItemService();
    


    public String showInventory()throws Exception{

        inventories = service.getAllInventory();

        return "inventory";
    }


    public String saveInventory() throws Exception{
        try {
            service.saveDataInventory(item_id, qty, type);
            inventories = service.getAllInventory();
        }
        catch (Exception e){
            addActionError(e.getMessage());
            inventories = service.getAllInventory();
        }

        return "inventory";
    }

    public String editInventory() throws Exception{

        try {
            service.editDataInventory(id, item_id, qty, type);
            inventories = service.getAllInventory();
        } catch (Exception e){
            addActionError(e.getMessage());
            inventories = service.getAllInventory();
        }

        return "inventory";
    }

    public String deleteInventory() throws Exception{

        service.deleteDataItem(id);
        Thread.sleep(2000);
        inventories = service.getAllInventory();

        return "inventory";
    }



    public Map<String, String> getTypeList() {
        if (typeList == null) {
            typeList = new LinkedHashMap<>();
            typeList.put("T", "Top Up");
            typeList.put("W", "Withdrawal");
        }
        return typeList;
    }

    public List<Item> getItems() throws Exception{
        if (items == null) {
            items = itemService.getAllItem();
        }
        return items;
    }



    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public void setTypeList(Map<String, String> typeList) {
        this.typeList = typeList;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
