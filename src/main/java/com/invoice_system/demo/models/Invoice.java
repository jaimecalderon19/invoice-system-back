package com.invoice_system.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "invoices", schema = "invoice_system")
public class Invoice {
    @Id
    @Column(name = "invoiceid")
    private String invoiceId;

    @ManyToOne
    @JoinColumn(name = "clientid", nullable = false)
    private Client client;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(name = "postcode", nullable = false)
    private Integer postCode;

    @Column(nullable = false)
    private String country;

    @Column(name = "invoicedate", nullable = false)
    private LocalDate invoiceDate;

    @Column(name = "invoicedue", nullable = false)
    private LocalDate invoiceDue;

    @Column(name = "paymentterm", nullable = false)
    private String paymentTerm;

    @Column(name = "projectdesc", nullable = false)
    private String projectDesc;

    @Column(name = "totalprice", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<Project> projects;
}