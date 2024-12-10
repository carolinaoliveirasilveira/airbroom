package br.com.airb.room.controller;

import br.com.airb.room.model.AdvertiserPerson;
import br.com.airb.room.service.AdvertiserPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anunciantes")
public class AdvertiserPersonController {

    @Autowired
    private AdvertiserPersonService advertiserPersonService;

    @GetMapping("/{id}/detalhes")
    public String mostrarDetalhes(@PathVariable Long id) {
        return advertiserPersonService.mostrarDetalhes(id);
    }

    @PostMapping("/criar")
    public ResponseEntity<AdvertiserPerson> criarAnunciante(@RequestBody AdvertiserPerson advertiserPerson) {
        AdvertiserPerson createdAdvertiser = advertiserPersonService.criarAnunciante(advertiserPerson);
        return ResponseEntity.ok(createdAdvertiser);  // Retorna o anunciante criado
    }

    @PutMapping("/{id}/atualizar")
    public ResponseEntity<AdvertiserPerson> atualizarAnunciante(@PathVariable Long id, @RequestBody AdvertiserPerson advertiserPerson) {
        AdvertiserPerson updatedAdvertiser = advertiserPersonService.atualizarAnunciante(id, advertiserPerson);
        if (updatedAdvertiser != null) {
            return ResponseEntity.ok(updatedAdvertiser);  // Retorna o anunciante atualizado
        } else {
            return ResponseEntity.notFound().build();  // Retorna 404 se não encontrar o anunciante
        }
    }

    @GetMapping("/{id}/buscar")
    public ResponseEntity<AdvertiserPerson> buscarAnunciantePorId(@PathVariable Long id) {
        return ResponseEntity.of(java.util.Optional.ofNullable(advertiserPersonService.buscarAnunciantePorId(id)));
    }

    @DeleteMapping("/{id}/excluir")
    public boolean excluirAnunciante(@PathVariable Long id) {
        return advertiserPersonService.excluirAnunciante(id);
    }
}
