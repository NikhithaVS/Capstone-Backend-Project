//package com.upgrad.FoodOrderingApp.service.entity;
//
//import org.hibernate.annotations.OnDelete;
//
//import javax.persistence.*;
//import javax.validation.constraints.Size;
//
//@Entity
//@Table(name = "restaurant", schema = "public")
//public class RestaurantEntity {
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
//  @Column(name = "restaurant_name")
//  @Size(max = 50)
//  private String restaurantName;
//
//  @Column(name = "photo_url")
//  @Size(max = 255)
//  private String photoUrl;
//
//  @Column(name = "customer_rating")
//  private Double customerRating;
//
//  @Column(name = "average_price_for_two")
//  private Integer averagePriceForTwo;
//
//  @Column(name = "number_of_customers_rated")
//  private Integer numberOfCustomersRated;
//
//  @OneToOne(fetch = FetchType.EAGER)
//  @JoinColumn(name = "address_id")
//  private AddressEntity address;
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
//  public String getRestaurantName() {
//    return restaurantName;
//  }
//
//  public void setRestaurantName(String restaurantName) {
//    this.restaurantName = restaurantName;
//  }
//
//  public String getPhotoUrl() {
//    return photoUrl;
//  }
//
//  public void setPhotoUrl(String photoUrl) {
//    this.photoUrl = photoUrl;
//  }
//
//  public Double getCustomerRating() {
//    return customerRating;
//  }
//
//  public void setCustomerRating(Double customerRating) {
//    this.customerRating = customerRating;
//  }
//
//  public Integer getAveragePriceForTwo() {
//    return averagePriceForTwo;
//  }
//
//  public void setAveragePriceForTwo(Integer averagePriceForTwo) {
//    this.averagePriceForTwo = averagePriceForTwo;
//  }
//
//  public Integer getNumberOfCustomersRated() {
//    return numberOfCustomersRated;
//  }
//
//  public void setNumberOfCustomersRated(Integer numberOfCustomersRated) {
//    this.numberOfCustomersRated = numberOfCustomersRated;
//  }
//
//  public AddressEntity getAddress() {
//    return address;
//  }
//
//  public void setAddress(AddressEntity address) {
//    this.address = address;
//  }
//}
