//--------------------------arvore binaria----------------------------------//
//class arvore binaria - pesquisa
class BinaryTree 
{
    //atributes
    public Node root;
    
    //constructor
    BinaryTree() {
        root = null;
    }

    //constructor
    BinaryTree(int x) {
        root = new Node(x);
    }

    //inittiate data
    public void init_bin() {
       this.insert(3);
       this.insert(11);
       this.insert(1);
       this.insert(5);
       this.insert(9);
       this.insert(12);
       this.insert(0);
       this.insert(2);
       this.insert(4);
       this.insert(6);
       this.insert(8);
       this.insert(10);
       this.insert(13);
       this.insert(14);
    }

    //show Nodes printing left first
    public void showValues() {
        // mostrarPre(this.root);
        // mostrarCentral(root);
        // mostrarPos(root);
    }

    public void mostrarPre(Node i) {
        if(i != null) {
            mostrarPre(i.left);
            MyIO.println(i.h_mod_15);
            mostrarPre(i.right);
        }
    }

    public void mostrarCentral(Node i) {
        if(i != null) {
            MyIO.println(i.h_mod_15);
            mostrarCentral(i.left);
            mostrarCentral(i.right);
        }
    }

    public void mostrarPos(Node i) {
        if(i != null) {
            mostrarPos(i.left);
            mostrarPos(i.right);
            MyIO.println(i.h_mod_15);
        }
    }

    //search Node with height mod 15.
    public Node search(int x)  {
        return  search(x, root);
        
    }

    //search Node with height mod 15. recursive
    public Node search(int x, Node i)  {
        if(i == null)               { i = null; }
        else if (x == i.h_mod_15)   { return i; } 
        else if(x < i.h_mod_15)     { i = search(x, i.left); }
        else { i = search(x, i.right); }
        return i;
    }

    //insert
    public void insert(int x) {
        root = insert(x, root);
    }

    //insert recursive
    public Node insert(int x, Node i) {
        if (i == null) { i = new Node(x);  } 
        else if(x < i.h_mod_15 ) {   i.left = insert(x, i.left );  }
        else if(x > i.h_mod_15 ) {   i.right = insert(x, i.right); }
        return i;
    }

    //insert pers
    public void insert_pers(Personagem pers) {
        int h_mod = (pers.getAltura() % 15);
        insert_pers(h_mod, pers, root);
    }

    //walkin in first tree
    public void insert_pers(int h_mod, Personagem pers, Node i) {
        if(i != null) {
            if(i.h_mod_15 == h_mod) { i.rootPers = insert_pers(pers, i.rootPers); }
            else if(i.h_mod_15 > h_mod) { insert_pers(h_mod, pers, i.left);  }
            else if(i.h_mod_15 < h_mod) { insert_pers(h_mod, pers, i.right); }
        }
    }

    //walkin in second tree
    public NodePers insert_pers(Personagem pers, NodePers i) {
        if(i == null) { i = new NodePers(pers); } 
        else if( pers.getNome().compareTo(i.element) < 0 ) { i.left = insert_pers(pers, i.left);   } 
        else if( pers.getNome().compareTo(i.element) > 0 ) { i.right = insert_pers(pers, i.right); }

        return i;
    }

    //search element in second tree
    public boolean search_pers(String name) {
        MyIO.print("raiz ");
        return search_pers(name, root);
    }

    //walkin in first tree
    public boolean search_pers(String name, Node i) {
        boolean resp = false;
        if(i != null) {
            resp = search_pers(name, i.rootPers);
            if(resp == false) { MyIO.print("esq ");  resp = search_pers(name, i.left);  }
            if(resp == false) {  MyIO.print("dir "); resp = search_pers(name, i.right); }
        }
       
        return resp;
    }

    //walkin in second tree
    public boolean search_pers(String name, NodePers i) {
        boolean resp = false;
        if(i != null) {
            if(name.compareTo(i.element) == 0) { resp = true; return resp; }
            { MyIO.print("ESQ "); search_pers(name, i.left);  }
            { MyIO.print("DIR "); search_pers(name, i.right); }
        }
        
        return resp;
    }

