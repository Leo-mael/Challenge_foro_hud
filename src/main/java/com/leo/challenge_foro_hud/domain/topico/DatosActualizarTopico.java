package com.leo.challenge_foro_hud.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        //@NotNull Long id,
        Long idCurso,
        String titulo,
        String mensaje,
        Boolean estatus
) {
}
