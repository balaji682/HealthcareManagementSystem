package com.balaji.healthcare;
import jakarta.persistence.*;
@Entity @Table(name="prescriptions")
public class Prescription {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @OneToOne @JoinColumn(name="appointment_id", nullable=false, unique=true) public Appointment appointment;
 public String medication; public String dosage; public String instructions;
 public Prescription() {}
}
