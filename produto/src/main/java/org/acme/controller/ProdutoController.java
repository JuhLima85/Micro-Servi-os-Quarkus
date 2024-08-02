package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.ProdutoDTO;
import org.acme.service.ProdutoService;

import java.util.List;

@Path("/api/produtos")
public class ProdutoController {

    @Inject
    ProdutoService produtoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProdutoDTO> obterTodosProdutos() {
        return produtoService.listarTodosProdutos();
    }

    @POST
    @Transactional
    public Response salvarProduto(ProdutoDTO produtoDto) {
        try {
            produtoService.criarProduto(produtoDto);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizarProduto(@PathParam("id") Long id, ProdutoDTO produtoDto) {
        try {
            produtoService.editarProduto(id, produtoDto);
            return Response.accepted().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response excluirProduto(@PathParam("id") Long id) {
        try {
            produtoService.deletarProduto(id);
            return Response.accepted().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
