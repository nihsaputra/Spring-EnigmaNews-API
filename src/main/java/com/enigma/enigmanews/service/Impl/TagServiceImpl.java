package com.enigma.enigmanews.service.Impl;

import com.enigma.enigmanews.entity.Tag;
import com.enigma.enigmanews.model.request.TagRequest;
import com.enigma.enigmanews.model.response.TagResponse;
import com.enigma.enigmanews.repository.TagRepository;
import com.enigma.enigmanews.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Tag> createOrUpdate(TagRequest request) {
        List<Tag> tags = new ArrayList<>();
        for (String tagName : request.getTags()) {
            Optional<Tag> optionalTag = tagRepository.findByName(tagName);
            if (!optionalTag.isPresent()) {
                Tag buildTag = Tag.builder()
                        .name(tagName)
                        .build();
                tags.add(tagRepository.save(buildTag));
            }else {
                tags.add(optionalTag.get());
            }
        }
        return tags;
    }

}
