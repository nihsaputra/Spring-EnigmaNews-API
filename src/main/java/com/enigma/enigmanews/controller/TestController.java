package com.enigma.enigmanews.controller;

import com.enigma.enigmanews.entity.Article;
import com.enigma.enigmanews.entity.ArticleTag;
import com.enigma.enigmanews.entity.Author;
import com.enigma.enigmanews.entity.Tag;
import com.enigma.enigmanews.model.request.ArticleRequest;
import com.enigma.enigmanews.model.request.SearchArticleRequest;
import com.enigma.enigmanews.model.request.TagRequest;
import com.enigma.enigmanews.model.response.ArticleResponse;
import com.enigma.enigmanews.model.response.WebResponse;
import com.enigma.enigmanews.service.ArticleService;
import com.enigma.enigmanews.service.ArticleTagService;
import com.enigma.enigmanews.service.AuthorService;
import com.enigma.enigmanews.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/test")
public class TestController {
    private final AuthorService authorService;
    private final TagService tagService;
    private final ArticleService articleService;
    private final ArticleTagService articleTagService;

    @PostMapping
    public List<Tag> create(@RequestBody TagRequest request){
        return tagService.createOrUpdate(request);
    }

    @GetMapping
    public List<?> getListArticle(@RequestParam(required = false) String author,
                                @RequestParam(required = false) String tag){
        SearchArticleRequest articleRequest = SearchArticleRequest.builder()
                .author(author)
                .Tags(tag)
                .build();

        return articleTagService.getAll(articleRequest);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@RequestBody ArticleRequest request, @PathVariable String id){

        ArticleResponse updateResponse = articleService.update(request, id);

        WebResponse<ArticleResponse> response = WebResponse.<ArticleResponse>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly update article")
                .data(updateResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){

        String delete = articleService.delete(id);

        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly delete article")
                .data(delete)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public List<?> getByArticleId(@PathVariable String id){
         return articleTagService.getByArticleId(id);
    }

}
