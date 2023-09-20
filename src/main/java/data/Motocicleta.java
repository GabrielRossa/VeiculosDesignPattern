package data;

public class Motocicleta extends Veiculo{
    public int cilindradas;

    @Override
    public String getVeiculo(){
        return super.getVeiculo() + ", Cilindradas: " + cilindradas;
    }
}
