package io.nology.todo_app.todopost;

import jakarta.validation.constraints.NotBlank;

public class UpdateTodoPostDTO {
    @NotBlank
    private String title;
    
    @NotBlank
    private String content;

    private boolean completed;
    
    private int position;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "UpdateTodoPostDTO [title=" + title + ", content=" + content + ", completed=" + completed + ", position=" + position + "]";
    }
}
