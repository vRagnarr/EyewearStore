package model;

public class Product {
	private int id;
	private String nome;
	private String brand;
	private double prezzo;
	private String sesso;
	private String image;
	private String modello;
	private String data_inserimento;
	
	public Product() {
		
	}
	
	public Product(int id, String nome, String brand, double prezzo, String date, String sesso, String modello) {
		this.id = id;
		this.nome = nome;
		this.brand = brand;
		this.prezzo = prezzo;
		this.sesso = sesso;
		this.modello = modello;
		this.data_inserimento = date;
	}
	
	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}
	
	public String getData_Inserimento() {
		return data_inserimento;
	}
	
	public void setData_Inserimento(String date) {
		this.data_inserimento = date;
	}
	
	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
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

	public String getBrand() {
		return brand;
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

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", nome=" + nome + ", marca=" + brand + ", prezzo=" + prezzo + ", sesso=" + sesso
				 + "]";
	}
	
	
}
