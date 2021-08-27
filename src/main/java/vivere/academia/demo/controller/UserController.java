package vivere.academia.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vivere.academia.demo.exceptions.UserLoginAlreadyExistsException;
import vivere.academia.demo.exceptions.UserNotExistsException;
import vivere.academia.demo.models.User;
import vivere.academia.demo.service.interf.IUserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserController(IUserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public void createUser(@RequestBody User user) throws UserLoginAlreadyExistsException {
        userService.createUser(user);
    }


    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable int id) throws UserNotExistsException {
        userService.deleteUserById(id);
    }

    @GetMapping
    public List<User> findByQuery(@RequestParam(required = false) String name, @RequestParam(required = false) String email){
        return userService.findByQuery(Optional.ofNullable(name), Optional.ofNullable(email));
    }

    //In order to use this endpoint you need to change 'Content-type' header to = 'application/json-patch+json' and use the proper patch format
    //The idea of this whole patch thing is to be able to partially update users, never tried this before, seems pretty cool
    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<User> updateCustomer(@PathVariable int id, @RequestBody JsonPatch patch) throws UserNotExistsException {
        try {
            User user = userService.findUserById(id);
            User userPatched = applyPatchToCustomer(patch, user);
            userService.updateUser(userPatched);
            return ResponseEntity.ok(userPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private User applyPatchToCustomer(JsonPatch patch, User targetCustomer) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetCustomer, JsonNode.class));
        return objectMapper.treeToValue(patched, User.class);
    }
}
