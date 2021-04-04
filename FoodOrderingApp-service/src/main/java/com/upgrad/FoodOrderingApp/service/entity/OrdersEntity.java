//package com.upgrad.FoodOrderingApp.service.entity;
//
//import javax.persistence.*;
//import javax.validation.constraints.Size;
//import java.time.ZonedDateTime;
//
//@Entity
//@Table(name = "orders", schema = "public")
//public class OrdersEntity {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @Column(name = "id")
//  private Integer id;
//
//  @Column(name = "uuid")
//  @Size(max = 200)
//  private String uuid;
//
//  @Column(name = "bill")
//  private Double bill;
//
//  @OneToOne(fetch = FetchType.EAGER)
//  @JoinColumn(name = "coupon_id")
//  private CouponEntity coupon;
//
//  @Column(name = "discount")
//  private Integer discount;
//
//  @Column(name = "date")
//  private ZonedDateTime date;
//
//  @OneToOne(fetch = FetchType.EAGER)
//  @JoinColumn(name = "payment_id")
//  private PaymentEntity payment;
//
//  @OneToOne(fetch = FetchType.EAGER)
//  @JoinColumn(name = "customer_id")
//  private CustomerEntity customer;
//
//  @OneToOne(fetch = FetchType.EAGER)
//  @JoinColumn(name = "address_id")
//  private AddressEntity address;
//
//  @OneToOne(fetch = FetchType.EAGER)
//  @JoinColumn(name = "restaurant_id")
//  private RestaurantEntity restaurant;
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
//  public Double getBill() {
//    return bill;
//  }
//
//  public void setBill(Double bill) {
//    this.bill = bill;
//  }
//
//  public CouponEntity getCoupon() {
//    return coupon;
//  }
//
//  public void setCoupon(CouponEntity coupon) {
//    this.coupon = coupon;
//  }
//
//  public Integer getDiscount() {
//    return discount;
//  }
//
//  public void setDiscount(Integer discount) {
//    this.discount = discount;
//  }
//
//  public ZonedDateTime getDate() {
//    return date;
//  }
//
//  public void setDate(ZonedDateTime date) {
//    this.date = date;
//  }
//
//  public PaymentEntity getPayment() {
//    return payment;
//  }
//
//  public void setPayment(PaymentEntity payment) {
//    this.payment = payment;
//  }
//
//  public CustomerEntity getCustomer() {
//    return customer;
//  }
//
//  public void setCustomer(CustomerEntity customer) {
//    this.customer = customer;
//  }
//
//  public AddressEntity getAddress() {
//    return address;
//  }
//
//  public void setAddress(AddressEntity address) {
//    this.address = address;
//  }
//
//  public RestaurantEntity getRestaurant() {
//    return restaurant;
//  }
//
//  public void setRestaurant(RestaurantEntity restaurant) {
//    this.restaurant = restaurant;
//  }
//}
