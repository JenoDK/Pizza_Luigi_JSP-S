package be.vdab.entities;
import java.io.Serializable;
public class Persoon implements Serializable {
	private static final long serialVersionUID = 1L;
	private String voornaam;
	private String familienaam;
	private int aantalKinderen;
	private boolean gehuwd;
	private Adres adres;
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getFamilienaam() {
		return familienaam;
	}
	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}
	public int getAantalKinderen() {
		return aantalKinderen;
	}
	public void setAantalKinderen(int aantalKinderen) {
		this.aantalKinderen = aantalKinderen;
	}
	public boolean isGehuwd() {
		return gehuwd;
	}
	public void setGehuwd(boolean gehuwd) {
		this.gehuwd = gehuwd;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getNaam(){
		return voornaam +' '+ familienaam;
	}
	public Adres getAdres() {
		return adres;
	}
	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	
}
