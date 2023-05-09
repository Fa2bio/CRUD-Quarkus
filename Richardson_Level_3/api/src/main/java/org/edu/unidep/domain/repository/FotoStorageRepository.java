package org.edu.unidep.domain.repository;

import java.util.UUID;

import org.edu.unidep.domain.model.FotoProduto;

public interface FotoStorageRepository {

	java.io.InputStream toRecover(String fileName);

	void store(NewPhoto newPhoto);

	void toRemove(String fileName);
	
	void salvar(FotoProduto foto);
	
	void deletar(FotoProduto foto);
	
	default void toReplace(String oldFileName, NewPhoto newPhoto) {
		this.store(newPhoto);
		if(oldFileName != null) this.toRemove(oldFileName);
	}
	
	default String generateFileName(String originalName) {
		return UUID.randomUUID().toString() + "_" + originalName;
	}
	
	public class NewPhoto{
		
		private String fileName;
		private java.io.InputStream inputStream;
		
		public NewPhoto(String fileName, java.io.InputStream inputStream) {
			this.fileName = fileName;
			this.inputStream = inputStream;
		}
		
		public String getFileName() {			return fileName;
		}

		public java.io.InputStream getInputStream() {
			return inputStream;
		}
				
	}
}
