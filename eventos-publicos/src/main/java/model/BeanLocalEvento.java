package model;

public class BeanLocalEvento {
	
	private String local;
	private String tipo;
	private String nome;
	
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return "BeanLocalEvento [local=" + local + ", tipo=" + tipo + ", nome=" + nome + "]";
	}
	
	

}
