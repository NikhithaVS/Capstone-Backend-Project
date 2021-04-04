//package com.upgrad.FoodOrderingApp.service.entity;
//
//import javax.persistence.*;
//import javax.validation.constraints.Size;
//
//@Entity
//@Table(name = "item", schema = "public")
//public class ItemEntity {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @Column(name = "id")
//  private Integer id;
//
//  @Column(name = "uuid")
//  @Size(max = 200)
//  private String uuid;
//
//  @Column(name = "item_name")
//  @Size(max = 30)
//  private String itemName;
//
//  public Integer getId() {
//    return id;
//  }
//
//  public void setId(Integer id) {
//    this.id = id;
//  }
//
//  public String getUuid() {
//    return uuid;
//  }
//
//  public void setUuid(String uuid) {
//    this.uuid = uuid;
//  }
//
//  public String getItemName() {
//    return itemName;
//  }
//
//  public void setItemName(String itemName) {
//    this.itemName = itemName;
//  }
//
//  public Integer getPrice() {
//    return price;
//  }
//
//  public void setPrice(Integer price) {
//    this.price = price;
//  }
//
//  public String getType() {
//    return type;
//  }
//
//  public void setType(String type) {
//    this.type = type;
//  }
//
//  @Column(name = "price")
//  private Integer price;
//
//  @Column(name = "type")
//  private String type;
//}
