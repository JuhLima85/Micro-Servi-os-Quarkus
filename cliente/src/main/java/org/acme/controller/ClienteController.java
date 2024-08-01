package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.ClienteDTO;
import org.acme.service.ClienteService;

import java.util.List;

@Path("/api/clientes")
public class ClienteController {

    @Inject
    ClienteService clienteService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ClienteDTO> obterTodosClientes() {
        return clienteService.listarTodosClientes();
    }

    @POST
    @Transactional
    public Response salvarCliente(ClienteDTO clienteDTO) {
        try {
            clienteService.criarCliente(clienteDTO);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizarCliente(@PathParam("id") Long id, ClienteDTO clienteDTO) {
        try {
            clienteService.editarCliente(id, clienteDTO);
            return Response.accepted().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response excluirCliente(@PathParam("id") Long id) {
        try {
            clienteService.deletarCliente(id);
            return Response.accepted().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
