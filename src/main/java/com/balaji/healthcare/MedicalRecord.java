package com.balaji.healthcare;
import jakarta.persistence.*; import java.time.*;
@Entity @Table(name="medical_records")
public class MedicalRecord {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @ManyToOne @JoinColumn(name="patient_id", nullable=false) public Patient patient;
 public String diagnosis; public String treatment; public LocalDateTime record_date;
 public MedicalRecord() {}
}
