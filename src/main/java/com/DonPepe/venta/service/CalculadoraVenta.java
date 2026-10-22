package com.DonPepe.venta.service;

import com.DonPepe.venta.dto.VentaRequest;
import com.DonPepe.venta.dto.VentaResponse;
import com.DonPepe.venta.exception.ValidacionException;
import com.DonPepe.venta.model.MetodoPago;
import org.springframework.stereotype.Service;

@Service
public class CalculadoraVenta {

    private static final double IGV = 0.18;
    private static final double DESCUENTO = 0.10;
    private static final double COMISION_TARJETA = 0.03;

    public VentaResponse calcular(VentaRequest request) {

        validar(request);

        double subtotal =
                request.getCantidad() * request.getPrecioUnitario();

        double descuento = 0;

        if (subtotal > 500) {
            descuento = subtotal * DESCUENTO;
        }

        double subtotalConDescuento =
                subtotal - descuento;

        double igv =
                subtotalConDescuento * IGV;

        double totalConIgv =
                subtotalConDescuento + igv;

        double comision = 0;

        if (request.getMetodoPago() == MetodoPago.TARJETA) {
            comision = totalConIgv * COMISION_TARJETA;
        }

        double totalPagar =
                totalConIgv + comision;

        return new VentaResponse(
                request.getCliente(),
                redondear(subtotal),
                redondear(descuento),
                redondear(subtotalConDescuento),
                redondear(igv),
                redondear(comision),
                redondear(totalPagar)
        );
    }

    private void validar(VentaRequest request) {

        if (request == null) {
            throw new ValidacionException("El request no puede ser nulo");
        }

        if (request.getCliente() == null ||
                request.getCliente().trim().isEmpty()) {

            throw new ValidacionException(
                    "El nombre del cliente es obligatorio");
        }

        if (!request.getCliente()
                .matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {

            throw new ValidacionException(
                    "El nombre del cliente no puede contener números ni caracteres especiales");
        }

        if (request.getCliente().length() < 3) {

            throw new ValidacionException(
                    "El nombre debe tener al menos 3 caracteres");
        }

        if (request.getCantidad() == null) {

            throw new ValidacionException(
                    "La cantidad es obligatoria");
        }

        if (request.getCantidad() <= 0) {

            throw new ValidacionException(
                    "La cantidad debe ser mayor a 0");
        }

        if (request.getPrecioUnitario() == null) {

            throw new ValidacionException(
                    "El precio es obligatorio");
        }

        if (request.getPrecioUnitario() <= 0) {

            throw new ValidacionException(
                    "El precio debe ser mayor a 0");
        }

        if (request.getMetodoPago() == null) {

            throw new ValidacionException(
                    "Método de pago obligatorio");
        }
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}