package io.nology.todo_app.todopost;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nology.todo_app.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/todos")
public class TodoPostController {
    private static final Logger logger = LogManager.getLogger(TodoPostController.class);

    @Autowired
    private TodoPostService TodoPostService;

    @Operation (summary = "Create a new post", description = "Create a new post in the todo app")
    @PostMapping()
    public ResponseEntity<TodoPost> createPost(@Valid @RequestBody CreateTodoPostDTO data) {
        TodoPost createdPost = this.TodoPostService.createPost(data);
        logger.info("POST /todos - Created new post with id: {}", createdPost.getId());
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @Tag(name = "get", description =  "GET methods of Todo App API")
    @Operation (summary = "Get all posts", description = "Get all posts in the todo app")
    @GetMapping()
    public ResponseEntity<List<TodoPost>> findAllPosts() {
        List<TodoPost> allPosts = this.TodoPostService.findAllPosts();
        logger.info("GET /todos - Fetched all posts");
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @Tag(name = "get", description =  "GET methods of Todo App API")
    @Operation (summary = "Get a post by id", description = "Get a post in the todo app by its id")
    @GetMapping("/{id}")
    public ResponseEntity<TodoPost> findPostById(@PathVariable Long id) throws NotFoundException {
        Optional<TodoPost> maybePost = this.TodoPostService.findById(id);
        TodoPost foundPost = maybePost.orElseThrow(() -> new NotFoundException(TodoPost.class, id));
        logger.info("GET /todos/{} - Found post", id);
        return new ResponseEntity<>(foundPost, HttpStatus.OK);
    }

    @Operation (summary = "Delete a post by id", description = "Delete a post in the todo app by its id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable Long id) throws NotFoundException {
        boolean isDeleted = this.TodoPostService.deleteById(id);
        if (!isDeleted) {
            throw new NotFoundException(TodoPost.class, id);
        }
        logger.info("DELETE /todos/{} - Deleted post", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Tag(name = "patch", description =  "PATCH methods of Todo App API")
    @Operation (summary = "Update a post by id", description = "Update a post in the todo app by its id")
    @PatchMapping("/{id}")
    public ResponseEntity<TodoPost> updatePost(@PathVariable Long id,
            @RequestBody UpdateTodoPostDTO data)
            throws NotFoundException {
        TodoPost updatedPost = TodoPostService.updatePost(id, data);
        logger.info("PATCH /todos/{} - Updated post", id);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @Tag(name = "patch", description =  "PATCH methods of Todo App API")
    @Operation (summary = "Complete a post by id", description = "Complete a post in the todo app by its id")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoPost> completePost(@PathVariable Long id) throws NotFoundException {
        TodoPost completedPost = TodoPostService.completePost(id);
        logger.info("PATCH /todos/{}/complete - Completed post", id);
        return new ResponseEntity<>(completedPost, HttpStatus.OK);
    }
}