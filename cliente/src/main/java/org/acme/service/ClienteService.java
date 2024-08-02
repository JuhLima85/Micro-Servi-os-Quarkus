package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.ClienteDTO;
import org.acme.entity.ClienteEntity;
import org.acme.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Serviço responsável pela gestão dos clientes.
 *
 * Esta classe oferece operações CRUD (Create, Read, Update, Delete) para
 * os clientes através da interface ClienteRepository.
 */
@ApplicationScoped
public class ClienteService {

    @Inject
    private ClienteRepository clienteRepository;

    /**
     * Retorna uma lista de todos os clientes.
     *
     * @return uma lista de ClienteDTO contendo todos os clientes.
     */
    public List<ClienteDTO> listarTodosClientes(){
        List<ClienteDTO> clientes = new ArrayList<>();
        clienteRepository.findAll().stream().forEach(item -> {
            clientes.add(clienteEntityToDTO(item));
        });

        return clientes;
    }

    /**
     * Cria um novo cliente.
     *
     * @param clienteDTO o DTO do cliente que será criado.
     */
    public void criarCliente(ClienteDTO clienteDTO) {
        clienteRepository.persist(clienteDTOToEntity(clienteDTO));
    }

    /**
     * Edita um cliente existente.
     *
     * @param id o ID do cliente que será editado.
     * @param clienteDTO o DTO do cliente com as novas informações.
     */
    public void editarCliente(Long id, ClienteDTO clienteDTO){
        ClienteEntity cliente = clienteRepository.findById(id);

        cliente.setNome(clienteDTO.getNome());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setIdade(clienteDTO.getIdade());

        clienteRepository.persist(cliente);
    }

    /**
     * Deleta um cliente pelo seu ID.
     *
     * @param id o ID do cliente que será deletado.
     */
    public void deletarCliente(Long id){
        clienteRepository.deleteById(id);
    }

    /**
     * Converte uma entidade ClienteEntity para um DTO ClienteDTO.
     *
     * @param cliente a entidade ClienteEntity que será convertida.
     * @return um DTO ClienteDTO.
     */
    private ClienteDTO clienteEntityToDTO(ClienteEntity cliente){
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setTelefone(cliente.getTelefone());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setEndereco(cliente.getEndereco());
        clienteDTO.setIdade(cliente.getIdade());

        return clienteDTO;
    }

    /**
     * Converte um DTO ClienteDTO para uma entidade ClienteEntity.
     *
     * @param cliente o DTO ClienteDTO que será convertido.
     * @return uma entidade ClienteEntity.
     */
    private ClienteEntity clienteDTOToEntity(ClienteDTO cliente){
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setNome(cliente.getNome());
        clienteEntity.setTelefone(cliente.getTelefone());
        clienteEntity.setEmail(cliente.getEmail());
        clienteEntity.setEndereco(cliente.getEndereco());
        clienteEntity.setIdade(cliente.getIdade());

        return clienteEntity;
    }
}
