package io.nology.todo_app.todopost;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import io.nology.todo_app.exceptions.NotFoundException;

@Service
@Transactional
public class TodoPostService {
    private static final Logger logger = LogManager.getLogger(TodoPostService.class);

    @Autowired
    private TodoPostRepository todoPostRepository;

    public TodoPost createPost(CreateTodoPostDTO data) {
        TodoPost newPost = new TodoPost();
        newPost.setTitle(data.getTitle().trim());
        newPost.setContent(data.getContent().trim());
        newPost.setCreatedAt(new Date());
        newPost.setCompleted(false);
        newPost.setPosition(todoPostRepository.count() + 1);
        TodoPost savedPost = todoPostRepository.save(newPost);
        logger.info("Created new TodoPost with id: {}", savedPost.getId());
        return savedPost;
    }

    public List<TodoPost> findAllPosts() {
        // Return all posts ordered by position
        return todoPostRepository.findAll(Sort.by(Sort.Direction.ASC, "position"));
    }

    public Optional<TodoPost> findById(Long id) {
        return todoPostRepository.findById(id);
    }

    public boolean deleteById(Long id) {
        Optional<TodoPost> maybePost = findById(id);
        if (maybePost.isEmpty()) {
            throw new NotFoundException(TodoPost.class, id);
        }
        todoPostRepository.delete(maybePost.get());
        logger.info("Deleted TodoPost with id: {}", id);
        return true;
    }

    public TodoPost updatePost(Long id, UpdateTodoPostDTO data) {
        Optional<TodoPost> maybePost = todoPostRepository.findById(id);

        TodoPost existingPost = maybePost.map(post -> {
            Optional.ofNullable(data.getTitle()).ifPresent(title -> post.setTitle(title.trim()));
            Optional.ofNullable(data.getContent()).ifPresent(content -> post.setContent(content.trim()));
            post.setCompleted(data.isCompleted());  
            post.setPosition(data.getPosition()); 

            return post;

        }).orElseThrow(() -> new NotFoundException(TodoPost.class, id));

        TodoPost updatedPost = todoPostRepository.save(existingPost);
        logger.info("Updated TodoPost with id: {}", id);
        return updatedPost;
    }

    public TodoPost completePost(Long id) {
        Optional<TodoPost> maybePost = todoPostRepository.findById(id);
        if (maybePost.isEmpty()) {
            throw new NotFoundException(TodoPost.class, id);
        }

        TodoPost post = maybePost.get();
        post.setCompleted(true);
        post.setCompletedAt(new Date());
        TodoPost completedPost = todoPostRepository.save(post);
        logger.info("Completed TodoPost with id: {}", id);
        return completedPost;
    }
}
