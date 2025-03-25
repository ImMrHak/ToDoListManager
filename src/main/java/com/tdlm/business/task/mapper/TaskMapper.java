package com.tdlm.business.task.mapper;

import com.tdlm.business.task.record.response.TaskResponseDTO;
import com.tdlm.domain.task.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskResponseDTO toTaskResponseDTO(Task task);
}
