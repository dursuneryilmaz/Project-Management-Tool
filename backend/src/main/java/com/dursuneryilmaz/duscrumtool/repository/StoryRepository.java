package com.dursuneryilmaz.duscrumtool.repository;

import com.dursuneryilmaz.duscrumtool.domain.Epic;
import com.dursuneryilmaz.duscrumtool.domain.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
    Story findByStoryId(String storyId);

    List<Story> findAllByEpic(Epic epic);
}
