package task;

import exception.TaskNotFoundException;
import user.User;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskRepository {

    ArrayList<Task> tasks = new ArrayList<>();

    public void createTask(int id, String userName, String taskName, String status){
        Task task = new Task(id, userName, taskName, status);
        tasks.add(task);
    }

    public void getAllTasks(User user){
        for (Task task : tasks){
            if(task.getUserName().equals(user.getUserName())){
                System.out.println(task);
            }
        }
    }

    public Task getTaskById(int id){
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new TaskNotFoundException("Task with id " + id + " is not found"));
    }

    public void updateTaskName(int id, String taskName){
        Task task = getTaskById(id);

        if(task == null){
            throw new TaskNotFoundException("Task with id " + id + " is not found");
        }

        task.setTaskName(taskName);
    }

    public void updateStatus(int id, String status){
        Task task = getTaskById(id);

        if(task == null){
            throw new TaskNotFoundException("Task with id " + id + " is not found");
        }

        task.setStatus(status);
    }

    public void deleteTask(int id){
        for(Task task : tasks){
            if(task.getId() == id){
                tasks.remove(task);
                System.out.println("Task deleted successfully");
                return;
            }
        }

        throw new TaskNotFoundException("Task with id " + id + " is not found");
    }
}

