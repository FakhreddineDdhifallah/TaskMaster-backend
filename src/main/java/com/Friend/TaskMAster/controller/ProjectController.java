package com.Friend.TaskMAster.controller;

import com.Friend.TaskMAster.model.Project;
import com.Friend.TaskMAster.model.User;
import com.Friend.TaskMAster.response.MessageResponse;
import com.Friend.TaskMAster.service.ProjectService;
import com.Friend.TaskMAster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;




    @GetMapping
    public ResponseEntity<List<Project>>getProjects(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String tag,
            @RequestHeader("Authorization") String jwt

    )throws Exception{
        User user =userService.findUserProfileByJwt(jwt);
        List<Project> projects = projectService.getProjectByTeam(user , category, tag);
        return new ResponseEntity<>(projects, HttpStatus.OK);

    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project>getProjectById(
            @PathVariable Long projectId,

            @RequestHeader("Authorization") String jwt

    ) throws Exception{
        User user =userService.findUserProfileByJwt(jwt);
        Project project = projectService.getProjectById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<Project>createProject(
            @PathVariable Long projectId,

            @RequestHeader("Authorization") String jwt,
            @RequestBody Project project

    ) throws Exception{
        User user =userService.findUserProfileByJwt(jwt);
        Project createdproject = projectService.createProject(project,user);
        return new ResponseEntity<>(createdproject, HttpStatus.OK);

    }
    @PatchMapping("/{projectId}")
    public ResponseEntity<Project>updateProject(
            @PathVariable Long projectId,

            @RequestHeader("Authorization") String jwt,
            @RequestBody Project project

    ) throws Exception{
        User user =userService.findUserProfileByJwt(jwt);
        Project updatedproject = projectService.updateProject(project , projectId);
        return new ResponseEntity<>(updatedproject, HttpStatus.OK);

    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<MessageResponse>deleteProject(
            @PathVariable Long projectId,

            @RequestHeader("Authorization") String jwt

    ) throws Exception{
        User user =userService.findUserProfileByJwt(jwt);
       projectService.deleteProject(projectId, user.getId());
        MessageResponse res= new MessageResponse("Project deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);

    }


    @GetMapping("/search")
    public ResponseEntity<List<Project>>searchProject(

            @RequestParam(required = false) String keyword,

            @RequestHeader("Authorization") String jwt

    ) throws Exception{
        User user =userService.findUserProfileByJwt(jwt);
        List<Project> projects = projectService.searchProject(keyword, user);
        return new ResponseEntity<>(projects, HttpStatus.OK);

    }
}


