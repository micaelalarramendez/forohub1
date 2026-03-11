package forohub.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "topicos")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String mensaje;

    private String autor;

    private String curso;

    public Topico() {}

    public Topico(String titulo, String mensaje, String autor, String curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.autor = autor;
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }

    public void actualizar(String titulo, String mensaje, String autor, String curso) {
        if (titulo != null) this.titulo = titulo;
        if (mensaje != null) this.mensaje = mensaje;
        if (autor != null) this.autor = autor;
        if (curso != null) this.curso = curso;
    }
}