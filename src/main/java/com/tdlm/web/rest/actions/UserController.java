package com.tdlm.web.rest.actions;

import com.tdlm.application.todo.record.request.GetMyToDoListDTO;
import com.tdlm.application.todo.service.ToDoService;
import com.tdlm.application.user.service.UserService;
import com.tdlm.web.rest.wrapper.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ToDoService toDoService;

    @GetMapping("/listMyToDo/{offset}/{pageSize}")
    public ResponseEntity<?> myToDoLists(Principal principal, @PathVariable Integer offset, @PathVariable Integer pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(toDoService.getAllMyToDoList(new GetMyToDoListDTO(principal.getName(), offset, pageSize)), HttpStatus.OK));
    }
}
