package com.test.controller;

import com.test.model.Item;
import com.test.service.ItemService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class ItemAction  extends ActionSupport {
    private List<Item> items;
    private String name;
    private Integer price;
    private Integer id;
    private Integer stock;

    private ItemService service = new ItemService();

    public String showItem() throws Exception{

        items = service.getAllItem();

        return "item";

    }

    public String save() throws Exception{

        service.saveDataItem(name, price, stock);
        items = service.getAllItem();

        return "item";
    }

    public String edit() throws Exception{

        service.editDataItem(id, name,price, stock);
        items = service.getAllItem();

        return "item";
    }

    public String delete() throws Exception{

        service.deleteDataItem(id);
        Thread.sleep(2000);
        items = service.getAllItem();
        return "item";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public List<Item> getItems() {
        return items;
    }
}
