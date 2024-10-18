package com.invoice_system.demo.repository;

import com.invoice_system.demo.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query(value = "SELECT * FROM invoice_system.get_all_clients()", nativeQuery = true)
    List<Client> getAllClients();

    @Query(value = "SELECT * FROM invoice_system.get_client_by_id(:p_client_id)", nativeQuery = true)
    Client getClientById(@Param("p_client_id") Long clientId);

    @Query(value = "SELECT * FROM invoice_system.create_client(:p_name, :p_email, :p_address, " +
            ":p_city, :p_post_code, :p_country)", nativeQuery = true)
    Client createClient(@Param("p_name") String name, @Param("p_email") String email,
                        @Param("p_address") String address, @Param("p_city") String city,
                        @Param("p_post_code") Integer postCode, @Param("p_country") String country);

    @Query(value = "SELECT * FROM invoice_system.update_client(:p_client_id, :p_name, :p_email, " +
            ":p_address, :p_city, :p_post_code, :p_country)", nativeQuery = true)
    Client updateClient(@Param("p_client_id") Long clientId, @Param("p_name") String name,
                        @Param("p_email") String email, @Param("p_address") String address,
                        @Param("p_city") String city, @Param("p_post_code") Integer postCode,
                        @Param("p_country") String country);

    @Query(value = "SELECT invoice_system.delete_client(:p_client_id)", nativeQuery = true)
    void deleteClient(@Param("p_client_id") Long clientId);
}

