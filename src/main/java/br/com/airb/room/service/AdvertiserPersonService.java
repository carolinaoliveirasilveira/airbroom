package br.com.airb.room.service;

import br.com.airb.room.model.AdvertiserPerson;
import br.com.airb.room.model.Contact;
import br.com.airb.room.model.Endereco;
import br.com.airb.room.model.dto.ContatoDto;
import br.com.airb.room.model.dto.EnderecoDto;
import br.com.airb.room.model.dto.RequestAdvertiserPersonDto;
import br.com.airb.room.model.dto.ResponseAdvertiserPersonDto;
import br.com.airb.room.model.enums.ContactEnum;
import br.com.airb.room.repository.AdvertiserPersonRepository;
import br.com.airb.room.repository.ContactRepository;
import br.com.airb.room.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdvertiserPersonService {

    @Autowired
    private AdvertiserPersonRepository advertiserPersonRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

   public ResponseAdvertiserPersonDto criarAnunciante(RequestAdvertiserPersonDto requestAdvertiserPersonDto) {
        AdvertiserPerson advertiserPerson = toAdvertiserDto(requestAdvertiserPersonDto);

       AdvertiserPerson returnAdvertiserPerson = advertiserPersonRepository.save(advertiserPerson);
       ArrayList<Contact> returnContacts = new ArrayList<>();
       requestAdvertiserPersonDto.contatos().forEach(c -> {
           Contact contact = new Contact();
           contact.setTypesContact(ContactEnum.valueOf(c.tipoContato()));
           contact.setContactDescription(c.descricaoContato());
           contact.setIdAnunciante(returnAdvertiserPerson.getId());
           returnContacts.add(contactRepository.save(contact));
       });

       ArrayList<Endereco> returnEnderecos = new ArrayList<>();
       requestAdvertiserPersonDto.endereco().forEach(e -> {
           Endereco endereco = new Endereco();
           endereco.setCidade(e.cidade());
           endereco.setEstado(e.estado());
           endereco.setPais(e.pais());
           returnEnderecos.add(enderecoRepository.save(endereco));
       });

        ResponseAdvertiserPersonDto responseAdvertiserPersonDto = toConverteAdvertiserParaDto(returnAdvertiserPerson, returnContacts, returnEnderecos);

        return responseAdvertiserPersonDto;
    }

    private ResponseAdvertiserPersonDto toConverteAdvertiserParaDto(AdvertiserPerson advertiserPerson, ArrayList<Contact> returnContacts, ArrayList<Endereco> returnEnderecos) {
       ArrayList<ContatoDto> contatoDtos = new ArrayList<>();
       returnContacts.forEach(c -> {
           contatoDtos.add(new ContatoDto(c.getTypesContact().toString(), c.getContactDescription()));
        });

       ArrayList<EnderecoDto> enderecosDto = new ArrayList<>();
       returnEnderecos.forEach(e -> {
           enderecosDto.add(new EnderecoDto(e.getCidade().toString(), e.getEstado().toString(), e.getPais().toString()));
       });


       ResponseAdvertiserPersonDto responseAdvertiserPersonDto =
                new ResponseAdvertiserPersonDto(
                        advertiserPerson.getId(),
                        advertiserPerson.getNome(),
                        advertiserPerson.getCpfOuCnpj(),
                        advertiserPerson.isAtivo(),
                        contatoDtos, enderecosDto);
        return responseAdvertiserPersonDto;
    }

    private AdvertiserPerson toAdvertiserDto(RequestAdvertiserPersonDto requestAdvertiserPersonDto) {
        AdvertiserPerson advertiserPerson = new AdvertiserPerson();
        advertiserPerson.setNome(requestAdvertiserPersonDto.nome());
        advertiserPerson.setCpfOuCnpj(requestAdvertiserPersonDto.cpfOuCnpj());
        advertiserPerson.setAtivo(requestAdvertiserPersonDto.ativo());
        return advertiserPerson;

    }

//    public ResponseAdvertiserPersonDto buscarAnunciantePorId(Long id) {
//        AdvertiserPerson advertiserPerson = advertiserPersonRepository.findById(id).orElse(null);
//        return (advertiserPerson != null) ? toConverteAdvertiserParaDto(advertiserPerson, returnContacts) : null;
//    }


    public boolean excluirAnunciante(Long id) {
        if (advertiserPersonRepository.existsById(id)) {
            advertiserPersonRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
