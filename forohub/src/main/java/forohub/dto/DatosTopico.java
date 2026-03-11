package forohub.dto;

import forohub.domain.Topico;

public record DatosTopico(Long id, String titulo, String mensaje, String autor, String curso) {
    public DatosTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor(), topico.getCurso());
    }
}