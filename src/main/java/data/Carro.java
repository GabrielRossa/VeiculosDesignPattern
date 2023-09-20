package data;

public class Carro extends Veiculo{
    public int numero_portas;

    @Override
    public String getVeiculo(){
        return super.getVeiculo() + ", Numero de portas: " + numero_portas;
    }
}
