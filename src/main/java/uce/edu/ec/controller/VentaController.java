package uce.edu.ec.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.ec.service.IVentaService;
import uce.edu.ec.service.to.VentaTo;

import java.util.List;

@Path("/ventas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VentaController {

    @Inject
    IVentaService ventaService;

    @POST
    public Response crearVenta(VentaTo ventaTo) {
        try {
            ventaService.crearVenta(ventaTo);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error al crear la venta: " + e.getMessage())
                           .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarVentaPorId(@PathParam("id") Integer id) {
        try {
            VentaTo ventaTo = ventaService.buscarVentaPorId(id);
            return Response.ok(ventaTo).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error al buscar la venta: " + e.getMessage())
                           .build();
        }
    }

    @GET
    public Response todasLasVentas() {
        try {
            List<VentaTo> ventas = ventaService.todasLasVentas();
            return Response.ok(ventas).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error al obtener las ventas: " + e.getMessage())
                           .build();
        }
    }
}