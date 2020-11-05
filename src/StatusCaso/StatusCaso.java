package StatusCaso;

public enum StatusCaso {
    CONFIRMADOS("Confirmados"),
    MORTOS("Mortos"),
    RECUPERADOS("Recuperados");
	public String valorCarta;
	StatusCaso(String valor) {
	valorCarta = valor;
	}
}
