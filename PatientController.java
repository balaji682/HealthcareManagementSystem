package com.balaji.healthcare;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins="*")
public class PatientController {
 private final PatientRepository repository;
 public PatientController(PatientRepository repository) { this.repository = repository; }
 @PostMapping public ResponseEntity<Patient> create(@RequestBody Patient patient) { return ResponseEntity.status(201).body(repository.save(patient)); }
 @GetMapping public List<Patient> getAll() { return repository.findAll(); }
 @GetMapping("/{id}") public ResponseEntity<Patient> getById(@PathVariable Long id) { return repository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); }
 @PutMapping("/{id}") public ResponseEntity<Patient> update(@PathVariable Long id, @RequestBody Patient updated) {
  return repository.findById(id).map(existing -> { updated.id = id; return ResponseEntity.ok(repository.save(updated)); }).orElseGet(() -> ResponseEntity.notFound().build());
 }
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
  if (!repository.existsById(id)) return ResponseEntity.notFound().build();
  repository.deleteById(id); return ResponseEntity.noContent().build();
 }
}