    public void sub_caminhar() {
        sub_caminhar(root);
    }

    public void sub_caminhar(Node i) {
        if(i != null) {
            sub_caminhar(i.left);
            sub_caminhar(i.rootPers);
            sub_caminhar(i.right);
        }
    }

    public void sub_caminhar(NodePers i) {
        if(i != null) {
            MyIO.println(i.element);
            sub_caminhar(i.left);
            sub_caminhar(i.right);
        }
    }

}
/**
 * @version v0.1
 * Class Node
*/
class Node 
{
    //atributes
    public int h_mod_15;
    
    //ref of root from other tree
    public NodePers rootPers;
    public Node left;
    public Node right;

    Node(int height_mod) { this(height_mod, null, null); }
    Node(int height_mod, Node left, Node right) {
        h_mod_15 = height_mod;
        this.left = left;
        this.right = right;
        rootPers = null;
    }

    //has
    public boolean hasLeft()                    { return this.left != null; }
    public boolean hasRight()                   { return this.right != null;}
}

//----------------------------NodePers------------------------------//

class NodePers 
{
    //atributes
    public String element;
    public NodePers left;
    public NodePers right;

    //constructors
    NodePers(Personagem pers) { this(pers, null, null); }
    NodePers(Personagem pers,
             NodePers left, NodePers right) {
        this.element = pers.getNome();
        this.left = left;
        this.right = right;
    }

    //has
    public boolean hasLeft()                    { return this.left != null; }
    public boolean hasRight()                   { return this.right != null;}
    
    //getters
    public String getElement()                  { return element; }
    public NodePers   getLeft()                 { return left; }
    public NodePers   getRight()                { return right; }
    
    //setters
    public void setLeft(NodePers left)          { this.left = left; }
    public void setRight(NodePers right)        { this.right = right; }
    public void setElement(Personagem element)  { this.element = element.getNome(); }
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
        this.nome               = nome; 
        this.altura             = altura;
        this.peso               = peso; 
        this.corDoCabelo        = corDoCabelo;
        this.CorDaPele          = corDaPele;
        this.corDosOlhos        = corDosOlhos;
        this.anoNascimento      = anoNascimento;
        this.genero             = genero;
        this.homeworld          = homeworld;
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

    //compare
    public int compare(Personagem pers2) {
        int resp = -1;
        if (pers2 != null) resp = this.getNome().compareTo(pers2.getNome());
        return resp;
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
    public static void main(String[] args) throws Exception {
        MyIO.setCharset("utf-8");
        
        //objetos
        BinaryTree binTreePers = new BinaryTree(7);
        GerenciadorDeArquivo gerenciador = new GerenciadorDeArquivo(); 

        //inicializar valores do mod altura (balanceamento force brute)
        binTreePers.init_bin();
        // binTreePers.showValues();
        binTreePers.sub_caminhar();

        //leitura de objetos (personagem)
        do { 
            //ler caminho do arquivo
            gerenciador.caminhoArquivo = MyIO.readLine();
            if(isFim(gerenciador.caminhoArquivo)) break;
            
            // cria-se referencia ao novo personagem
            Personagem pers = new Personagem();
            
            gerenciador.lerArquivo(); //gerenciador ler valores do arquivo
            gerenciador.setAtributosPersonagem(pers); //set atributos do personagem

            //personagem é inserido na sub_arvore
            binTreePers.insert_pers(pers); 

        } while (true);
        // binTreePers.caminhar();
        // binTreePers.sub_caminhar();


        // persquina na arvore
        do { 
            //ler nome de personagem a ser procurado
            String nomePers = MyIO.readLine();
            if(isFim(nomePers)) break;
            
            MyIO.print(nomePers + ' ');
            MyIO.println(binTreePers.search_pers(nomePers)? "SIM":"NÃO");

        } while (true);
    }
}