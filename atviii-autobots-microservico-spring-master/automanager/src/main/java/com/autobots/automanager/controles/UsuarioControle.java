package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.modelos.AdicionadorLinkUsuario;
import com.autobots.automanager.repositorios.RepositorioUsuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControle {

    private final RepositorioUsuario repositorio;
    private final AdicionadorLinkUsuario adicionadorLinkUsuario;

    @Autowired
    public UsuarioControle(RepositorioUsuario repositorio, AdicionadorLinkUsuario adicionadorLinkUsuario) {
        this.repositorio = repositorio;
        this.adicionadorLinkUsuario = adicionadorLinkUsuario;
    }

    @GetMapping
    public List<Usuario> obterUsuarios() {
        List<Usuario> usuarios = repositorio.findAll();
        adicionadorLinkUsuario.adicionarLink(usuarios);
        return usuarios;
    }

    @GetMapping("/{id}")
    public Usuario obterUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = repositorio.findById(id);
        return usuario.orElse(null);
    }

    @PostMapping
    public Usuario adicionarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = repositorio.save(usuario);
        adicionadorLinkUsuario.adicionarLink(novoUsuario);
        return novoUsuario;
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        Optional<Usuario> optionalUsuario = repositorio.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setNomeSocial(usuarioAtualizado.getNomeSocial());
            usuario.setPerfis(usuarioAtualizado.getPerfis());
            Usuario usuarioSalvo = repositorio.save(usuario);
            adicionadorLinkUsuario.adicionarLink(usuarioSalvo);
            return usuarioSalvo;
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void removerUsuario(@PathVariable Long id) {
        repositorio.deleteById(id);
    }
}
