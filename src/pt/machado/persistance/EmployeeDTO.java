package pt.machado.persistance;

public class EmployeeDTO {

	private String bi;
	private String nome;
	private String dataNascimento;
	private String morada;
	private String categoria;
	private String funcao;
	
	public EmployeeDTO() {
		
	}

	public EmployeeDTO(String bi, String nome, String dataNascimento, String morada, String categoria, String funcao) {
		super();
		this.bi = bi;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.morada = morada;
		this.categoria = categoria;
		this.funcao = funcao;
	}

	public String getBi() {
		return bi;
	}

	public void setBi(String bi) {
		this.bi = bi;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bi == null) ? 0 : bi.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((funcao == null) ? 0 : funcao.hashCode());
		result = prime * result + ((morada == null) ? 0 : morada.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		EmployeeDTO other = (EmployeeDTO) obj;
		if (bi == null) {
			if (other.bi != null)
				return false;
		} else if (!bi.equals(other.bi))
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (funcao == null) {
			if (other.funcao != null)
				return false;
		} else if (!funcao.equals(other.funcao))
			return false;
		if (morada == null) {
			if (other.morada != null)
				return false;
		} else if (!morada.equals(other.morada))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder("FUNCIONARIO [");
		sb.append("BI= ").append(this.bi);
		sb.append(", NOME= ").append(this.nome);
		sb.append(", DATA NASCIMENTO= ").append(this.dataNascimento);
		sb.append(", MORADA= ").append(this.morada);
		sb.append(", CATEGORIA= ").append(this.categoria);
		sb.append(", FUNCAO= ").append(this.funcao);
		sb.append("]");
		
		return sb.toString();
	}
	
}
