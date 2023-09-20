package data;

public class Veiculo {
    public String marca;
    public String modelo;
    public int ano_fabricacao;
    public double preco;

    public String getVeiculo(){
        return "Marca: " + marca +
                ", Modelo: " + modelo +
                ", Ano de fabricação: " + ano_fabricacao +
                ", Preço: " + preco;
    }
}
