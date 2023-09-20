import data.Carro;
import data.Motocicleta;
import data.Veiculo;
import storage.Armazenamento;

import java.util.List;

public class Concessionaria {
    private Armazenamento armazenamento;

    public Concessionaria(Armazenamento armazenamento){
        this.armazenamento = armazenamento;
    }

    public void salvarVeiculo(Veiculo veiculo){
        if (veiculo instanceof Carro){
            armazenamento.salvarCarro((Carro)veiculo);
        }else if (veiculo instanceof Motocicleta){
            armazenamento.salvarMoto((Motocicleta)veiculo);
        }
    }

    public void listarVeiculos() {
        List<Carro> carros = armazenamento.listarCarros();
        List<Motocicleta> motocicletas = armazenamento.listarMotos();

        System.out.println("Lista de carros cadastrados: ");
        for (Carro carro : carros){
            System.out.println(carro.getVeiculo());
            System.out.println();
        }

        System.out.println("Lista de motocicletas cadastradas: ");
        for (Motocicleta moto : motocicletas){
            System.out.println(moto.getVeiculo());
        }
    }
}
