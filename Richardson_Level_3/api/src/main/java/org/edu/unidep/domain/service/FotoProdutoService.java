package org.edu.unidep.domain.service;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.edu.unidep.domain.exception.FotoNaoEncontradaException;
import org.edu.unidep.domain.model.FotoProduto;
import org.edu.unidep.domain.repository.FotoStorageRepository;
import org.edu.unidep.domain.repository.ProdutoRepository;
import org.edu.unidep.domain.repository.FotoStorageRepository.NewPhoto;

@ApplicationScoped
public class FotoProdutoService {

	@Inject
	private ProdutoRepository produtoRepository;
	
	@Inject
	private FotoStorageRepository fotoStorageRepository;
	
	@Transactional
	public FotoProduto salvarFoto(FotoProduto foto, java.io.InputStream dataFile) {
		Long produtoId = foto.getProduto().getId();
		String newFileName = fotoStorageRepository.generateFileName(foto.getFileName());
		String existingFilename = null;
		
		Optional<FotoProduto> fotoExistente = produtoRepository.buscarFotoProdutoPeloCodigo(produtoId);
		
		if(fotoExistente.isPresent()) {
			existingFilename = fotoExistente.get().getFileName();
			fotoStorageRepository.deletar(foto);
		}
		foto.setFileName(newFileName);
		fotoStorageRepository.salvar(foto);
		
		NewPhoto newPhoto = new NewPhoto(foto.getFileName(), dataFile);
		fotoStorageRepository.toReplace(existingFilename, newPhoto);
		
		return foto;
	}
	
	@Transactional
	public void deletarFoto(Long produtoId) {
		FotoProduto foto = acharOuFalhar(produtoId);		
		fotoStorageRepository.deletar(foto);
		fotoStorageRepository.toRemove(foto.getFileName());
	}
	
	public FotoProduto acharOuFalhar(Long produtoId) {
		return produtoRepository.buscarFotoProdutoPeloCodigo(produtoId)
				.orElseThrow(() -> new FotoNaoEncontradaException(produtoId));
	}
}
