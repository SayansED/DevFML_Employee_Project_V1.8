/*
package sistema.entidades;

public class Cargo {
    // variável destinado ao id do cargo
    private int id; 
    // variável destinado ao nome do cargo
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }    
}
*/

//			Adaptação
package sistema.entidades;

public class Cargo {
    // variável destinado ao id do cargo
    private int cargoId; 
    // variável destinado ao nome do cargo
    private String cargoNome;

    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int id) {
        this.cargoId = id;
    }

    public String getCargoNome() {
        return cargoNome;
    }

    public void setCargoNome(String nome) {
        this.cargoNome = nome;
    }
    
    @Override
    public String toString() {
        return this.cargoNome;
    }    
}

