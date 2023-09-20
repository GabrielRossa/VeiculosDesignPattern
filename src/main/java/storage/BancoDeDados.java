package storage;

import data.Carro;
import data.Motocicleta;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BancoDeDados implements Armazenamento{
    Connection connection;
    public BancoDeDados(){
        conectar();
    }

    void conectar(){
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:veiculos.db");
            System.out.println("Banco de dados conectado com sucesso!");

            createTables();
        }catch (Exception e) {
            System.out.println("Erro na conexão com o banco!");
            e.printStackTrace();
        }
    }

    private void createTables(){
        try{
            Statement statement = connection.createStatement();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS carros (id INTEGER PRIMARY KEY AUTOINCREMENT, marca STRING, modelo STRING, ano_fabricacao INTEGER, preco DOUBLE PRECISION, numero_portas INTEGER)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS motos (id INTEGER PRIMARY KEY AUTOINCREMENT, marca STRING, modelo STRING, ano_fabricacao INTEGER, preco DOUBLE PRECISION, cilindradas INTEGER)");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void salvarCarro(Carro carro) {
        try{
            var state = connection.prepareStatement("INSERT INTO carros (marca, modelo, ano_fabricacao, preco, numero_portas) VALUES (?, ?, ?, ?, ?)");
            state.setString(1, carro.marca);
            state.setString(2, carro.modelo);
            state.setInt(3, carro.ano_fabricacao);
            state.setDouble(4, carro.preco);
            state.setInt(5, carro.numero_portas);

            state.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Carro> listarCarros() {
        try{
            var state = connection.createStatement();
            var result = state.executeQuery("SELECT * FROM carros");

            var carros = new ArrayList<Carro>();

            while(result.next()) {
                var carro = new Carro();
                carro.marca = result.getString("marca");
                carro.modelo = result.getString("modelo");
                carro.ano_fabricacao = result.getInt("ano_fabricacao");
                carro.preco = result.getDouble("preco");
                carro.numero_portas = result.getInt("numero_portas");
                carros.add(carro);
            }
            return carros;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void salvarMoto(Motocicleta moto) {
        try{
            var state = connection.prepareStatement("INSERT INTO motos (marca, modelo, ano_fabricacao, preco, cilindradas) VALUES (?, ?, ?, ?, ?)");
            state.setString(1, moto.marca);
            state.setString(2, moto.modelo);
            state.setInt(3, moto.ano_fabricacao);
            state.setDouble(4, moto.preco);
            state.setInt(5, moto.cilindradas);

            state.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Motocicleta> listarMotos() {
        try{
            var state = connection.createStatement();
            var result = state.executeQuery("SELECT * from motos");

            var motos = new ArrayList<Motocicleta>();

            while(result.next()){
                var moto = new Motocicleta();
                moto.marca = result.getString("marca");
                moto.modelo = result.getString("modelo");
                moto.ano_fabricacao = result.getInt("ano_fabricacao");
                moto.preco = result.getInt("preco");
                moto.cilindradas = result.getInt("cilindradas");
                motos.add(moto);
            }
            return motos;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
