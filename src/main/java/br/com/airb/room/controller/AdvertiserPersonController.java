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


   /* @GetMapping("/{id}/buscar")
    public ResponseEntity<ResponseAdvertiserPersonDto> buscarAnunciantePorId(@PathVariable Long id) {
        ResponseAdvertiserPersonDto advertiser = advertiserPersonService.buscarAnunciantePorId(id);
        return (advertiser != null) ? ResponseEntity.ok(advertiser) : ResponseEntity.notFound().build();
    }*/

    @DeleteMapping("/{id}/excluir")
    public boolean excluirAnunciante(@PathVariable Long id) {
        return advertiserPersonService.excluirAnunciante(id);
    }
}
