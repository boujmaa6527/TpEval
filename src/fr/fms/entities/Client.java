package fr.fms.entities;

public class Client {
	
		private int IdClient;
		private String nom; 
		private String prenom;
		private String email; 
		private String adresse;
		private String telephone;
		private int idUser; 
		
		public Client(int idClient, String nom, String prenom, String email, String adresse, String telephone, int idUser) {
			super();
			IdClient = idClient;
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.adresse = adresse;
			this.telephone = telephone;
			this.idUser = idUser; 
		}
		public Client( String nom, String prenom, String email, String adresse, String telephone, int idUser) {
			super();
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.adresse = adresse;
			this.telephone = telephone;
			this.idUser = idUser; 
		}
		public int getIdUser() {
			return idUser;
		}
		public void setIdUser(int idUser) {
			this.idUser = idUser;
		}
		public int getIdClient() {
			return IdClient;
		}
		public void setIdClient(int idClient) {
			IdClient = idClient;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getAdresse() {
			return adresse;
		}
		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		@Override
		public String toString() {
			return "Client [IdClient=" + IdClient + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
					+ ", adresse=" + adresse + ", telephone=" + telephone + ", idUser=" + idUser + "]";
		}
		
		

}
