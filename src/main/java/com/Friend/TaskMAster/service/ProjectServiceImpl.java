package com.Friend.TaskMAster.service;

import com.Friend.TaskMAster.model.Chat;
import com.Friend.TaskMAster.model.Project;
import com.Friend.TaskMAster.model.User;
import com.Friend.TaskMAster.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {



    @Autowired
    private ProjectRepository projectRepository;


    @Autowired
    private  UserService userService;


    @Autowired
    private  ChatService chatService;



    @Override
    public Project createProject(Project project, User user) throws Exception {
        Project createdProject = new Project();
        createdProject.setOwner(user);
        createdProject.setName(project.getName());
        createdProject.setDescription(project.getDescription());
        createdProject.setCategory(project.getCategory());
        createdProject.setTags(project.getTags());
        createdProject.getTeam().add(user);

        Project savedProject = projectRepository.save(createdProject);

        Chat chat = new Chat();
        chat.setProject(savedProject);
        Chat projectChat = chatService.createChat(chat);
        savedProject.setChat(projectChat);
        return savedProject ;
    }

    @Override
    public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
        return List.of();
    }

    @Override
    public Project getProjectById(long projectId) throws Exception {
        return null;
    }

    @Override
    public void deleteProject(long projectId, long userId) throws Exception {

    }

    @Override
    public Project updateProject(Project updatedProject, long id) throws Exception {
        return null;
    }

    @Override
    public void addUserToProject(long projectId, long userId) throws Exception {

    }

    @Override
    public void removeUserFromProject(long projectId, long userId) throws Exception {

    }

    @Override
    public Chat getChatByProjectId(long projectId) throws Exception {
        return null;
    }
}
