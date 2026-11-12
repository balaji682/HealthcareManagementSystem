package com.balaji.healthcare;
import jakarta.persistence.*; import java.math.*; import java.time.*;
@Entity @Table(name="billing")
public class Billing {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @OneToOne @JoinColumn(name="appointment_id", nullable=false, unique=true) public Appointment appointment;
 public BigDecimal amount; public String status; public LocalDateTime billing_date;
 public Billing() {}
}
