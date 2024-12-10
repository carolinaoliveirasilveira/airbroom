package br.com.airb.room.controller;

import br.com.airb.room.model.Publicity;
import br.com.airb.room.service.PublicityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicityController {
    @Autowired
    private PublicityService publicityService;

    @GetMapping("/publicity/{id}")
    public String exibirDetalhesAnuncio(@PathVariable Long id) {
             return publicityService.mostrarDetalhes(id);
    }

    @GetMapping("/{id}/buscar")
    public ResponseEntity<Publicity> buscarAnuncioPorId(@PathVariable Long id) {
        Publicity publicity = publicityService.buscarAnuncioPorId(id);
        if (publicity == null) {
            return ResponseEntity.notFound().build();  // Retorna 404 se não encontrar o anúncio
        }
        return ResponseEntity.ok(publicity);  // Retorna o objeto Publicity
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> excluirAnuncio(@PathVariable Long id) {
        boolean sucesso = publicityService.excluirAnuncio(id);

        if (sucesso) {
            return ResponseEntity.ok("Anúncio excluído com sucesso!");  // Retorna 200 OK com mensagem
        } else {
            return ResponseEntity.notFound().build();  // Retorna 404 se o anúncio não for encontrado
        }
    }
    }




