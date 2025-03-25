package com.tdlm.business.task.service;

import com.tdlm.business.task.mapper.TaskMapper;
import com.tdlm.business.task.record.request.CreateMultiTaskDTO;
import com.tdlm.business.task.record.request.CreateTaskDTO;
import com.tdlm.business.task.record.response.TaskResponseDTO;
import com.tdlm.domain.task.model.Task;
import com.tdlm.domain.task.repository.TaskDomainRepository;
import com.tdlm.domain.todo.model.ToDo;
import com.tdlm.kernel.exception.task.TaskAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService {
    private final TaskDomainRepository taskDomainRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponseDTO createTask(CreateTaskDTO createTaskDTO) {
        for(Task task : taskDomainRepository.findAllByToDo_ToDoId(createTaskDTO.toDoId()))
            if(task.getTaskName().equals(createTaskDTO.taskName())) throw new TaskAlreadyExistException("Task already exist");

        Task newTask = Task.builder()
                .taskName(createTaskDTO.taskName())
                .taskStatus(createTaskDTO.taskStatus())
                .completed(false)
                .toDo(ToDo.builder().toDoId(createTaskDTO.toDoId()).build()).build();

        return taskMapper.toTaskResponseDTO(taskDomainRepository.save(newTask));
    }

    @Override
    public List<TaskResponseDTO> createMultiTasks(CreateMultiTaskDTO createMultiTaskDTO) {
        for(Task task : taskDomainRepository.findAllByToDo_ToDoId(createMultiTaskDTO.toDoId()))
            for(String nameTask : createMultiTaskDTO.taskNameStatus().keySet())
                if(task.getTaskName().equals(nameTask)) throw new TaskAlreadyExistException("Task already exist");

        List<TaskResponseDTO> taskResponseDTOList = new ArrayList<>();

        for(String nameTask : createMultiTaskDTO.taskNameStatus().keySet()){
            Task newTask = Task.builder()
                    .taskName(nameTask)
                    .taskStatus(createMultiTaskDTO.taskNameStatus().get(nameTask))
                    .completed(false)
                    .toDo(ToDo.builder().toDoId(createMultiTaskDTO.toDoId()).build()).build();

            taskResponseDTOList.add(taskMapper.toTaskResponseDTO(taskDomainRepository.save(newTask)));
        }

        return taskResponseDTOList;
    }
}
