package fr.pau.univ.series.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.pau.univ.series.exception.DaoException;

@Entity
@Table(name = "series")
public class Serie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nom;

	@Transient
	private boolean toutVu = false;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "fk_serie", referencedColumnName = "id")
	private List<Saison> saisons = new ArrayList<>();

	public Serie() {
	}

	public Serie(final String nom) {
		this.nom = nom;
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

	public Collection<Saison> getSaisons() {
		return this.saisons;
	}

	public void setSaisons(final List<Saison> saisons) {
		this.saisons = saisons;
	}

	public Saison addSaison(final Saison s) throws DaoException {
		this.saisons.add(s);
		return s;
	}

	public void removeSaison(final Saison s) throws DaoException {
		this.saisons.remove(s);
	}

	public void setToutVu(final boolean toutVu) {
		this.toutVu = toutVu;
	}

	public boolean isToutVu() {
		return this.toutVu;
	}

	@Override
	public String toString() {
		return getNom();
	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof Serie)) {
			return false;
		}
		return ((Serie) obj).getId() == getId();
	}
}