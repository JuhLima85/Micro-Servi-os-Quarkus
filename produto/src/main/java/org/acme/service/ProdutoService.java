package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.ProdutoDTO;
import org.acme.entity.ProdutoEntity;
import org.acme.repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Serviço responsável pela gestão dos produtos.
 *
 * Esta classe oferece operações CRUD (Create, Read, Update, Delete) para
 * os produtos através da interface ProdutoRepository.
 */
@ApplicationScoped
public class ProdutoService {

    @Inject
    ProdutoRepository produtoRepository;

    /**
     * Retorna uma lista de todos os produtos.
     *
     * @return uma lista de ProdutoDTO contendo todos os produtos.
     */
    public List<ProdutoDTO> listarTodosProdutos(){
        List<ProdutoDTO> produtos = new ArrayList<>();
        produtoRepository.findAll().stream().forEach(item -> {
            produtos.add(produtoEntityToDTO(item));
        });

        return produtos;
    }

    /**
     * Cria um novo produto.
     *
     * @param produtoDTO o DTO do produto que será criado.
     */
    public void criarProduto(ProdutoDTO produtoDTO) {
        produtoRepository.persist(produtoDTOToEntity(produtoDTO));
    }

    /**
     * Edita um produto existente.
     *
     * @param id o ID do produto que será editado.
     * @param produtoDTO o DTO do produto com as novas informações.
     */
    public void editarProduto(Long id, ProdutoDTO produtoDTO){
        ProdutoEntity produto = produtoRepository.findById(id);

        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setCategoria(produtoDTO.getCategoria());
        produto.setModelo(produtoDTO.getModelo());
        produto.setPreco(produtoDTO.getPreco());

        produtoRepository.persist(produto);
    }

    /**
     * Deleta um produto pelo seu ID.
     *
     * @param id o ID do produto que será deletado.
     */
    public void deletarProduto(Long id){
        produtoRepository.deleteById(id);
    }

    /**
     * Converte uma entidade ProdutoEntity para um DTO ProdutoDTO.
     *
     * @param produto a entidade ProdutoEntity que será convertida.
     * @return um DTO ProdutoDTO.
     */
    private ProdutoDTO produtoEntityToDTO(ProdutoEntity produto){
        ProdutoDTO produtoDTO = new ProdutoDTO();

        produtoDTO.setNome(produto.getNome());
        produtoDTO.setDescricao(produto.getDescricao());
        produtoDTO.setCategoria(produto.getCategoria());
        produtoDTO.setModelo(produto.getModelo());
        produtoDTO.setPreco(produto.getPreco());

        return produtoDTO;
    }

    /**
     * Converte um DTO ProdutoDTO para uma entidade ProdutoEntity.
     *
     * @param produtoDto o DTO ProdutoDTO que será convertido.
     * @return uma entidade ProdutoEntity.
     */
    private ProdutoEntity produtoDTOToEntity(ProdutoDTO produtoDto){
        ProdutoEntity produto = new ProdutoEntity();

        produto.setNome(produtoDto.getNome());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setCategoria(produtoDto.getCategoria());
        produto.setModelo(produtoDto.getModelo());
        produto.setPreco(produtoDto.getPreco());

        return produto;
    }

}
