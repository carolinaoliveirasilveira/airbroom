package br.com.airb.room.service;

import br.com.airb.room.model.Publicity;
import br.com.airb.room.repository.PublicityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicityService {

    @Autowired
    private PublicityRepository publicityRepository;  // Injeção do repositório

    public String mostrarDetalhes(Long id) {
        // Buscar o objeto Publicity com o ID fornecido
        Publicity publicity = buscarAnuncioPorId(id);

        if (publicity == null) {
            return "Anúncio não encontrado!";
        }

        // Construir os detalhes do anúncio
        return new StringBuilder()
                .append("Endereço: ").append(publicity.getLocalizacao())
                .append("\nTamanho: ").append(publicity.getTamanho()).append(" m²\n")
                .append("Mobilia: ").append(String.join(", ", publicity.getMobiliaDisponivel())).append("\n")
                .append("Capacidade: ").append(publicity.getQuantidadePessoas()).append(" pessoas\n")
                .append("Período: ").append(publicity.getDiaInicio()).append("/").append(publicity.getMesInicio())
                .append(" a ").append(publicity.getDiaFim()).append("/").append(publicity.getMesFim()).append("\n")
                .append("Aceita Pets: ").append(publicity.isAceitaPets() ? "Sim" : "Não").append("\n")
                .append("Aceita Crianças: ").append(publicity.isAceitaCriancas() ? "Sim" : "Não").append("\n")
                .append("Acessibilidade: ").append(publicity.getAcessibilidade() != null ? publicity.getAcessibilidade() : "Não especificado").append("\n")
                .append("Fotos: ").append(String.join(", ", publicity.getFotos())).append("\n")
                .append("Valor: R$").append(publicity.getValor()).append(" por período")
                .toString();
    }

    public Publicity buscarAnuncioPorId(Long id) {
        return publicityRepository.findById(id).orElse(null);  // Retorna null se não encontrado
    }

    public boolean excluirAnuncio(Long id) {
        // Verifica se o anúncio existe
        if (publicityRepository.existsById(id)) {
            publicityRepository.deleteById(id);  // Exclui o anúncio do banco
            return true;  // Retorna verdadeiro se o anúncio foi excluído com sucesso
        }
        return false;  // Retorna falso se o anúncio não for encontrado
    }
}


