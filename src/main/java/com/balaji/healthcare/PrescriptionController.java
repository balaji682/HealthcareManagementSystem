package com.balaji.healthcare;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/prescriptions")
@CrossOrigin(origins="*")
public class PrescriptionController {
 private final PrescriptionRepository repository;
 public PrescriptionController(PrescriptionRepository repository) { this.repository = repository; }
 @PostMapping public ResponseEntity<Prescription> create(@RequestBody Prescription prescription) { return ResponseEntity.status(201).body(repository.save(prescription)); }
 @GetMapping public List<Prescription> getAll() { return repository.findAll(); }
 @GetMapping("/{id}") public ResponseEntity<Prescription> getById(@PathVariable Long id) { return repository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); }
 @PutMapping("/{id}") public ResponseEntity<Prescription> update(@PathVariable Long id, @RequestBody Prescription updated) {
  return repository.findById(id).map(existing -> { updated.id = id; return ResponseEntity.ok(repository.save(updated)); }).orElseGet(() -> ResponseEntity.notFound().build());
 }
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
  if (!repository.existsById(id)) return ResponseEntity.notFound().build();
  repository.deleteById(id); return ResponseEntity.noContent().build();
 }
}
