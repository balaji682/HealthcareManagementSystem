package com.balaji.healthcare;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/billing")
@CrossOrigin(origins="*")
public class BillingController {
 private final BillingRepository repository;
 public BillingController(BillingRepository repository) { this.repository = repository; }
 @PostMapping public ResponseEntity<Billing> create(@RequestBody Billing billing) { return ResponseEntity.status(201).body(repository.save(billing)); }
 @GetMapping public List<Billing> getAll() { return repository.findAll(); }
 @GetMapping("/{id}") public ResponseEntity<Billing> getById(@PathVariable Long id) { return repository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); }
 @PutMapping("/{id}") public ResponseEntity<Billing> update(@PathVariable Long id, @RequestBody Billing updated) {
  return repository.findById(id).map(existing -> { updated.id = id; return ResponseEntity.ok(repository.save(updated)); }).orElseGet(() -> ResponseEntity.notFound().build());
 }
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
  if (!repository.existsById(id)) return ResponseEntity.notFound().build();
  repository.deleteById(id); return ResponseEntity.noContent().build();
 }
}
