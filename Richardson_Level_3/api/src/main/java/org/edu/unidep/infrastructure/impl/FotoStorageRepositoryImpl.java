package org.edu.unidep.infrastructure.impl;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.enterprise.context.ApplicationScoped;

import org.edu.unidep.domain.exception.StorageException;
import org.edu.unidep.domain.model.FotoProduto;
import org.edu.unidep.domain.repository.FotoStorageRepository;
import org.flywaydb.core.internal.util.FileCopyUtils;
import org.springframework.beans.factory.annotation.Value;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class FotoStorageRepositoryImpl implements FotoStorageRepository, PanacheRepository<FotoProduto>{

	@Value("${api.storage.location.directory-photos}")
	private Path diretorioFotos;

	@Override
	public InputStream toRecover(String fileName) {
		try {
			Path filePath = getArquivoPath(fileName);
			return Files.newInputStream(filePath);
		} catch (Exception e) {
			throw new StorageException("Unable to retrieve file.", e);
		}	
	}

	@Override
	public void store(NewPhoto newPhoto) {
		try {
			Path filePath = getArquivoPath(newPhoto.getFileName());
			FileCopyUtils.copy(newPhoto.getInputStream(), Files.newOutputStream(filePath));
		} catch (Exception e) {
			throw new StorageException("Unable to store the file.", e);
		}		
	}

	@Override
	public void toRemove(String fileName) {
		try {
			Path arquivoPath = getArquivoPath(fileName);
			
			Files.deleteIfExists(arquivoPath);
		} catch (Exception e) {
			throw new StorageException("Could not delete file.", e);
		}		
	}
	
	@Override
	public void salvar(FotoProduto foto) {
		persist(foto);
		getEntityManager().flush();
	}
	
	@Override
	public void deletar(FotoProduto foto) {
		delete(foto);
		getEntityManager().flush();
	}
	
	private Path getArquivoPath(String fileName) {
		return diretorioFotos.resolve(Path.of(fileName));
	}


}
