package com.balaji.healthcare;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*; import java.util.*;
@Entity @Table(name="doctors")
public class Doctor {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 public String name; public String specialization; public String phone; public String email;
 @JsonIgnore @OneToMany(mappedBy="doctor") public List<Appointment> appointments = new ArrayList<>();
 public Doctor() {}
}
