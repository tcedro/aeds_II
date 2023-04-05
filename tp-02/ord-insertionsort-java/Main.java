import java.util.Date;
//-----------------------------------lista-----------------------------------//

class CharacterList  
{
    public Personagem array[];
    public int n; 

    CharacterList(int maxSize) {
        this.n = 0;
        this.array = new Personagem [maxSize];
    }

    //----------------Inserir-no-inicio--------------------//
    
    public void inserirInicio(Personagem elemento) {
        if ( array.length > n ) {
            for (int i = this.n; i > 0; i--) { //
                this.array[i] = this.array[i-1];
            }
            this.array[0] = elemento;
            this.n++;
        }
    }

    //----------------Inserir-no-Fim--------------------//

    public void inserirFim(Personagem elemento) {
        if ( array.length > n ) {
            this.array[this.n++] = elemento;
        }
    }

    //----------------Inserir-no-Meio--------------------//

    public void inserirMeio(int pos, Personagem elemento) {
        if ( array.length > n ) {
            for (int i = this.n; i > pos; i--) {
                this.array[i] = this.array[i-1];
            }

            this.array[pos] = elemento;
            this.n++;
        }
    }

    public int getLen() {
        return this.n;
    }

    //------------------remover-no-fim--------------------------//

    public Personagem removerFim() {
        Personagem tmp = null;
        if ( array.length != 0 ) {
            tmp = this.array[--n];
        }
        return tmp;
    }

    //------------------remover-no-inicio--------------------------//

    public Personagem removerInicio() {
        Personagem tmp = null;
        if ( array.length != 0 ) {
            tmp = this.array[0];
            for (int i = 0; i < this.n - 1; i++) {
                this.array[i] = this.array[i+1];
            }
            this.n--;
        }
        return tmp;
    }

    //------------------remover-no-meio--------------------------//

    public Personagem removerMeio(int pos) {
        Personagem tmp = null;
        if ( pos >= 0  && pos < n && array.length != 0 ) {
            tmp = this.array[pos];
            for (int i = pos; i < this.n - 1; i++) {
                this.array[i] = this.array[i+1];
            }
            this.n--;
        }
        return tmp;
    }

    public void swap(int pos1, int pos2) {
        Personagem aux = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = aux;
    }

    public int selectionSort() { // O ( n lg(n) )
        int numComp = 0;
        for (int i = 0; i < (getLen() - 1); i++) {
            int menor = i;
            for (int j = (i + 1); j < getLen(); j++) {
                numComp++;
                if (array[menor].getNome().compareTo( array[j].getNome() ) > 0) { 
                    menor = j;
                }
                numComp++;
            }
            swap(menor, i);
        }
        return numComp;
    }

    public int insertionSort() {
        int numComp = 0;
        for (int i = 1; i < getLen(); i++) {
            Personagem tmp = array[i].clone();
            int j = i - 1;

            while((j >= 0) && array[j].getAnoNascimento().compareTo( tmp.getAnoNascimento() ) > 0) {
                array[j+1] = array[j];
                j--;
                numComp++;
            }
            array[j+1] = tmp;
        }
        return numComp;
    }

    //-----------------------------------------------------------------//

    public String toString() {
       String tmp = "";
        for (int i = 0; i < this.n; i++) {
            tmp += "["+ i +"] " +this.array[i].toString() + "\n";
        
       }
        return tmp;
    }

}

