package br.com.airb.room.service;

import br.com.airb.room.model.AdvertiserPerson;
import br.com.airb.room.model.dto.RequestAdvertiserPersonDto;
import br.com.airb.room.model.dto.ResponseAdvertiserPersonDto;
import br.com.airb.room.repository.AdvertiserPersonPepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertiserPersonService {

    @Autowired
    private AdvertiserPersonPepository advertiserPersonRepository;

    public ResponseAdvertiserPersonDto criarAnunciante(RequestAdvertiserPersonDto requestAdvertiserPersonDto) {
        // Converte o DTO recebido para a entidade AdvertiserPerson
        AdvertiserPerson advertiserPerson = toAdvertiserDto(requestAdvertiserPersonDto);

        // Salva a entidade AdvertiserPerson no repositório
        AdvertiserPerson returnAdvertiserPerson = advertiserPersonRepository.save(advertiserPerson);

        // Converte a entidade AdvertiserPerson de volta para um DTO de resposta
        ResponseAdvertiserPersonDto responseAdvertiserPersonDto1 = toConverteAdvertiserParaDto(returnAdvertiserPerson);

        // Retorna o DTO de resposta
        return responseAdvertiserPersonDto1;
    }

    private ResponseAdvertiserPersonDto toConverteAdvertiserParaDto(AdvertiserPerson advertiserPerson) {
        ResponseAdvertiserPersonDto responseAdvertiserPersonDto =
                new ResponseAdvertiserPersonDto(
                        advertiserPerson.getId(),
                        advertiserPerson.getNome(),
                        advertiserPerson.getEmail(),
                        advertiserPerson.getTelefone(),
                        advertiserPerson.getCpfOuCnpj(),
                        advertiserPerson.getEndereco(),
                        advertiserPerson.getCidade(),
                        advertiserPerson.getEstado(),
                        advertiserPerson.getPais(),
                        advertiserPerson.isAtivo());
        return responseAdvertiserPersonDto;
    }

    private AdvertiserPerson toAdvertiserDto(RequestAdvertiserPersonDto requestAdvertiserPersonDto) {
        AdvertiserPerson advertiserPerson = new AdvertiserPerson();
        advertiserPerson.setNome(requestAdvertiserPersonDto.nome());
        advertiserPerson.setEmail(requestAdvertiserPersonDto.email());
        advertiserPerson.setTelefone(requestAdvertiserPersonDto.telefone());
        advertiserPerson.setCpfOuCnpj(requestAdvertiserPersonDto.cpfOuCnpj());
        advertiserPerson.setEndereco(requestAdvertiserPersonDto.endereco());
        advertiserPerson.setCidade(requestAdvertiserPersonDto.cidade());
        advertiserPerson.setPais(requestAdvertiserPersonDto.pais());
        advertiserPerson.setAtivo(requestAdvertiserPersonDto.ativo());
        return advertiserPerson;

    }





    public AdvertiserPerson atualizarAnunciante(Long id, AdvertiserPerson advertiserPerson) {
        if (advertiserPersonRepository.existsById(id)) {
            advertiserPerson.setId(id);  // Define o ID do anunciante para atualização
            return advertiserPersonRepository.save(advertiserPerson);  // Atualiza o anunciante no banco
        }
        return null;  // Retorna null se o anunciante não for encontrado
    }

    public AdvertiserPerson buscarAnunciantePorId(Long id) {
        return advertiserPersonRepository.findById(id).orElse(null);  // Retorna null se não encontrado
    }


    public boolean excluirAnunciante(Long id) {
        if (advertiserPersonRepository.existsById(id)) {
            advertiserPersonRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
