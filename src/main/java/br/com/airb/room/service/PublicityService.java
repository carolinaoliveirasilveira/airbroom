package br.com.airb.room.service;

import br.com.airb.room.model.Publicity;
import br.com.airb.room.model.dto.RequestPublicityDto;
import br.com.airb.room.model.dto.ResponsePublicityDto;
import br.com.airb.room.repository.PublicityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PublicityService {

    @Autowired
    private PublicityRepository publicityRepository;

    public ResponsePublicityDto criarAnuncio(RequestPublicityDto requestPublicityDto, UUID idAnunciante) {
        Publicity publicity = toPublicityDto(requestPublicityDto);
        publicity.setIdAnunciante(idAnunciante);
        Publicity returnPublicity = publicityRepository.save(publicity);
        ResponsePublicityDto responsePublicityDto = toConvertePublicityParaResponsePublicityDto(returnPublicity);
        return responsePublicityDto;
    }

    private ResponsePublicityDto toConvertePublicityParaResponsePublicityDto(Publicity publicity) {
        ResponsePublicityDto responsePublicityDto =
                new ResponsePublicityDto(
                        publicity.getId(),
                        publicity.getLocalizacao(),
                        publicity.getTamanho(),
                        publicity.getMobiliaDisponivel(),
                        publicity.getQuantidadePessoas(),
                        publicity.getDiaInicio(),
                        publicity.getMesInicio(),
                        publicity.getDiaFim(),
                        publicity.getMesFim(),
                        publicity.isAceitaPets(),
                        publicity.isAceitaCriancas(),
                        publicity.getAcessibilidade(),
                        publicity.getIdAnunciante(),
                        publicity.getDescricao(),
                        publicity.getTitulo());

        return responsePublicityDto;
    }

    private Publicity toPublicityDto(RequestPublicityDto requestPublicityDto) {
        Publicity publicity = new Publicity();
        publicity.setLocalizacao(requestPublicityDto.localizacao());
        publicity.setTamanho(requestPublicityDto.tamanho());
        publicity.setMobiliaDisponivel(requestPublicityDto.mobiliaDisponivel());
        publicity.setQuantidadePessoas(requestPublicityDto.quantidadePessoas());
        publicity.setDiaInicio(requestPublicityDto.diaInicio());
        publicity.setDiaFim(requestPublicityDto.diaFim());
        publicity.setMesFim(requestPublicityDto.mesFim());
        publicity.setAceitaPets(requestPublicityDto.aceitaPets());
        publicity.setAceitaCriancas(requestPublicityDto.aceitaCriancas());
        publicity.setAcessibilidade(requestPublicityDto.acessibilidade());
        publicity.setDescricao(requestPublicityDto.descricao());
        publicity.setTitulo(requestPublicityDto.titulo());
        return publicity;

    }


    public Publicity buscarAnuncioPorId(Long id) {
        return publicityRepository.findById(id).orElse(null);  // Retorna null se não encontrado
    }

    public boolean excluirAnuncio(Long id) {
        if (publicityRepository.existsById(id)) {
            publicityRepository.deleteById(id);
            return true;
        }
        return false;
    }


}


