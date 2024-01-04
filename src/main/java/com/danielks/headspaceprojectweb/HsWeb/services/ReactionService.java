package com.danielks.headspaceprojectweb.HsWeb.services;

import com.danielks.headspaceprojectweb.HsWeb.entities.Reaction;
import com.danielks.headspaceprojectweb.HsWeb.exceptions.InvalidRequestException;
import com.danielks.headspaceprojectweb.HsWeb.exceptions.reaction.ReactionNotFoundException;
import com.danielks.headspaceprojectweb.HsWeb.models.ReactionDTO;
import com.danielks.headspaceprojectweb.HsWeb.repositories.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReactionService {
    @Autowired
    private ReactionRepository reactionRepository;

    private ReactionDTO convertToDTO(Reaction reaction) {
        ReactionDTO reactionDTO = new ReactionDTO();
        reactionDTO.setId(reaction.getId());
        reactionDTO.setUser(reaction.getUser());
        reactionDTO.setEntityId(reaction.getEntityId());
        reactionDTO.setEntity_type(reaction.getEntity_type());
        reactionDTO.setReact_type(reaction.getReact_type());
        reactionDTO.setCreate_time(reaction.getCreate_time());
        return reactionDTO;
    }

    private Reaction convertToEntity(ReactionDTO reactionDTO) {
        Reaction reaction = new Reaction();
        reaction.setId(reactionDTO.getId());
        reaction.setUser(reactionDTO.getUser());
        reaction.setEntityId(reactionDTO.getEntityId());
        reaction.setEntity_type(reactionDTO.getEntity_type());
        reaction.setReact_type(reactionDTO.getReact_type());
        reaction.setCreate_time(reactionDTO.getCreate_time());
        return reaction;
    }

    public List<ReactionDTO> getAllReactions() {
        List<Reaction> reactions = reactionRepository.findAll();
        return reactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ReactionDTO> getReactionById(UUID id) {
        Optional<Reaction> reactionOptional = reactionRepository.findById(id);

        if (reactionOptional.isPresent()) {
            return reactionOptional.map(this::convertToDTO);
        } else {
            throw new ReactionNotFoundException(id); // Lança a exceção se a reação não for encontrada
        }
    }

    public ReactionDTO createReaction(ReactionDTO reactionDTO) {
        Reaction reaction = convertToEntity(reactionDTO);
        reaction.setCreate_time(LocalDate.now());

        reaction = (Reaction) reactionRepository.save(reaction);
        return convertToDTO(reaction);
    }

    public ReactionDTO updateReactionType(UUID reactionId, String updatedReactType) {
        Optional<ReactionDTO> optionalReactionDTO = getReactionById(reactionId);

        if (optionalReactionDTO.isPresent()) {
            ReactionDTO existingReactionDTO = optionalReactionDTO.get();
            existingReactionDTO.setReact_type(updatedReactType);

            return createReaction(existingReactionDTO);
        }

        throw new InvalidRequestException("Reaction with ID " + reactionId + " does not exist");
    }
    public UUID countReactionsByentityId(UUID entityId) {
        return reactionRepository.countByEntityId(entityId);
    }

    public void deleteReaction(UUID id) {
        reactionRepository.deleteById(id);
    }

}
