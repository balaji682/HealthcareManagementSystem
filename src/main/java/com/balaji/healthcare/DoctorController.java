package com.balaji.healthcare;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins="*")
public class DoctorController {
 private final DoctorRepository repository;
 public DoctorController(DoctorRepository repository) { this.repository = repository; }
 @PostMapping public ResponseEntity<Doctor> create(@RequestBody Doctor doctor) { return ResponseEntity.status(201).body(repository.save(doctor)); }
 @GetMapping public List<Doctor> getAll() { return repository.findAll(); }
 @GetMapping("/{id}") public ResponseEntity<Doctor> getById(@PathVariable Long id) { return repository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); }
 @PutMapping("/{id}") public ResponseEntity<Doctor> update(@PathVariable Long id, @RequestBody Doctor updated) {
  return repository.findById(id).map(existing -> { updated.id = id; return ResponseEntity.ok(repository.save(updated)); }).orElseGet(() -> ResponseEntity.notFound().build());
 }
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
  if (!repository.existsById(id)) return ResponseEntity.notFound().build();
  repository.deleteById(id); return ResponseEntity.noContent().build();
 }
}
