package com.invoice_system.demo.repository;

import com.invoice_system.demo.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {

    @Query(value = "SELECT * FROM invoice_system.get_all_invoices()", nativeQuery = true)
    List<Invoice> getAllInvoices();

    @Query(value = "SELECT * FROM invoice_system.get_invoice_by_id(:p_invoice_id)", nativeQuery = true)
    Invoice getInvoiceById(@Param("p_invoice_id") String invoiceId);

    @Query(value = "SELECT * FROM invoice_system.create_invoice(:p_invoice_id, :p_client_id, :p_address, " +
            ":p_city, :p_post_code, :p_country, :p_invoice_date, :p_invoice_due, :p_payment_term, " +
            ":p_project_desc, :p_total_price, :p_status)", nativeQuery = true)
    Invoice createInvoice(@Param("p_invoice_id") String invoiceId,
                          @Param("p_client_id") Long clientId,
                          @Param("p_address") String address,
                          @Param("p_city") String city,
                          @Param("p_post_code") Integer postCode,
                          @Param("p_country") String country,
                          @Param("p_invoice_date") java.sql.Date invoiceDate,
                          @Param("p_invoice_due") java.sql.Date invoiceDue,
                          @Param("p_payment_term") String paymentTerm,
                          @Param("p_project_desc") String projectDesc,
                          @Param("p_total_price") java.math.BigDecimal totalPrice,
                          @Param("p_status") String status);

    @Query(value = "SELECT * FROM invoice_system.update_invoice(:p_invoice_id, :p_client_id, :p_address, " +
            ":p_city, :p_post_code, :p_country, :p_invoice_date, :p_invoice_due, :p_payment_term, " +
            ":p_project_desc, :p_total_price, :p_status)", nativeQuery = true)
    Invoice updateInvoice(@Param("p_invoice_id") String invoiceId,
                          @Param("p_client_id") Long clientId,
                          @Param("p_address") String address,
                          @Param("p_city") String city,
                          @Param("p_post_code") Integer postCode,
                          @Param("p_country") String country,
                          @Param("p_invoice_date") java.sql.Date invoiceDate,
                          @Param("p_invoice_due") java.sql.Date invoiceDue,
                          @Param("p_payment_term") String paymentTerm,
                          @Param("p_project_desc") String projectDesc,
                          @Param("p_total_price") java.math.BigDecimal totalPrice,
                          @Param("p_status") String status);

    @Query(value = "SELECT invoice_system.delete_invoice(:p_invoice_id)", nativeQuery = true)
    void deleteInvoice(@Param("p_invoice_id") String invoiceId);
}