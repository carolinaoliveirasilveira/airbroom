package br.com.airb.room.controller;

import br.com.airb.room.model.AdvertiserPerson;
import br.com.airb.room.model.dto.RequestAdvertiserPersonDto;
import br.com.airb.room.model.dto.ResponseAdvertiserPersonDto;
import br.com.airb.room.service.AdvertiserPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anunciantes")
public class AdvertiserPersonController {

    @Autowired
    private AdvertiserPersonService advertiserPersonService;


    @PostMapping("/criar")
    public ResponseAdvertiserPersonDto criarAnunciante(@RequestBody RequestAdvertiserPersonDto requestAdvertiserPersonDto) {
        return advertiserPersonService.criarAnunciante(requestAdvertiserPersonDto);
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
