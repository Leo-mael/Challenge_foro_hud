package com.leo.challenge_foro_hud.domain.topico;

import com.leo.challenge_foro_hud.domain.curso.Curso;
import com.leo.challenge_foro_hud.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topico")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private Boolean status;
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor", nullable = false)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso", nullable = false)
    private Curso curso;

    public Topico(DatosRegistroTopico datos, Usuario autor, Curso curso){
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = autor;
        this.curso = curso;
        this.status = true;
        this.fechaCreacion = LocalDateTime.now();
    }

    public void actualizarDatos(@Valid DatosActualizarTopico datos, Curso curso) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.curso = curso;
        this.status = datos.estatus();
        this.fechaCreacion = LocalDateTime.now();
    }
}
