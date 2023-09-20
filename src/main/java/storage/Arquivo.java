package storage;

import data.Carro;
import data.Motocicleta;
import data.Veiculo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Arquivo implements Armazenamento{
    private File file = new File("veiculos.txt");
    private List<Carro> carros = new ArrayList<>();
    private List<Motocicleta> motos = new ArrayList<>();

    public Arquivo(){
        criarArquivo();
        lerArquivo();
    }

    private void criarArquivo(){
        if(!file.exists()){
            try{
                file.createNewFile();
                System.out.println("Arquivo de texto para armazenar veículos foi criado!");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private String serializaVeiculo(Veiculo veiculo){
        return veiculo.marca + "," + veiculo.modelo + "," + veiculo.ano_fabricacao + "," + veiculo.preco;
    }

    private void salvarDados() {
        try {
            List<String> lines = new ArrayList<>();

            lines.add("*LISTA DE CARROS*");

            for(Carro carro : carros){
                lines.add(serializaVeiculo(carro) + "," + carro.numero_portas);
            }

            lines.add("*LISTA DE MOTOS*");

            for(Motocicleta moto : motos){
                lines.add(serializaVeiculo(moto) + ',' + moto.cilindradas);
            }

            FileWriter fw = new FileWriter(file);

            fw.write(String.join("\n", lines));
            fw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void lerArquivo(){
        try{
            Scanner scanner = new Scanner(file);

            String tipo = "";

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if(line.equals("*LISTA DE CARROS*")) {
                    tipo = "carro";
                } else if (line.equals("*LISTA DE MOTOS*")){
                    tipo = "moto";
                } else if (tipo != ""){
                    Veiculo veiculo;
                    String[] split = line.split(",");

                    if(tipo.equals("carro")){
                        Carro carro = new Carro();
                        veiculo = carro;
                        carros.add(carro);

                        carro.numero_portas = Integer.parseInt(split[4]);
                    } else {
                        Motocicleta moto = new Motocicleta();
                        veiculo = moto;
                        motos.add(moto);

                        moto.cilindradas = Integer.parseInt(split[4]);
                    }

                    veiculo.marca = split[0];
                    veiculo.modelo = split[1];
                    veiculo.ano_fabricacao = Integer.parseInt(split[2]);
                    veiculo.preco = Double.parseDouble(split[3]);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void salvarCarro(Carro carro) {
        carros.add(carro);
        salvarDados();
    }

    @Override
    public List<Carro> listarCarros() {
        return carros;
    }

    @Override
    public void salvarMoto(Motocicleta moto) {
        motos.add(moto);
        salvarDados();
    }

    @Override
    public List<Motocicleta> listarMotos() {
        return motos;
    }
}
