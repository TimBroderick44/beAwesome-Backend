package io.nology.todo_app.todopost;

import jakarta.validation.constraints.NotBlank;

public class CreateTodoPostDTO {
    private String content;
    @NotBlank
    private String title;
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "CreateTodoPostDTO [content=" + content + ", title=" + title + "]";
    }
}
