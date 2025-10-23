package com.bienesraices.pagos.api.web;

import com.bienesraices.pagos.api.model.Pago;
import com.bienesraices.pagos.api.repository.PagoRepository;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/pagos", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class PagoController {

    private final PagoRepository repo;

    public PagoController(PagoRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("ok");
    }

    // Listar todos
    @GetMapping
    public List<Pago> list() {
        return repo.findAll();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pago> get(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pago> create(@Valid @RequestBody Pago p) {
        Pago saved = repo.save(p);
        return ResponseEntity
                .created(URI.create("/api/pagos/" + saved.getId()))
                .body(saved);
    }

    // Actualizar
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pago> update(@PathVariable Long id, @Valid @RequestBody Pago p) {
        return repo.findById(id).map(db -> {
            db.setPropiedadId(p.getPropiedadId());
            db.setUsuarioId(p.getUsuarioId());
            db.setMonto(p.getMonto());
            db.setFecha(p.getFecha());
            db.setMetodo(p.getMetodo());
            db.setEstado(p.getEstado());
            return ResponseEntity.ok(repo.save(db));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


