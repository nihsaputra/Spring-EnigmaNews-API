package com.enigma.enigmanews.repository;

import com.enigma.enigmanews.entity.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleTagRepository extends JpaRepository<ArticleTag, String> {
    Optional<List<ArticleTag>> findByArticle_Id(String articleId);
}
