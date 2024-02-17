package com.myecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name="name")
    private String name;
    
    @Column(name="description")
    private String description;

    @Column(name="imageUrl")
    private String imageUrl;

    @Column(name="price")
    private Float price;

   
    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name="category_name")
    private Categories category;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
   public Categories getCategory() {
        return category;
    }
    public void setCategory(Categories category) {
        this.category = category;
    }   
    
}