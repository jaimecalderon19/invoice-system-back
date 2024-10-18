package com.invoice_system.demo.controllers;

import com.invoice_system.demo.models.Project;
import com.invoice_system.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects();
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") Long projectId) {
        Project project = projectRepository.getProjectById(projectId);
        if(project == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(project);
    }

    @PostMapping("/projects")
    public Project createProject(@RequestBody Project project) {
        return projectRepository.createProject(project.getInvoice().getInvoiceId(), project.getName(),
                project.getQuantity(), project.getPrice(), project.getTotal());
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable(value = "id") Long projectId,
                                                 @RequestBody Project projectDetails) {
        Project updatedProject = projectRepository.updateProject(projectId, projectDetails.getInvoice().getInvoiceId(),
                projectDetails.getName(), projectDetails.getQuantity(),
                projectDetails.getPrice(), projectDetails.getTotal());
        if(updatedProject == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable(value = "id") Long projectId) {
        projectRepository.deleteProject(projectId);
        return ResponseEntity.ok().build();
    }
}
