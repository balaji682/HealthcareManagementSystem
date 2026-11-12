package com.balaji.healthcare;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.*;
import java.util.*;
@Entity @Table(name="patients")
public class Patient {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 public String name; public LocalDate dob; public String gender; public String contact; public String address;
 public LocalDateTime created_at = LocalDateTime.now();
 @JsonIgnore @OneToMany(mappedBy="patient") public List<Appointment> appointments = new ArrayList<>();
 @JsonIgnore @OneToMany(mappedBy="patient") public List<MedicalRecord> medicalRecords = new ArrayList<>();
 public Patient() {}
}
