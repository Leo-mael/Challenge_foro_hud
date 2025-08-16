package com.leo.challenge_foro_hud.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String estado,
        String autor,
        String curso
) {

    public DatosListadoTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus() ? "ABIERTO" : "CERRADO",
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
