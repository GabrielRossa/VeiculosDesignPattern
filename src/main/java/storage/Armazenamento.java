package storage;

import data.Carro;
import data.Motocicleta;

import java.util.List;

public interface Armazenamento {
    void salvarCarro(Carro carro);
    List<Carro> listarCarros();

    void salvarMoto(Motocicleta moto);
    List<Motocicleta> listarMotos();
}