package com.sukhov.android.ainsoft;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import java.util.List;

/**
 * Created by Sukhov on 24.01.2018.
 */

/**
 * Замечательный класс, с которым я разбирался долгое время, как оказалось есть специальные сайты
 * благодаря которым можно автоматически создавать подобную архитектуру, но я все же решил немного
 * углубиться в процесс создания тестового задания(Сухов)
 */

@Root(name = "xml")
public class ProductsFromXml{
    @ElementList(inline = true)
    public List<Products> products;
}
@Root(name = "products")
class Products{
    @ElementList(inline = true)
    public List<Product> product;
}

@Root(name = "product")
class Product {

    @Element(name = "id")
    private int id;
    @Element(name = "name")
    private String name;
    @Element(name = "price")
    private String price;

    public Product() {
    }

    public Product(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public Product(int id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
