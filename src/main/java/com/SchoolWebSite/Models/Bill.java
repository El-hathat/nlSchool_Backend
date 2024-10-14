package com.SchoolWebSite.Models;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Bill {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long billID;

	    private Double montant;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(name="Etat")
	    private Etat etat;
	    
	    private Date billDate;
	    private Date limiteDate;
	    
	    @ManyToOne
	    @JoinColumn(name = "student")
	    private Student student;
	    
	    @ManyToOne
	    @JoinColumn(name = "admin")
	    private Admin admin;

		

		public Bill(Long billID, Double montant, Etat etat, Date billDate, Date limiteDate) {
			super();
			this.billID = billID;
			this.montant = montant;
			this.etat = etat;
			this.billDate = billDate;
			this.limiteDate = limiteDate;
		}

		public Long getBillID() {
			return billID;
		}

		public void setBillID(Long billID) {
			this.billID = billID;
		}

		public Date getLimiteDate() {
			return limiteDate;
		}

		public void setLimiteDate(Date limiteDate) {
			this.limiteDate = limiteDate;
		}

		public Double getMontant() {
			return montant;
		}

		public void setMontant(Double montant) {
			this.montant = montant;
		}

		public Etat getEtat() {
			return etat;
		}

		public void setEtat(Etat etat) {
			this.etat = etat;
		}

		public Date getBillDate() {
			return billDate;
		}

		public void setBillDate(Date billDate) {
			this.billDate = billDate;
		}
	    
	    public Bill (){}
}
