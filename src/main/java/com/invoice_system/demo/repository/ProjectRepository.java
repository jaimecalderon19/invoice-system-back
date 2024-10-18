package com.invoice_system.demo.repository;

import com.invoice_system.demo.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value = "SELECT * FROM invoice_system.get_all_projects()", nativeQuery = true)
    List<Project> getAllProjects();

    @Query(value = "SELECT * FROM invoice_system.get_project_by_id(:p_project_id)", nativeQuery = true)
    Project getProjectById(@Param("p_project_id") Long projectId);

    @Query(value = "SELECT * FROM invoice_system.create_project(:p_invoice_id, :p_name, :p_quantity, " +
            ":p_price, :p_total)", nativeQuery = true)
    Project createProject(@Param("p_invoice_id") String invoiceId, @Param("p_name") String name,
                          @Param("p_quantity") Integer quantity,
                          @Param("p_price") java.math.BigDecimal price,
                          @Param("p_total") java.math.BigDecimal total);

    @Query(value = "SELECT * FROM invoice_system.update_project(:p_project_id, :p_invoice_id, " +
            ":p_name, :p_quantity, :p_price, :p_total)", nativeQuery = true)
    Project updateProject(@Param("p_project_id") Long projectId, @Param("p_invoice_id") String invoiceId,
                          @Param("p_name") String name, @Param("p_quantity") Integer quantity,
                          @Param("p_price") java.math.BigDecimal price,
                          @Param("p_total") java.math.BigDecimal total);

    @Query(value = "SELECT invoice_system.delete_project(:p_project_id)", nativeQuery = true)
    void deleteProject(@Param("p_project_id") Long projectId);
}

