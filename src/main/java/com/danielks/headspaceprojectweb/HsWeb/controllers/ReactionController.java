package com.danielks.headspaceprojectweb.HsWeb.controllers;

import com.danielks.headspaceprojectweb.HsWeb.models.ReactionDTO;
import com.danielks.headspaceprojectweb.HsWeb.services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/reactions")
public class ReactionController {
    private final ReactionService reactionService;

    @Autowired
    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @GetMapping
    public ResponseEntity<List<ReactionDTO>> getAllReactions() {
        List<ReactionDTO> reactions = reactionService.getAllReactions();
        return new ResponseEntity<>(reactions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReactionDTO> getReactionById(@PathVariable UUID id) {
        Optional<ReactionDTO> reaction = reactionService.getReactionById(id);
        return reaction.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ReactionDTO> createReaction(@RequestBody ReactionDTO reactionDTO) {
        ReactionDTO createdReaction = reactionService.createReaction(reactionDTO);
        return new ResponseEntity<>(createdReaction, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReactionDTO> updateReactionType(@PathVariable UUID id,
                                                          @RequestParam String updatedReactType) {
        ReactionDTO updatedReaction = reactionService.updateReactionType(id, updatedReactType);
        if (updatedReaction != null) {
            return new ResponseEntity<>(updatedReaction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReaction(@PathVariable UUID id) {
        reactionService.deleteReaction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
