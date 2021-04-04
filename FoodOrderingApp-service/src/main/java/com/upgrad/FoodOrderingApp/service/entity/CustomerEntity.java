package com.upgrad.FoodOrderingApp.service.entity;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "customer", schema = "public")
@NamedQueries({
  @NamedQuery(
      name = "getCustomerByContactNumber",
      query = "select c from CustomerEntity c where c.contactNumber=:contactNumber")
})
public class CustomerEntity implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "uuid")
  @Size(max = 200)
  private String uuid;

  @Size(max = 30)
  @Column(name = "firstname")
  @NotNull
  private String firstName;

  @Column(name = "lastname")
  @Size(max = 30)
  private String lastName;

  @Column(name = "email")
  @Size(max = 50)
  @NotNull
  private String email;

  @Size(max = 30)
  @Column(name = "contact_number")
  @NotNull
  private String contactNumber;

  @Column(name = "password")
  @Size(max = 255)
  @NotNull
  private String password;

  @Column(name = "salt")
  @Size(max = 255)
  private String salt;

//  @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.LAZY)
//  @JoinTable(
//          name = "customer_address",
//          joinColumns = { @JoinColumn(name = "customer_id") },
//          inverseJoinColumns = { @JoinColumn(name = "address_id") }
//  )
  @ManyToMany(cascade = { CascadeType.ALL },mappedBy = "customers",fetch = FetchType.LAZY)
  private List<AddressEntity> addresses;

  public List<AddressEntity> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<AddressEntity> addresses) {
    this.addresses = addresses;
  }

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

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this).hashCode();
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);

  }

}
