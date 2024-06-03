package com.example.League.Application.services;

import com.example.League.Application.models.Commentaire;
import java.util.List;

public interface CommentaireService {
    Commentaire saveCommentaire(Commentaire commentaire);
    List<Commentaire> getCommentairesByGameId(Long gameId);
}
