package com.balaji.healthcare;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*; import java.time.*;
@Entity @Table(name="appointments")
public class Appointment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @ManyToOne @JoinColumn(name="patient_id", nullable=false) public Patient patient;
 @ManyToOne @JoinColumn(name="doctor_id", nullable=false) public Doctor doctor;
 public LocalDateTime appointment_date; public String reason; public String status;
 @JsonIgnore @OneToOne(mappedBy="appointment", cascade=CascadeType.ALL) public Prescription prescription;
 @JsonIgnore @OneToOne(mappedBy="appointment", cascade=CascadeType.ALL) public Billing billing;
 public Appointment() {}
}
