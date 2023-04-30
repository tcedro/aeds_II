// import java.lang.Exception;
//--------------------------Lista flexivel----------------------------------//

class ListPers 
{
    //atributes
    private CellPers first;
    private CellPers last;
    public int length;
    
    //constructor
    public ListPers() {
        first = new CellPers();
        last  = first;
        length = 0;
    }

    //getters
    public CellPers getFirst() {
        return first;
    }

    public CellPers getLast() {
        return last;
    }

    //insert
    public void insert(Personagem pers, int pos) throws Exception {
        if( pos < 0 || pos > length )  throw new Exception("ERROR:position invalid!");
        else if( pos == 0 )      insertStart(pers);
        else if( pos == length ) insertEnd(pers);
        else {
            CellPers i = first;
            for(int j = 0; j < pos; j++, i = i.next);
            CellPers tmp = new CellPers(pers);

            tmp.next = i.next;
            i.next = tmp;
            length++;

            tmp = i = null;
        }
    }

    //insert start
    public void insertStart(Personagem pers) {
        CellPers tmp = new CellPers(pers);
        tmp.next = first.next;
        first.next = tmp;
        if(first == last) last = tmp;
        length++;
        tmp = null;
    }

    //insert end
    public void insertEnd(Personagem pers) {
        last.next = new CellPers(pers);
        last = last.next;
        length++;
    }

    //remove start
    public Personagem removeStart() throws Exception {
        if(first == last) { throw new Exception("ERROR:list empty!");}
        CellPers tmp = first;
        first = first.next;
        Personagem removed = first.element;
        tmp.next = null;
        tmp = null;
        return removed;
    }

    //remove end
    public Personagem removeEnd() throws Exception {
        if(first == last) throw new Exception("ERROR!");
        
        CellPers i;
        
        for(i = first; i.next != last; i = i.next);
        Personagem removed = last.element;
        last = i;
        i = last.next;
        last.next = null;
        length--;
        
        return removed;
    }

    public Personagem remove(int pos) throws Exception {
        Personagem removed; 
        if( first == last || pos < 0 || pos >= length ) throw new Exception("ERROR:list empty or position invalid!");
        else if( pos == 0 )        removed = removeStart();
        else if( pos == length-1 ) removed = removeEnd();
        else { 
            CellPers i = first;
            for(int j = 0; j < pos; j++, i = i.next);
            CellPers tmp = i.next;

            removed = tmp.element; i.next = tmp.next;
            tmp.next = null; i = tmp = null;

            length--;
        }
       
        return removed;
    }

    public void showList() {
        CellPers i = getFirst().next;
        int j = 0 ;
        while( i != null) {
            MyIO.println("[" +j+ "] " + i.element.toString());
            i = i.next;
            j++;
        }
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
        //gerenciador de caminho e conteudo de arquivos
        GerenciadorDeArquivo gerenciador = new GerenciadorDeArquivo(); 
        ListPers list_pers = new ListPers();        
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
            
            //personagem é inserido a lista
            list_pers.insertEnd(pers);
            

        } while (true);

        //---------------------------------------------------------------------------------//

        int numOp =  Integer.parseInt( MyIO.readLine() );
        String tmp = "";

        for (int k = 0; k < numOp; k++) {
            tmp = MyIO.readLine();
            String func = tmp.substring(0, 2);

            //------------------------------- Inserir no inicio --------------------------------------//
            
            if ( func.compareTo("II") == 0 ) {
                gerenciador.caminhoArquivo = tmp.substring(3, tmp.length());
                Personagem pers = new Personagem();

                gerenciador.lerArquivo();
                gerenciador.setAtributosPersonagem(pers);
            
                //personagem é inserido a lista
                list_pers.insertStart(pers);
            }

            //------------------------------ Inserir no fim ----------------------------------//
            
            else if( func.compareTo("IF") == 0 ) {
                gerenciador.caminhoArquivo = tmp.substring(3, tmp.length());
                Personagem pers = new Personagem();

                gerenciador.lerArquivo();
                gerenciador.setAtributosPersonagem(pers);
            
                //personagem é inserido a lista
                list_pers.insertEnd(pers);
            }

            //------------------------------ Inserir ----------------------------------//
            
            else if( func.compareTo("I*") == 0) {
                Personagem pers = new Personagem();
                Integer pos = Integer.parseInt(tmp.substring(3, tmp.indexOf(" ", 3)));
                gerenciador.caminhoArquivo = tmp.substring(tmp.indexOf(" ", 3) + 1, tmp.length());

                gerenciador.lerArquivo();
                gerenciador.setAtributosPersonagem(pers);
            
                //personagem é inserido a lista
                list_pers.insert(pers, pos);
            }
            
            //------------------------------ Remover no inicio ----------------------------------//

            else if( func.compareTo("RI") == 0) { MyIO.println( "(R) " + list_pers.removeStart().getNome() ); }

            //------------------------------ Remover no fim ----------------------------------//

            else if( func.compareTo("RF") == 0) { MyIO.println( "(R) " + list_pers.removeEnd().getNome() ); }

            //----------------------------------- Remover ----------------------------------//

            else if( func.compareTo("R*") == 0) {
                Integer pos = Integer.parseInt(tmp.substring(3, tmp.length()));
                MyIO.println( "(R) " + list_pers.remove(pos).getNome() );
            }
        }
    
        list_pers.showList();
    }
}