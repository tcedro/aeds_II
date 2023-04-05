class Personagem 
{
    //----------------------------atributes----------------------------------//

    private String nome;
    private int    altura;
    private double peso;
    private String corDoCabelo;
    private String CorDaPele;
    private String corDosOlhos;
    private String anoNascimento;
    private String genero;
    private String homeworld;

    //----------------------------constructors-------------------------------//

    /*
        Default
    */
    Personagem() {
        this.nome = ""; 
        this.altura = 0;
        this.peso = 0.0; 
        this.corDoCabelo = "";
        this.CorDaPele = "";
        this.corDosOlhos = "";
        this.anoNascimento = "";
        this.genero = "";
        this.homeworld = "";
    }
    
    /*
        Parameters
    */
    Personagem(String nome, int altura, double peso, String corDoCabelo, String corDaPele,
              String corDosOlhos, String anoNascimento, String genero, String homeworld) {
        this.nome = nome; 
        this.altura = altura;
        this.peso = peso; 
        this.corDoCabelo = corDoCabelo;
        this.CorDaPele = corDaPele;
        this.corDosOlhos = corDosOlhos;
        this.anoNascimento = anoNascimento;
        this.genero = genero;
        this.homeworld = homeworld;
    }

    //----------------------------getters----------------------------------//

    public String getNome()          { return this.nome; }
    public int    getAltura()        { return this.altura; }
    public double getPeso()          { return this.peso; }
    public String getCorDoCabelo()   { return this.corDoCabelo; }
    public String getCorDaPele()     { return this.CorDaPele; }
    public String getCorDosOlhos()   { return this.corDosOlhos; }
    public String getAnoNascimento() { return this.anoNascimento; }
    public String getGenero()        { return this.genero; }
    public String getHomeWorld()     { return this.homeworld; }

    //----------------------------setters----------------------------------//

    public void setNome(String nome)                    {  this.nome = nome; }
    public void setAltura(int altura)                   {  this.altura = altura; }
    public void setPeso(double peso)                    {  this.peso = peso; }
    public void setCorDoCabelo(String corDoCabelo)      {  this.corDoCabelo = corDoCabelo; }
    public void setCorDaPele(String corDaPele)          {  this.CorDaPele = corDaPele; }
    public void setCorDosOlhos(String corDosOlhos)      {  this.corDosOlhos = corDosOlhos; }
    public void setAnoNascimento(String anoNascimentos) {  this.anoNascimento = anoNascimentos; }
    public void setGenero(String genero)                {  this.genero = genero; }
    public void setHomeWorld(String homeworld)          {  this.homeworld = homeworld; }

    //------------------------------clone--------------------------------//

    public Personagem clone() {
        Personagem clone = new Personagem();

        clone.setAltura        (this.getAltura());
        clone.setAnoNascimento (this.getAnoNascimento()); 
        clone.setCorDaPele     (this.getCorDaPele());
        clone.setCorDoCabelo   (this.getCorDoCabelo());
        clone.setCorDosOlhos   (this.getCorDosOlhos());
        clone.setGenero        (this.getGenero());
        clone.setHomeWorld     (this.getHomeWorld());
        clone.setNome          (this.getNome());
        clone.setPeso          (this.getPeso());

        return clone;
    }

    //------------------------------toString--------------------------------//

    public String toString() {
        return " ## " + this.getNome() + " ## " + this.getAltura() + " ## " + this.getPeso() + " ## " + this.getCorDoCabelo()  +
         " ## " + this.getCorDaPele() + " ## " + this.getCorDosOlhos() + " ## " + this.getAnoNascimento() + " ## " +
         this.getGenero() + " ## " + this.getHomeWorld() + " ## ";
    }

} 
//----------------------------GerenciadorDeArquivo-----------------------------//

class GerenciadorDeArquivo 
{
    //------------------------------atributos-----------------------------------//

    public String caminhoArquivo;
    public String conteudoAquivo;

    //------------------------------lerArquivo---------------------------------//
    
    public void lerArquivo() {
        Arq.openRead(this.caminhoArquivo);
        this.conteudoAquivo = Arq.readLine();
        Arq.close();
    }

    //-----------------------------tratarString-------------------------------//

    // public void tratarString() {
    //     String aux = "";
    //     for (int i = 1; i < this.caminhoArquivo.length(); i++) aux += this.caminhoArquivo.charAt(i);
    //     this.caminhoArquivo = aux;
    // }

