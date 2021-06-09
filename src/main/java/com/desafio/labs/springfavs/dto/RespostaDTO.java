package com.desafio.labs.springfavs.dto;

public class RespostaDTO {

	private Long favorito;

	private String mensagem;

	public RespostaDTO(Long favorito,String mensagem) {
		super();
		this.favorito = favorito;
		this.mensagem = mensagem;
	}

	public RespostaDTO() {}
	
	public Long getFavorito() {
		return favorito;
	}

	public void setFavorito(Long favorito) {
		this.favorito = favorito;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( (mensagem == null) ? 0 : mensagem.hashCode());
		result = prime * result + ( (favorito == null) ? 0 : favorito.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RespostaDTO other = (RespostaDTO) obj;
		if (mensagem == null) {
			if (other.mensagem != null)
				return false;
		} else if (!mensagem.equals(other.mensagem))
			return false;
		if (favorito == null) {
			if (other.favorito != null)
				return false;
		} else if (!favorito.equals(other.favorito))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RespostaDTO [favorito=" + favorito + ", mensagem=" + mensagem + "]";
	}

}
