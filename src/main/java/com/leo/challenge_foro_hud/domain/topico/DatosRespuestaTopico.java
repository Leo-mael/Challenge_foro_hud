package com.leo.challenge_foro_hud.domain.topico;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        String idAutor,
        String idCurso,
        String status
) {
}
