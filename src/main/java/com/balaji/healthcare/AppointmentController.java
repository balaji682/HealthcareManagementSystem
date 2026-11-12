package com.balaji.healthcare;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins="*")
public class AppointmentController {
 private final AppointmentRepository repository;
 public AppointmentController(AppointmentRepository repository) { this.repository = repository; }
 @PostMapping public ResponseEntity<Appointment> create(@RequestBody Appointment appointment) { return ResponseEntity.status(201).body(repository.save(appointment)); }
 @GetMapping public List<Appointment> getAll() { return repository.findAll(); }
 @GetMapping("/{id}") public ResponseEntity<Appointment> getById(@PathVariable Long id) { return repository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); }
 @PutMapping("/{id}") public ResponseEntity<Appointment> update(@PathVariable Long id, @RequestBody Appointment updated) {
  return repository.findById(id).map(existing -> { updated.id = id; return ResponseEntity.ok(repository.save(updated)); }).orElseGet(() -> ResponseEntity.notFound().build());
 }
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
  if (!repository.existsById(id)) return ResponseEntity.notFound().build();
  repository.deleteById(id); return ResponseEntity.noContent().build();
 }
}
