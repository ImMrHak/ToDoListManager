package com.tdlm.business.todo.service;

import com.tdlm.business.todo.mapper.TodoMapper;
import com.tdlm.business.todo.record.request.CreateToDoListDTO;
import com.tdlm.business.todo.record.request.GetMyToDoListDTO;
import com.tdlm.business.todo.record.response.ToDoPagedResponseDTO;
import com.tdlm.business.todo.record.response.ToDoResponseDTO;
import com.tdlm.domain.todo.model.ToDo;
import com.tdlm.domain.todo.repository.ToDoDomainRepository;
import com.tdlm.domain.user.repository.UserDomainRepository;
import com.tdlm.kernel.exception.todo.ToDoAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ToDoServiceImp implements ToDoService {
    private final UserDomainRepository userDomainRepository;
    private final ToDoDomainRepository toDoDomainRepository;
    private final TodoMapper todoMapper;

    @Override
    //@Cacheable(cacheNames = "allMyToDo", key = "#getMyToDoListDTO.userName() + ':' + #getMyToDoListDTO.offset() + ':' + #getMyToDoListDTO.pageSize()")
    public ToDoPagedResponseDTO getAllMyToDoList(GetMyToDoListDTO getMyToDoListDTO) {
        Page<ToDo> pageToDos = toDoDomainRepository.findAllByOwner_Username(getMyToDoListDTO.userName(),
                PageRequest.of(getMyToDoListDTO.offset(), getMyToDoListDTO.pageSize()));

        List<ToDoResponseDTO> toDosResponseDTO = pageToDos.getContent().stream().map(todoMapper::toToDoResponseDTO).toList();

        return new ToDoPagedResponseDTO(toDosResponseDTO, pageToDos.getTotalPages(), pageToDos.getTotalElements(), getMyToDoListDTO.offset() + 1, getMyToDoListDTO.pageSize());
    }

    @Override
    public ToDoResponseDTO createMyToDoList(CreateToDoListDTO createToDoListDTO) {
        if(toDoDomainRepository.countByTitleAndOwner_Username(createToDoListDTO.title(), createToDoListDTO.userName()) > 0) throw new ToDoAlreadyExistException("ToDoList Already Exist With The Same Name");

        ToDo newToDo = ToDo.builder()
                .title(createToDoListDTO.title())
                .description(createToDoListDTO.description())
                .owner(userDomainRepository.findByUsername(createToDoListDTO.userName()))
                .build();

        return todoMapper.toToDoResponseDTO(toDoDomainRepository.save(newToDo));
    }
}
