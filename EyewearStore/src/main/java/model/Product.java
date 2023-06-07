package model;

public class Product {
	private int id;
	private String nome;
	private String marca;
	private String prezzo;
	private String sesso;
	private int quantità;
	private String image;
	
	public Product() {
		
	}
	
	public Product(int id, String nome, String marca, String prezzo, String sesso, int quantità) {
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.prezzo = prezzo;
		this.sesso = sesso;
		this.quantità = quantità;
	}

	public String getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}

	public int getQuantità() {
		return quantità;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getMarca() {
		return marca;
	}

	public String getSesso() {
		return sesso;
	}
	

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", nome=" + nome + ", marca=" + marca + ", prezzo=" + prezzo + ", sesso=" + sesso
				+ ", quantità=" + quantità + "]";
	}
	
	
}
