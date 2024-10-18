package com.invoice_system.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "clients", schema = "invoice_system")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientid")
    private Long clientId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(name = "postcode", nullable = false)
    private Integer postCode;

    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Invoice> invoices;
}
