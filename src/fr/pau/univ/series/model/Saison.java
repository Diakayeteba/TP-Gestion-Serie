package fr.pau.univ.series.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.pau.univ.series.exception.DaoException;

@Entity
@Table(name = "saisons")
public class Saison implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int numero;
	private String nom;

	@Transient
	private boolean toutVu = false;

	@OneToMany(fetch = javax.persistence.FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL, orphanRemoval = true)
	@javax.persistence.JoinColumn(name = "fk_saison", referencedColumnName = "id")
	private List<Episode> episodes = new ArrayList<>();

	public Saison() {
	}

	public Saison(final String nom, final int numero) {
		this.nom = nom;
		this.numero = numero;
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(final int numero) {
		this.numero = numero;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

	public Collection<Episode> getEpisodes() {
		return this.episodes;
	}

	public Episode addEpisode(final Episode e) throws DaoException {
		this.episodes.add(e);
		return e;
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
		if (!(obj instanceof Saison)) {
			return false;
		}
		return ((Saison) obj).getId() == getId();
	}
}