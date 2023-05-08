package org.edu.unidep.domain.repository;

import java.util.List;
import java.util.Optional;

import org.edu.unidep.domain.model.Produto;

public interface ProdutoRepository {
	List<Produto> listarProdutos();
	Optional<Produto> buscarPessoaPeloCodigo(Long id);
	String buscarVendasPorUnidadeDeMedida(String descricao, String unidadeDeMedida);
	String buscarVendasPorReais(String descricao);
	String buscarTotalEmEstoque(String descricao);
	void salvar(Produto produto);
	void deletar(Produto produto);
}
