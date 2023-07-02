package model;

public class User {
	
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String indirizzo;
	private String data_nascita;
	private String sesso;
	
	public User() {
		
	}
	
	public User(String nome, String cognome, String email, String password, String indirizzo, String data_nascita,
			String sesso) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.indirizzo = indirizzo;
		this.data_nascita = data_nascita;
		this.sesso = sesso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getData_nascita() {
		return data_nascita;
	}
	
	public void setData_nascita(String data_nascita) {
		this.data_nascita = data_nascita;
	}

	@Override
	public String toString() {
		return "User [nome=" + nome + ", cognome=" + cognome + ", indirizzo=" + indirizzo + "]";
	}
	
	
	
	
}
