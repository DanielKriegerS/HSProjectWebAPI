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
        return new ReactionDTO(
                reaction.getId(),
                reaction.getUser(),
                reaction.getEntityId(),
                reaction.getEntity_type(),
                reaction.getReact_type(),
                reaction.getCreate_time()
        );
    }

    private Reaction convertToEntity(ReactionDTO reactionDTO) {
        return new Reaction(
                reactionDTO.id(),
                reactionDTO.user(),
                reactionDTO.entityId(),
                reactionDTO.entity_type(),
                reactionDTO.react_type(),
                reactionDTO.create_time()
        );
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
            throw new ReactionNotFoundException(id);
        }
    }

    public ReactionDTO createReaction(ReactionDTO reactionDTO) {
        Reaction reaction = convertToEntity(reactionDTO);
        reaction.setCreate_time(LocalDate.now());

        reaction = reactionRepository.save(reaction);
        return convertToDTO(reaction);
    }

    public ReactionDTO updateReactionType(UUID reactionId, String updatedReactType) {
        Optional<ReactionDTO> optionalReactionDTO = getReactionById(reactionId);

        if (optionalReactionDTO.isPresent()) {
            ReactionDTO existingReactionDTO = optionalReactionDTO.get();
            existingReactionDTO = new ReactionDTO(
                    existingReactionDTO.id(),
                    existingReactionDTO.user(),
                    existingReactionDTO.entityId(),
                    existingReactionDTO.entity_type(),
                    updatedReactType,
                    existingReactionDTO.create_time()
            );

            return createReaction(existingReactionDTO);
        }

        throw new InvalidRequestException("Reaction with ID " + reactionId + " does not exist");
    }

    public UUID countReactionsByEntityId(UUID entityId) {
        return reactionRepository.countByEntityId(entityId);
    }

    public void deleteReaction(UUID id) {
        reactionRepository.deleteById(id);
    }
}
