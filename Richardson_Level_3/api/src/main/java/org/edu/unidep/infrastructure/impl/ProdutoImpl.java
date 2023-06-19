package org.edu.unidep.infrastructure.impl;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.edu.unidep.domain.model.Produto;
import org.edu.unidep.domain.repository.ProdutoRepository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ProdutoImpl implements PanacheRepository<Produto>, ProdutoRepository{

	@Override
	public List<Produto> listarProdutos() {
		return listAll();
	}

	@Override
	public Optional<Produto> buscarProdutoPeloCodigo(Long id) {
		return findByIdOptional(id);
	}


	@Override
	@Transactional
	public void salvar(Produto produto) {
		persist(produto);		
	}

	@Override
	@Transactional
	public void deletar(Produto produto) {
		delete(produto);
	}

	@Override
	public String buscarVendasPorUnidadeDeMedida(String descricao, String unidadeDeMedida) {
		
		String jpql = """				
				SELECT SUM(totalDeVendas) FROM Produto p WHERE p.descricao = :descricao AND p.unidadeMedida = :unidadeDeMedida
				""";
		
		return getEntityManager()
				.createQuery(jpql)
				.setParameter("unidadeDeMedida", unidadeDeMedida)
				.setParameter("descricao", descricao)
				.getSingleResult()
				.toString();
	}

	@Override
	public String buscarVendasPorReais(String descricao) {
		
		String jpql = """
				SELECT SUM(totalDeVendas*preco) FROM Produto p WHERE p.descricao = :descricao
				""";
		return getEntityManager()
				.createQuery(jpql)
				.setParameter("descricao", descricao)
				.getSingleResult()
				.toString();
	}

	@Override
	public String buscarTotalEmEstoque(String descricao) {
		String jpql = """
				SELECT SUM(estoque) FROM Produto p WHERE p.descricao = :descricao
				""";
				
		return getEntityManager()
				.createQuery(jpql)
				.setParameter("descricao", descricao)
				.getSingleResult()
				.toString();
	}
}