//-------------------------------Personagem ----------------------------------//

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
    public String conteudoArquivo;

    //------------------------------lerArquivo---------------------------------//
    
    public void lerArquivo() {
        Arq.openRead(this.caminhoArquivo);
        this.conteudoArquivo = Arq.readLine();
        Arq.close();
    }

    //---------------------------Separar-valor-do-Nome------------------------//
    public String separarNome() {
        int indiceDoAtributo = this.conteudoArquivo.indexOf("'name'") + 9;
        String name = "";
        while (this.conteudoArquivo.charAt(indiceDoAtributo) != 39) {
            name += conteudoArquivo.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        
        return name;
    }

    //-----------------------------Separar-Altura----------------------------//

    public String separarAltura() {
        int indiceDoAtributo = this.conteudoArquivo.indexOf("'height'") + 11;
        String altura = "";
        while (this.conteudoArquivo.charAt(indiceDoAtributo) != 39) {
            altura += conteudoArquivo.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        if(altura.equals("unknown")) altura = "0";
        return altura;
    }

    //-----------------------------Separar-peso----------------------------//

    public String separarPeso() {
        int indiceDoAtributo = this.conteudoArquivo.indexOf("'mass'") + 9;
        String peso = "";
        while (this.conteudoArquivo.charAt(indiceDoAtributo) != 39) {
            if (conteudoArquivo.charAt(indiceDoAtributo) == ',') {
                peso += '.'; 
                indiceDoAtributo++;
                continue;
            }
            
            peso += conteudoArquivo.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        if(peso.equals("unknown")) peso = "0";
        return peso;
    }

    //---------------------------Separar-Cor-Do-Cabelo----------------------//

    public String separarCorDoCabelo() {
        int indiceDoAtributo = this.conteudoArquivo.indexOf("'hair_color'") + 15;
        String corDoCabelo = "";
        while (this.conteudoArquivo.charAt(indiceDoAtributo) != 39) {
            corDoCabelo += conteudoArquivo.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        return corDoCabelo;
    }

    //-----------------------separar-cor-da-pele-------------------------//

    public String separarCorDaPele() {
        int indiceDoAtributo = this.conteudoArquivo.indexOf("'skin_color'") + 15;
        String corDaPele = "";
        while (this.conteudoArquivo.charAt(indiceDoAtributo) != 39) {
            corDaPele += conteudoArquivo.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        return corDaPele;
    }

    //-----------------------separar-cor-dos-olhos-------------------//

    public String separarCorDosOlhos() {
        int indiceDoAtributo = this.conteudoArquivo.indexOf("'eye_color'") + 14;
        String corDosOlhos = "";
        while (this.conteudoArquivo.charAt(indiceDoAtributo) != 39) {
            corDosOlhos += conteudoArquivo.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        return corDosOlhos;
    }

    //-----------------------data-de-nascimento---------------------//

    public String separarDataNascimento() {
        int indiceDoAtributo = this.conteudoArquivo.indexOf("'birth_year'") + 15;
        String dataNascimento = "";
        while (this.conteudoArquivo.charAt(indiceDoAtributo) != 39) {
            dataNascimento += conteudoArquivo.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        return dataNascimento;
    }

    //------------------------separar-genero-----------------------//
    
    public String separarGenero() {
        int indiceDoAtributo = this.conteudoArquivo.indexOf("'gender'") + 11;
        String genero = "";
        while (this.conteudoArquivo.charAt(indiceDoAtributo) != 39) {
            genero += conteudoArquivo.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        return genero;
    }

    //----------------------separar-homeworld---------------------//

    public String separarHomeworld() {
        int indiceDoAtributo = this.conteudoArquivo.indexOf("'homeworld'") + 14;
        String homeworld = "";
        while (this.conteudoArquivo.charAt(indiceDoAtributo) != 39) {
            homeworld += conteudoArquivo.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        return homeworld;
    }

    public void setAtributosPersonagem(Personagem pers) {
        pers.setNome           ( this.separarNome() );
        pers.setAltura         ( Integer.parseInt( this.separarAltura() ));
        pers.setPeso           ( Double.parseDouble( this.separarPeso() ));
        // pers.setPeso           ( this.getPeso() );
        pers.setCorDaPele      ( this.separarCorDaPele() );
        pers.setCorDoCabelo    ( this.separarCorDoCabelo() );
        pers.setCorDosOlhos    ( this.separarCorDosOlhos() );
        pers.setAnoNascimento  ( this.separarDataNascimento() );
        pers.setGenero         ( this.separarGenero() );
        pers.setHomeWorld      ( this.separarHomeworld() );      
            
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
        // double peso = this.separarPeso();
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
        //gerenciador de caminho e conteudo de arquivos
        GerenciadorDeArquivo gerenciador = new GerenciadorDeArquivo(); 
        //lista de personagens 
        CharacterList charList = new CharacterList(100);
        
        do { 
            //ler caminho do arquivo
            gerenciador.caminhoArquivo = MyIO.readLine();
            if(isFim(gerenciador.caminhoArquivo)) break;

            // cria-se referencia a novo personagem
            Personagem novoPersonagem = new Personagem();
            
            //gerenciador ler valores do arquivo
            gerenciador.lerArquivo();
            
            //set atributos do personagem
            gerenciador.setAtributosPersonagem(novoPersonagem);
           
            //personagem é inserido a lista
            charList.inserirFim(novoPersonagem);
        } while (true);

        //---------------------- SEGUNDA PARTE ----------------------------//

        // //dados inicializados
        int numComp = 0;
        long tempoInicial = System.currentTimeMillis();
        
        numComp = charList.insertionSort();

        for (int i = 0; i < charList.n; i++) {
            MyIO.println(charList.array[i].toString() );
        } 

        Arq.openWrite("748189_insercao.txt");
        Arq.print("748189" + "\t"+ (System.currentTimeMillis() - tempoInicial) + "\t" + numComp);
        Arq.close();
    }
}