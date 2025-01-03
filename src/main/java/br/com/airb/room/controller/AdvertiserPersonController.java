package br.com.airb.room.controller;

import br.com.airb.room.model.dto.RequestAdvertiserPersonDto;
import br.com.airb.room.model.dto.ResponseAdvertiserPersonDto;
import br.com.airb.room.service.AdvertiserPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/advertisers")
public class AdvertiserPersonController {

    @Autowired
    private AdvertiserPersonService advertiserPersonService;


    @PostMapping("/create")
    public ResponseAdvertiserPersonDto createAdvertiser(@RequestBody RequestAdvertiserPersonDto requestAdvertiserPersonDto) {
        return advertiserPersonService.createAdvertiser(requestAdvertiserPersonDto);
    }

    @DeleteMapping("/{id}/delete")
    public boolean deleteAdvertiser(@PathVariable UUID id) {
        return advertiserPersonService.deleteAdvertiser(id);
    }

}