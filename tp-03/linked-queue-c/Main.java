//--------------------------filar circular----------------------------------//

//class fila circular

class Queue{
    private CellPers first;
    private CellPers last;
    private int length;
    private int capacity;

    public Queue(){
        this(5);
    }

    public Queue(int capacity) {
        this.first = this.last = null;
        this.length = 0;
        this.capacity = capacity;
    }

    public int length() {
        return this.length;
    }

    public void print() {
        int i = 0;

        MyIO.println("[" + i++ + "] " + this.first.element.toString());
        CellPers aux = this.first.next;

        while(aux != this.first) {
            MyIO.println("[" + i++ + "] " + aux.element.toString());
            aux = aux.next;
        }
    }

    public void push(Personagem pers) {
        if(this.length == this.capacity) this.pop();

        CellPers tmp = new CellPers(pers);

        if(this.first == null) {
            this.first = tmp;
        }else {
            this.last.next = tmp;
        }
        
        this.last = tmp;
        this.last.next = this.first;
        tmp = null;

        this.length++;
    }

    public Personagem pop() {
        if(this.length > 0) {
            Personagem game = this.first.element;

            if(this.first == this.last) {
                this.first = this.last = null;
            }else {
                this.first = this.first.next;
                this.last.next = this.first;
            }

            this.length--;
            
            return game;
        }

        return null;
    }

    public Personagem get(int index) {
        if(index >= 0 && index < this.length) {
            int i = 0;
            CellPers aux = this.first;
            
            while(aux.hasNext() && i < index) {
                aux = aux.next;
                i++;
            }

            Personagem pers = aux.element;
            aux = null;
            
            return pers;
        }

        return null;
    }

    public int getMedia() {
        float media = 0;
        
        if(this.length > 0) {
            media += this.first.element.getAltura();
            CellPers aux = this.first.next;

            if(this.first != this.last) {
                while(aux != this.first) {
                    media += aux.element.getAltura();
                    aux = aux.next;
                }
            }

            media /= this.length;
        }

        return Math.round(media);
    }
}

//----------------------------Celula Personagem------------------------------//

class CellPers 
{
    public Personagem element;
    public CellPers next;

    CellPers() {
        this(null);
    }
    
    CellPers(Personagem pers) {
        this.element = pers;
        this.next = null;
    }

    public void setElement(Personagem element) {
        this.element = element;
    }

    public boolean hasNext(){
        return this.next != null;
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

    public int separarPeso() {
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
        if (peso.equals("unknown")) {
            return 0;
        } else {
            double pesoDouble = Double.parseDouble(peso);
            if (Math.floor(pesoDouble) == pesoDouble) {
                return (int) pesoDouble;
            } else {
                return (int) Math.round(pesoDouble);
            }
        }
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
        pers.setPeso           (this.getPeso());
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
        // double peso = Double.parseDouble(this.separarPeso() ); 
        double peso = this.separarPeso();
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
    public static void main(String[] args) throws Exception{
        MyIO.setCharset("utf-8");
        
        Queue queuePers = new Queue(5);
        GerenciadorDeArquivo gerenciador = new GerenciadorDeArquivo(); 
        
        do { 
            //ler caminho do arquivo
            gerenciador.caminhoArquivo = MyIO.readLine();
            if(isFim(gerenciador.caminhoArquivo)) break;
            
            // cria-se referencia a novo personagem
            Personagem pers = new Personagem();
            
            //gerenciador ler valores do arquivo
            gerenciador.lerArquivo();
            
            //set atributos do personagem
            gerenciador.setAtributosPersonagem(pers);
            
            //personagem é inserido a fila
            queuePers.push(pers);

            //print da media de altura
            MyIO.println(queuePers.getMedia()); 
           
        } while (true);

//---------------------------------------------------------------------------------//

        int numOp =  Integer.parseInt( MyIO.readLine() );
        String tmp = "";

        for (int k = 0; k < numOp; k++) {
            tmp = MyIO.readLine();
            String func = tmp.substring(0, 1);

            //inserir            
            if( func.compareTo("I") == 0) {
                Personagem pers = new Personagem();
                
                gerenciador.caminhoArquivo = tmp.substring(tmp.indexOf(" ", 1) + 1, tmp.length());

                gerenciador.lerArquivo();
                gerenciador.setAtributosPersonagem(pers);
            
                //personagem é inserido a lista
                queuePers.push(pers);

                //printarmedia
                MyIO.println( queuePers.getMedia() );
            }

            //remover
            else { 
                MyIO.println("(R) " + queuePers.pop().getNome() );
            }
        }
    }
}