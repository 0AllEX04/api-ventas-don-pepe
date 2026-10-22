package com.DonPepe.venta.dto;

public class VentaResponse {

    private String cliente;
    private Double subtotal;
    private Double descuento;
    private Double subtotalConDescuento;
    private Double igv;
    private Double comision;
    private Double totalPagar;

    public VentaResponse() {
    }

    public VentaResponse(
            String cliente,
            Double subtotal,
            Double descuento,
            Double subtotalConDescuento,
            Double igv,
            Double comision,
            Double totalPagar) {

        this.cliente = cliente;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.subtotalConDescuento = subtotalConDescuento;
        this.igv = igv;
        this.comision = comision;
        this.totalPagar = totalPagar;
    }

    public String getCliente() {
        return cliente;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Double getDescuento() {
        return descuento;
    }

    public Double getSubtotalConDescuento() {
        return subtotalConDescuento;
    }

    public Double getIgv() {
        return igv;
    }

    public Double getComision() {
        return comision;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }
}