package com.enigma.enigmanews.controller;

import com.enigma.enigmanews.entity.UserCredential;
import com.enigma.enigmanews.model.request.ArticleRequest;
import com.enigma.enigmanews.model.request.AuthRequest;
import com.enigma.enigmanews.model.response.ArticleResponse;
import com.enigma.enigmanews.model.response.WebResponse;
import com.enigma.enigmanews.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ArticleRequest request){
        ArticleResponse articleResponse = articleService.create(request);
        WebResponse<ArticleResponse> response = WebResponse.<ArticleResponse>builder()
                                                        .status(HttpStatus.CREATED.getReasonPhrase())
                                                        .message("successfuly create new article")
                                                        .data(articleResponse)
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

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@RequestBody ArticleRequest request,
                                     @PathVariable String id){

        ArticleResponse updateResponse = articleService.update(request, id);

        WebResponse<ArticleResponse> response = WebResponse.<ArticleResponse>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly update article")
                .data(updateResponse)
                .build();
        return ResponseEntity.ok(response);
    }

}
