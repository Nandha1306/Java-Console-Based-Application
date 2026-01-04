package task;

import user.User;

public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(int id, String userName, String taskName, String status){
        taskRepository.createTask(id, userName, taskName, status);
    }

    public Task getTaskById(int id){
        return taskRepository.getTaskById(id);
    }

    public void getAllTasks(User user){
        taskRepository.getAllTasks(user);
    }

    public void deleteTask(int id){
        taskRepository.deleteTask(id);
    }

    public void updateTaskName(int id, String taskName){
        taskRepository.updateTaskName(id, taskName);
    }

    public void updateStatus(int id, String status){
        taskRepository.updateStatus(id, status);
    }
}
