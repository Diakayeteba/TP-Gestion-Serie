package fr.pau.univ.series.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "episodes")
public class Episode implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String titre;
	private int numero;
	private boolean vu = false;

	/**
	 * Constructeur par défaut obligatoire pour JPA
	 */
	public Episode() {
	}

	/**
	 * @param titre
	 * @param numero
	 */
	public Episode(final String titre, final int numero) {
		this.titre = titre;
		this.numero = numero;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return this.titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(final String titre) {
		this.titre = titre;
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return this.numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(final int numero) {
		this.numero = numero;
	}

	/**
	 * @return the vu
	 */
	public boolean isVu() {
		return this.vu;
	}

	/**
	 * @param vu the vu to set
	 */
	public void setVu(final boolean vu) {
		this.vu = vu;
	}

	@Override
	public String toString() {
		return getTitre();
	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof Episode)) {
			return false;
		}
		return ((Episode) obj).getId() == getId();
	}
}