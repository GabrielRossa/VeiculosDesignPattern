import data.Carro;
import data.Motocicleta;
import storage.Armazenamento;
import storage.Arquivo;
import storage.BancoDeDados;

public class Main {
    public static void main(String[] args) {
        Armazenamento armazenamento = new BancoDeDados();
        Concessionaria concessionaria = new Concessionaria(armazenamento);

        Carro carro = Main.criarCarro();
        concessionaria.salvarVeiculo(carro);

        Motocicleta moto = Main.criarMoto();
        concessionaria.salvarVeiculo(moto);

        concessionaria.listarVeiculos();
    }
    public static Carro criarCarro(){
        var carro = new Carro();

        carro.modelo = "Agera RS";
        carro.marca = "Koenigsegg";
        carro.ano_fabricacao = 2015;
        carro.preco = 2500000;
        carro.numero_portas = 2;

        return carro;
    }
    public static Motocicleta criarMoto(){
        var moto = new Motocicleta();

        moto.modelo = "Ninja";
        moto.marca = "Kawasaki";
        moto.ano_fabricacao = 2023;
        moto.preco = 56000;
        moto.cilindradas = 1000;

        return moto;
    }
}