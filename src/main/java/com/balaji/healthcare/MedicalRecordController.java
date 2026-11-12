package com.balaji.healthcare;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/records")
@CrossOrigin(origins="*")
public class MedicalRecordController {
 private final MedicalRecordRepository repository;
 public MedicalRecordController(MedicalRecordRepository repository) { this.repository = repository; }
 @PostMapping public ResponseEntity<MedicalRecord> create(@RequestBody MedicalRecord medicalRecord) { return ResponseEntity.status(201).body(repository.save(medicalRecord)); }
 @GetMapping public List<MedicalRecord> getAll() { return repository.findAll(); }
 @GetMapping("/{id}") public ResponseEntity<MedicalRecord> getById(@PathVariable Long id) { return repository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); }
 @PutMapping("/{id}") public ResponseEntity<MedicalRecord> update(@PathVariable Long id, @RequestBody MedicalRecord updated) {
  return repository.findById(id).map(existing -> { updated.id = id; return ResponseEntity.ok(repository.save(updated)); }).orElseGet(() -> ResponseEntity.notFound().build());
 }
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
  if (!repository.existsById(id)) return ResponseEntity.notFound().build();
  repository.deleteById(id); return ResponseEntity.noContent().build();
 }
}
