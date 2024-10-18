package com.Friend.TaskMAster.service;

import com.Friend.TaskMAster.model.Chat;
import com.Friend.TaskMAster.model.Project;
import com.Friend.TaskMAster.model.User;
import com.Friend.TaskMAster.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
        List<Project> projects = projectRepository.findByTeamContainingOrOwner(user , user);

        if (category!=null){
            projects=projects.stream().filter(project -> project.getCategory().equals(category))
                    .collect(Collectors.toList());

        }


        if (tag!=null){
            projects=projects.stream().filter(project -> project.getTags().contains(tag))
                    .collect(Collectors.toList());

        }

        return projects;

    }

    @Override
    public Project getProjectById(long projectId) throws Exception {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()){
            throw new Exception("project not found");
        }

        return optionalProject.get();
    }

    @Override
    public void deleteProject(long projectId, long userId) throws Exception {

        getProjectById(projectId);
        //userService.findUserById(userId);
        projectRepository.deleteById(projectId);

    }

    @Override
    public Project updateProject(Project updatedProject, long id) throws Exception {
        Project project = getProjectById(id);
        project.setName(updatedProject.getName());
        project.setDescription(updatedProject.getDescription());
        project.setTags(updatedProject.getTags());


        return projectRepository.save(project);
    }


    @Override
    public void addUserToProject(long projectId, long userId) throws Exception {
        Project project = getProjectById(projectId);
        User user = userService.findUserById(userId);
        if (!project.getTeam().contains(user)){
            project.getChat().getUsers().add(user);
            project.getTeam().add(user);
        }
        projectRepository.save(project);

    }

    @Override
    public void removeUserFromProject(long projectId, long userId) throws Exception {
        Project project = getProjectById(projectId);
        User user = userService.findUserById(userId);
        if (project.getTeam().contains(user)){
            project.getChat().getUsers().remove(user);
            project.getTeam().remove(user);
        }
        projectRepository.save(project);


    }

    @Override
    public Chat getChatByProjectId(long projectId) throws Exception {
        Project project = getProjectById(projectId);

        return project.getChat();
    }

    @Override
    public List<Project> searchProject(String keyword, User user) throws Exception {
        return projectRepository.findByNameContainingAndTeamContains(keyword, user);
    }
}
