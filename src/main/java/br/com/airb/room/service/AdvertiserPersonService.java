package br.com.airb.room.service;

import br.com.airb.room.model.AdvertiserPerson;
import br.com.airb.room.repository.AdvertiserPersonPepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertiserPersonService {

    @Autowired
    private AdvertiserPersonPepository advertiserPersonRepository;

    public String mostrarDetalhes(Long id) {
        AdvertiserPerson advertiserPerson = buscarAnunciantePorId(id);
        return advertiserPerson != null ? advertiserPerson.mostrarDetalhes() : "Anunciante não encontrado!";
    }

    public AdvertiserPerson criarAnunciante(AdvertiserPerson advertiserPerson) {
        return advertiserPersonRepository.save(advertiserPerson);  // Salva o anunciante no banco
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
