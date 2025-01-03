package br.com.airb.room.controller;

import br.com.airb.room.model.Publicity;
import br.com.airb.room.model.dto.RequestPublicityDto;
import br.com.airb.room.model.dto.ResponsePublicityDto;
import br.com.airb.room.service.PublicityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/publicity")
public class PublicityController {
    @Autowired
    private PublicityService publicityService;

    @PostMapping("/{idAdvertiser}/create")
    public ResponseEntity<ResponsePublicityDto> createPublicity(
            @PathVariable UUID idAdvertiser,
            @RequestBody RequestPublicityDto requestPublicityDto) {

        ResponsePublicityDto responsePublicityDto = publicityService.createPublicity(requestPublicityDto, idAdvertiser);

        return new ResponseEntity<>(responsePublicityDto, HttpStatus.CREATED);
    }


    @GetMapping("/{id}/search")
    public ResponseEntity<Publicity> searchAdById(@PathVariable Long id) {
        Publicity publicity = publicityService.searchAdById(id);
        if (publicity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(publicity);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteAd(@PathVariable Long id) {
        boolean success = publicityService.deleteAd(id);

        if (success) {
            return ResponseEntity.ok("Anúncio excluído com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}