    //---------------------------Separar-valor-do-Nome------------------------//
    public String separarNome() {
        int indiceDoAtributo = this.conteudoAquivo.indexOf("'name'") + 9;
        String name = "";
        while (this.conteudoAquivo.charAt(indiceDoAtributo) != 39) {
            name += conteudoAquivo.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        
        return name;
    }

    //-----------------------------Separar-Altura----------------------------//

    public String separarAltura() {
        int indiceDoAtributo = this.conteudoAquivo.indexOf("'height'") + 11;
        String altura = "";
        while (this.conteudoAquivo.charAt(indiceDoAtributo) != 39) {
            altura += conteudoAquivo.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        if(altura.equals("unknown")) altura = "0";
        return altura;
    }

    //-----------------------------Separar-peso----------------------------//

    public String separarPeso() {
        int indiceDoAtributo = this.conteudoAquivo.indexOf("'mass'") + 9;
        String peso = "";
        while (this.conteudoAquivo.charAt(indiceDoAtributo) != 39) {
            if (conteudoAquivo.charAt(indiceDoAtributo) == ',') {
                peso += '.'; 
                indiceDoAtributo++;
                continue;
            }
            
            peso += conteudoAquivo.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        if(peso.equals("unknown")) peso = "0";
        return peso;
    }

    //---------------------------Separar-Cor-Do-Cabelo----------------------//

    public String separarCorDoCabelo() {
        int indiceDoAtributo = this.conteudoAquivo.indexOf("'hair_color'") + 15;
        String corDoCabelo = "";
        while (this.conteudoAquivo.charAt(indiceDoAtributo) != 39) {
            corDoCabelo += conteudoAquivo.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        return corDoCabelo;
    }

    //-----------------------separar-cor-da-pele-------------------------//

    public String separarCorDaPele() {
        int indiceDoAtributo = this.conteudoAquivo.indexOf("'skin_color'") + 15;
        String corDaPele = "";
        while (this.conteudoAquivo.charAt(indiceDoAtributo) != 39) {
            corDaPele += conteudoAquivo.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        return corDaPele;
    }

    //-----------------------separar-cor-dos-olhos-------------------//

    public String separarCorDosOlhos() {
        int indiceDoAtributo = this.conteudoAquivo.indexOf("'eye_color'") + 14;
        String corDosOlhos = "";
        while (this.conteudoAquivo.charAt(indiceDoAtributo) != 39) {
            corDosOlhos += conteudoAquivo.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        return corDosOlhos;
    }

    //-----------------------data-de-nascimento---------------------//

    public String separarDataNascimento() {
        int indiceDoAtributo = this.conteudoAquivo.indexOf("'birth_year'") + 15;
        String dataNascimento = "";
        while (this.conteudoAquivo.charAt(indiceDoAtributo) != 39) {
            dataNascimento += conteudoAquivo.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        return dataNascimento;
    }

    //------------------------separar-genero-----------------------//
    
    public String separarGenero() {
        int indiceDoAtributo = this.conteudoAquivo.indexOf("'gender'") + 11;
        String genero = "";
        while (this.conteudoAquivo.charAt(indiceDoAtributo) != 39) {
            genero += conteudoAquivo.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        return genero;
    }

    //----------------------separar-homeworld---------------------//

    public String separarHomeworld() {
        int indiceDoAtributo = this.conteudoAquivo.indexOf("'homeworld'") + 14;
        String homeworld = "";
        while (this.conteudoAquivo.charAt(indiceDoAtributo) != 39) {
            homeworld += conteudoAquivo.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        return homeworld;
    }

    //------------------------------setters-------------------------------//
    
    public void setCaminhoArquivo(String caminho) {
        this.caminhoArquivo = caminho;
    }

    //------------------------------getters-----------------------------------//

    public String getNome() { 
        String nome = this.separarNome();
        return nome; 
    }
    public int getAltura() { 
        int altura = Integer.parseInt( this.separarAltura() );         
        return altura; 
    }
    public double getPeso() { 
        double peso = Double.parseDouble(this.separarPeso() ); 
        return peso; 
    }
    public String getCorDoCabelo() { 
        String corDoCabelo = this.separarCorDoCabelo();    
        return corDoCabelo;
    }
    public String getCorDaPele() { 
        String corDaPele = this.separarCorDaPele();    
        return corDaPele; 
    }
    public String getCorDosOlhos() { 
        String corDosOlhos = this.separarCorDosOlhos();
        return corDosOlhos;
    }
    public String getAnoNascimento() { 
        String anoNascimento = this.separarDataNascimento();
        return anoNascimento; 
    }
    public String getGenero() { 
        String genero = this.separarGenero();          
        return genero; 
    }
    public String getHomeWorld() { 
        String homeworld = this.separarHomeworld();    
        return homeworld; 
    }
}
class Main 
{
    //--------------------------------isFim--------------------------------//
    
    public static boolean isFim(String str) { return (str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M'); }

    //--------------------------------main----------------------------------//
    public static void main(String[] args) {
        MyIO.setCharset("utf-8");
        Personagem[] p = new Personagem[87]; 
        GerenciadorDeArquivo gerenciador = new GerenciadorDeArquivo(); 
        int numEntrada = 0;
       
        do { 
            gerenciador.caminhoArquivo = MyIO.readLine();
            if(isFim(gerenciador.caminhoArquivo)) break;
            
            // gerenciador.tratarString();
            p[numEntrada] = new Personagem();
            gerenciador.lerArquivo();
            p[numEntrada].setNome           ( gerenciador.separarNome() );
            p[numEntrada].setAltura         ( Integer.parseInt( gerenciador.separarAltura() ));
            p[numEntrada].setPeso           ( Double.parseDouble( gerenciador.separarPeso() ));
            p[numEntrada].setCorDaPele      ( gerenciador.separarCorDaPele() );
            p[numEntrada].setCorDoCabelo    ( gerenciador.separarCorDoCabelo() );
            p[numEntrada].setCorDosOlhos    ( gerenciador.separarCorDosOlhos() );
            p[numEntrada].setAnoNascimento  ( gerenciador.separarDataNascimento() );
            p[numEntrada].setGenero         ( gerenciador.separarGenero() );
            p[numEntrada].setHomeWorld      ( gerenciador.separarHomeworld() );      
            
            MyIO.println(p[numEntrada].toString());

            numEntrada++;

        } while (true);
    }
}