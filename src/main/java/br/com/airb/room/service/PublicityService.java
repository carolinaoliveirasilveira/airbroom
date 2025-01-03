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

    public ResponsePublicityDto createPublicity(RequestPublicityDto requestPublicityDto, UUID idAdvertiser) {
        Publicity publicity = toPublicityDto(requestPublicityDto);
        publicity.setIdAdvertiser(idAdvertiser);
        Publicity savedPublicity = publicityRepository.save(publicity);
        ResponsePublicityDto responsePublicityDto = convertAdvertising(savedPublicity);
        return responsePublicityDto;
    }

    private ResponsePublicityDto convertAdvertising(Publicity publicity) {
        return new ResponsePublicityDto(
                publicity.getId(),
                publicity.getLocation(),
                publicity.getSize(),
                publicity.getFurnitureAvailable(),
                publicity.getAmountPeople(),
                publicity.getCheckin(),
                publicity.getCheckout(),
                publicity.isAcceptsPets(),
                publicity.isAcceptschildren(),
                publicity.getAccessibility(),
                publicity.getIdAdvertiser(),
                publicity.getPhotos(),
                publicity.getValue(),
                publicity.getDescription(),
                publicity.getTitle()
        );
    }

    private Publicity toPublicityDto(RequestPublicityDto requestPublicityDto) {
        Publicity publicity = new Publicity();
        publicity.setLocation(requestPublicityDto.location());
        publicity.setSize(requestPublicityDto.size());
        publicity.setFurnitureAvailable(requestPublicityDto.furnitureAvailable());
        publicity.setAmountPeople(requestPublicityDto.amountPeople());
        publicity.setCheckin(requestPublicityDto.checkin());
        publicity.setCheckout(requestPublicityDto.checkout());
        publicity.setAcceptsPets(requestPublicityDto.acceptsPets());
        publicity.setAcceptschildren(requestPublicityDto.acceptschildren());
        publicity.setAccessibility(requestPublicityDto.accessibility());
        publicity.setPhotos(requestPublicityDto.photos());
        publicity.setValue(requestPublicityDto.value());
        publicity.setTitle(requestPublicityDto.title());
        publicity.setDescription(requestPublicityDto.description());
        return publicity;
    }

    public Publicity searchAdById(Long id) {
        return publicityRepository.findById(id).orElse(null);
    }

    public boolean deleteAd(Long id) {
        if (publicityRepository.existsById(id)) {
            publicityRepository.deleteById(id);
            return true;
        }
        return false;
    }


}


