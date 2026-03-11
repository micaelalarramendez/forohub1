package forohub.controller;

import forohub.domain.Topico;
import forohub.dto.DatosActualizarTopico;
import forohub.dto.DatosTopico;
import forohub.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository repository;

    public TopicoController(TopicoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<DatosTopico> listar() {
        return repository.findAll().stream().map(DatosTopico::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosTopico> detallar(@PathVariable Long id) {
        return repository.findById(id)
                .map(topico -> ResponseEntity.ok(new DatosTopico(topico)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DatosTopico> crear(@RequestBody DatosActualizarTopico datos) {
        if (datos.titulo() == null || datos.titulo().isBlank() ||
                datos.mensaje() == null || datos.mensaje().isBlank() ||
                datos.autor() == null || datos.autor().isBlank() ||
                datos.curso() == null || datos.curso().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            return ResponseEntity.status(409).build();
        }

        Topico topico = new Topico(datos.titulo(), datos.mensaje(), datos.autor(), datos.curso());
        repository.save(topico);

        return ResponseEntity
                .created(URI.create("/topicos/" + topico.getId()))
                .body(new DatosTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosTopico> actualizar(@PathVariable Long id, @RequestBody DatosActualizarTopico datos) {
        var topicoOptional = repository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (datos.titulo() == null || datos.titulo().isBlank() ||
                datos.mensaje() == null || datos.mensaje().isBlank() ||
                datos.autor() == null || datos.autor().isBlank() ||
                datos.curso() == null || datos.curso().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Topico topico = topicoOptional.get();
        topico.actualizar(datos.titulo(), datos.mensaje(), datos.autor(), datos.curso());

        return ResponseEntity.ok(new DatosTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}