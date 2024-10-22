package fr.fms.entities;

public class Categorie {
	
	private int IdCatgory;
	private String nomCategory;
	private String descriptionCategory;
	public Categorie(int idCatgory, String nomCategory, String descriptionCategory) {
		IdCatgory = idCatgory;
		this.nomCategory = nomCategory;
		this.descriptionCategory = descriptionCategory;
	} 
	public Categorie(String nomCategory, String descriptionCategory) {
		
		this.nomCategory = nomCategory;
		this.descriptionCategory = descriptionCategory;
	}
	
	public int getIdCatgory() {
		return IdCatgory;
	}
	public void setIdCatgory(int idCatgory) {
		IdCatgory = idCatgory;
	}
	public String getNomCategory() {
		return nomCategory;
	}
	public void setNomCategory(String nomCategory) {
		this.nomCategory = nomCategory;
	}
	public String getDescriptionCategory() {
		return descriptionCategory;
	}
	public void setDescriptionCategory(String descriptionCategory) {
		this.descriptionCategory = descriptionCategory;
	} 
	@Override
	public String toString() {
		return "Categorie [IdCatgory=" + IdCatgory + ", nomCategory=" + nomCategory + ", descriptionCategory="
				+ descriptionCategory + "]";
	}
	

}
