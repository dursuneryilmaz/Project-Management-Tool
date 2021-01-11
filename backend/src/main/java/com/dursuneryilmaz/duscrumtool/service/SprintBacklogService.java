package com.dursuneryilmaz.duscrumtool.service;

import com.dursuneryilmaz.duscrumtool.domain.Sprint;
import com.dursuneryilmaz.duscrumtool.domain.SprintBacklog;

public interface SprintBacklogService {
    SprintBacklog createSprintBacklog(SprintBacklog sprintBacklog, Sprint sprint);

    SprintBacklog getSprintBacklogById(String sprintBacklogId);

    SprintBacklog getBySprint(Sprint sprint);

    SprintBacklog updateSprintBacklogById(String sprintBacklogId, SprintBacklog sprintBacklog);

    Boolean deleteSprintBacklogById(String sprintBacklogId);

    SprintBacklog transferTasksToNextSprint(String sprintBacklogId);
}
