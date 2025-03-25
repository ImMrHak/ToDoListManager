package com.tdlm.business.task.service;

import com.tdlm.business.task.record.request.CreateMultiTaskDTO;
import com.tdlm.business.task.record.request.CreateTaskDTO;
import com.tdlm.business.task.record.response.TaskResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    TaskResponseDTO createTask(CreateTaskDTO createTaskDTO);
    List<TaskResponseDTO> createMultiTasks(CreateMultiTaskDTO createMultiTaskDTO);
}
