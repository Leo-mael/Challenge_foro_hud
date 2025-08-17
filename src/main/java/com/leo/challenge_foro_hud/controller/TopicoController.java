package com.leo.challenge_foro_hud.controller;

import com.leo.challenge_foro_hud.domain.curso.Curso;
import com.leo.challenge_foro_hud.domain.curso.CursoRepository;
import com.leo.challenge_foro_hud.domain.topico.*;
import com.leo.challenge_foro_hud.domain.usuario.Usuario;
import com.leo.challenge_foro_hud.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<?> registrado(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,  UriComponentsBuilder uriComponentsBuilder){
        String titulo = datosRegistroTopico.titulo();
        String mensaje = datosRegistroTopico.mensaje();
        if(topicoRepository.existsByTituloAndMensaje(titulo, mensaje))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El título y el mensaje ya existen.");

        Usuario userId = userRepository.findById(datosRegistroTopico.idAutor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Curso cursoId = cursoRepository.findById(datosRegistroTopico.idCurso())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));


        Topico topico = new Topico(datosRegistroTopico, userId, cursoId);
        topicoRepository.save(topico);
        System.out.println("Topico registrado correctamente");

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getAutor().getNombre(), topico.getCurso().getNombre(), topico.getStatus() ? "ABIERTO" : "CERRADO");

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        //return ResponseEntity.status(HttpStatus.CREATED).body("Topico registrado correctamente");
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listar(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
       // return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> retornarDatosTopico(@PathVariable Long id){
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Topico no encontrado"));

        DatosListadoTopico datosTopico = new DatosListadoTopico(topico);
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosListadoTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico,@PathVariable Long id){
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Topico no encontrado"));

        Curso cursoId = cursoRepository.findById(datosActualizarTopico.idCurso())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));

        topico.actualizarDatos(datosActualizarTopico, cursoId);

        DatosListadoTopico datosTopico = new DatosListadoTopico(topico);
        return ResponseEntity.ok(datosTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id){
        //Optional<Topico> topicoOptional = topicoRepository.findById(id);
        //if (topicoOptional.isPresent()) {
        //    topicoRepository.deleteById(id);
        //    return ResponseEntity.noContent().build();
        //}
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tópico no encontrado");
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Topico no encontrado"));

        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }

}
