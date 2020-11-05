package Medicao;

import Pais.Pais;
import StatusCaso.StatusCaso;
import java.time.LocalDate;

public class Medicao{
    private Pais pais = new Pais();
    private LocalDate data;
    /**
	 * @return the pais
	 */
	public Pais getPais() {
		return pais;
	}
	/**
	 * @return the data
	 */
	public LocalDate getData() {
		return data;
	}
	/**
	 * @return the casos
	 */
	public int getCasos() {
		return casos;
	}
	/**
	 * @return the status
	 */
	public StatusCaso getStatus() {
		return status;
	}
	/**
	 * @param pais the pais to set
	 */
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(LocalDate data) {
		this.data = data;
	}
	/**
	 * @param casos the casos to set
	 */
	public void setCasos(int casos) {
		this.casos = casos;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusCaso status) {
		this.status = status;
	}
	private int casos;
    private StatusCaso status;
}
