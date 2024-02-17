package com.myecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Categories {

    @Id
    private String name;

    //@OneToOne

    //@OneToMany(mappedBy = "category")
    //private List<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
