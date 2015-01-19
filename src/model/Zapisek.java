package model;

public class Zapisek {
	
	private int id;
	private String nadpis;
	private String obsah;
	
	public Zapisek(String nadpis, String obsah) {
		this.nadpis = nadpis;
		this.obsah = obsah;
	}
	
	public Zapisek(int id, String nadpis, String obsah) {
		this.id = id;
		this.nadpis = nadpis;
		this.obsah = obsah;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getNadpis() {
		return nadpis;
	}

	public void setNadpis(String nadpis) {
		this.nadpis = nadpis;
	}

	public String getObsah() {
		return obsah;
	}

	public void setObsah(String obsah) {
		this.obsah = obsah;
	}

}
