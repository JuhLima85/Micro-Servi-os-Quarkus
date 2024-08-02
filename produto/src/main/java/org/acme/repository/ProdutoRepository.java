package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.ProdutoEntity;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<ProdutoEntity> {
}
