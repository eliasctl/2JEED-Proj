package com.example.League.Application.implem;

import com.example.League.Application.models.Commentaire;
import com.example.League.Application.repositories.CommentaireRepository;
import com.example.League.Application.services.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireServiceImpl implements CommentaireService {
    @Autowired
    private CommentaireRepository commentaireRepository;

    @Override
    public Commentaire saveCommentaire(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    @Override
    public List<Commentaire> getCommentairesByGameId(Long gameId) {
        return commentaireRepository.findByGameId(gameId);
    }
}
