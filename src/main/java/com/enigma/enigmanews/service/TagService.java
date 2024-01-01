package com.enigma.enigmanews.service;

import com.enigma.enigmanews.entity.Tag;
import com.enigma.enigmanews.model.request.TagRequest;
import com.enigma.enigmanews.model.response.TagResponse;

import java.util.List;

public interface TagService {
   List<Tag> createOrUpdate(TagRequest request);

}
