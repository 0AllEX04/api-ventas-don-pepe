package com.DonPepe.venta.controller;

import com.DonPepe.venta.dto.VentaRequest;
import com.DonPepe.venta.dto.VentaResponse;
import com.DonPepe.venta.service.CalculadoraVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private CalculadoraVenta calculadoraVenta;

    @PostMapping("/calcular")
    public VentaResponse calcular(
            @RequestBody VentaRequest request) {

        return calculadoraVenta.calcular(request);
    }
}