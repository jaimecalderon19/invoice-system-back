package com.invoice_system.demo.controllers;

import com.invoice_system.demo.models.Invoice;
import com.invoice_system.demo.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class InvoiceController {


    private final InvoiceRepository invoiceRepository;

    @GetMapping("/invoices")
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.getAllInvoices();
    }

    @GetMapping("/invoices/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable(value = "id") String invoiceId) {
        Invoice invoice = invoiceRepository.getInvoiceById(invoiceId);
        if(invoice == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(invoice);
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return invoiceRepository.createInvoice(invoice.getInvoiceId(), invoice.getClient().getClientId(),
                invoice.getAddress(), invoice.getCity(), invoice.getPostCode(),
                invoice.getCountry(), java.sql.Date.valueOf(invoice.getInvoiceDate()),
                java.sql.Date.valueOf(invoice.getInvoiceDue()), invoice.getPaymentTerm(),
                invoice.getProjectDesc(), invoice.getTotalPrice(), invoice.getStatus());
    }

    @PutMapping("/invoices/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable(value = "id") String invoiceId,
                                                 @RequestBody Invoice invoiceDetails) {
        Invoice updatedInvoice = invoiceRepository.updateInvoice(invoiceId, invoiceDetails.getClient().getClientId(),
                invoiceDetails.getAddress(), invoiceDetails.getCity(),
                invoiceDetails.getPostCode(), invoiceDetails.getCountry(),
                java.sql.Date.valueOf(invoiceDetails.getInvoiceDate()),
                java.sql.Date.valueOf(invoiceDetails.getInvoiceDue()),
                invoiceDetails.getPaymentTerm(), invoiceDetails.getProjectDesc(),
                invoiceDetails.getTotalPrice(), invoiceDetails.getStatus());
        if(updatedInvoice == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedInvoice);
    }

    @DeleteMapping("/invoices/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable(value = "id") String invoiceId) {
        invoiceRepository.deleteInvoice(invoiceId);
        return ResponseEntity.ok().build();
    }
}