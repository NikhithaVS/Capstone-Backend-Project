package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Entity
@Table(name = "address", schema = "public")
@NamedQueries(
        @NamedQuery(
                name = "getByAddressID",
                query = "SELECT a FROM AddressEntity a WHERE a.uuid=:addressID and a.active = 1"))
public class AddressEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "uuid")
  @Size(max = 200)
  private String uuid;

  @Column(name = "flat_build_number")
  @Size(max = 255)
  private String flatBuildNumber;

  @Column(name = "locality")
  @Size(max = 255)
  private String locality;

  @Column(name = "city")
  @Size(max = 30)
  private String city;

  @Column(name = "pincode")
  @Size(max = 30)
  private String pincode;

//  @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
//  private List<OrderEntity> orders = new ArrayList<>();

  public CustomerEntity getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerEntity customer) {
    this.customer = customer;
  }

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "state_id")
  private StateEntity state;

  @Column(name = "active")
  private Integer active;

  @ManyToOne
  @JoinTable(
          name = "customer_address",
          joinColumns = {@JoinColumn(name = "address_id")},
          inverseJoinColumns = {@JoinColumn(name = "customer_id")})
  private CustomerEntity customer;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getFlatBuildNumber() {
    return flatBuildNumber;
  }

  public void setFlatBuildNumber(String flatBuildNumber) {
    this.flatBuildNumber = flatBuildNumber;
  }

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPincode() {
    return pincode;
  }

  public void setPincode(String pincode) {
    this.pincode = pincode;
  }

  public StateEntity getState() {
    return state;
  }

  public void setState(StateEntity state) {
    this.state = state;
  }

  public Integer getActive() {
    return active;
  }

  public void setActive(Integer active) {
    this.active = active;
  }
}
