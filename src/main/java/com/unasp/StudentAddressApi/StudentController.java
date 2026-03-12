package com.unasp.StudentAddressApi;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        return studentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("E-mail já cadastrado.");
        }
        Student saved = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Student updated) {
        return studentRepository.findById(id).map(student -> {
            student.setNome(updated.getNome());
            student.setDdd(updated.getDdd());
            student.setTelefone(updated.getTelefone());
            student.setEmail(updated.getEmail());
            student.setCep(updated.getCep());
            student.setLogradouro(updated.getLogradouro());
            student.setBairro(updated.getBairro());
            student.setCidade(updated.getCidade());
            student.setUf(updated.getUf());
            student.setRegiao(updated.getRegiao());
            return ResponseEntity.ok(studentRepository.save(student));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}