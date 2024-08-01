package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.ClienteDTO;
import org.acme.entity.ClienteEntity;
import org.acme.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ClienteService {

    @Inject
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> listarTodosClientes(){
        List<ClienteDTO> clientes = new ArrayList<>();
        clienteRepository.findAll().stream().forEach(item -> {
            clientes.add(clienteEntityToDTO(item));
        });

        return clientes;
    }

    public void criarCliente(ClienteDTO clienteDTO) {
        clienteRepository.persist(clienteDTOToEntity(clienteDTO));
    }

    public void editarCliente(Long id, ClienteDTO clienteDTO){
        ClienteEntity cliente = clienteRepository.findById(id);

        cliente.setNome(clienteDTO.getNome());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setIdade(clienteDTO.getIdade());

        clienteRepository.persist(cliente);
    }

    public void deletarCliente(Long id){
        clienteRepository.deleteById(id);
    }

    private ClienteDTO clienteEntityToDTO(ClienteEntity cliente){
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setTelefone(cliente.getTelefone());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setEndereco(cliente.getEndereco());
        clienteDTO.setIdade(cliente.getIdade());

        return clienteDTO;
    }

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
