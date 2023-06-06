class RedBlack 
{
    public Node root;
    
    public RedBlack() {
        this.root = null;
    }
    
    public RedBlack(Node root) {
        this.root = root;
    }

    //print
    public void show() {
        show_pre(root);
    }
    public void show_pre(Node i)  {
        if(i != null) {
            MyIO.println(i.element.getNome());
            show_pre(i.left);
            show_pre(i.right);
        }
    }

    public void insert(Personagem pers) {
        if( root == null ) {
            root = new Node(pers);
        
        } else if( root.left == null && root.right == null ) {
            if( pers.getNome().compareTo(root.element.getNome()) < 0) {
                root.left = new Node(pers);
            
            } else {
                root.right = new Node(pers);
            }

        } else if( root.left == null ) {
            if(pers.getNome().compareTo(root.element.getNome()) < 0) {
                root.left = new Node(pers);
            
            } else if(pers.getNome().compareTo(root.right.element.getNome()) < 0) {
                root.left = new Node(root.element);
                root.element = pers;
            
            } else {
                root.left = new Node(root.element);
                root.element = root.right.element;
                root.right.element = pers;
            
            }
            root.left.type = root.right.type = false;

        } else if( root.right == null ) {
            if( pers.getNome().compareTo(root.element.getNome()) > 0) {
                root.right = new Node(pers);
            
            } else if( pers.getNome().compareTo(root.left.element.getNome()) > 0) {
                root.right = new Node(root.element);
                root.element = pers;
            
            } else {
                root.right = new Node(root.element);
                root.element = root.left.element;
                root.left.element = pers;
            }
            
            root.left.type = root.right.type = false;
        
        } else { insert(pers, null, null, null, root); }
        
        root.type = false;
    }

    private void balancear(Node bisavo, Node avo, Node pai, Node i) {
        if(pai.type == true) {
            if(pai.element.getNome().compareTo(avo.element.getNome()) > 0) {
                if(i.element.getNome().compareTo(pai.element.getNome()) > 0) {
                    avo = rotacaoLeft(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }
            } else {
                if(i.element.getNome().compareTo(pai.element.getNome()) < 0) {
                    avo = rotacaoRight(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }

            if(bisavo == null) {
                root = avo;
            } else if( avo.element.getNome().compareTo(bisavo.element.getNome()) < 0 ) {
                bisavo.left = avo;
            } else {
                bisavo.right = avo;
            }

            avo.type = false;
            avo.left.type = avo.right.type = true;

        }
    }

    private void insert(Personagem pers, Node bisavo, Node avo, Node pai, Node i) {
        if(i == null) {
            if(pers.getNome().compareTo(pai.element.getNome()) < 0) {
                i = pai.left = new Node(pers, true);
            }else { 
                i = pai.right = new Node(pers, true);
            }
            if(pai.type == true) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            if(i.left != null && i.right != null && i.left.type == true && i.right.type == true) {
                i.type = true;
                i.left.type = i.right.type = false;
                if(i == root) {
                    i.type = false;
                } else if(pai.type == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            
            if(pers.getNome().compareTo(i.element.getNome()) < 0) {
                insert(pers, avo, pai, i, i.left);
            } else if(pers.getNome().compareTo(i.element.getNome()) > 0) {
                insert(pers, avo, pai, i, i.right);
            }

        }
    }

    private Node rotacaoRight(Node no) {
        Node noEsq = no.left;
        Node noEsqDir = noEsq.right;
  
        noEsq.right = no;
        no.left = noEsqDir;
  
        return noEsq;
     }
  
     private Node rotacaoLeft(Node no) {
        Node noDir = no.right;
        Node noDirEsq = noDir.left;
  
        noDir.left = no;
        no.right = noDirEsq;
        return noDir;
     }
  
     private Node rotacaoDirEsq(Node no) {
        no.right = rotacaoRight(no.right);
        return rotacaoLeft(no);
     }
  
     private Node rotacaoEsqDir(Node no) {
        no.left = rotacaoLeft(no.left);
        return rotacaoRight(no);
     }

    //pesquisar
    public boolean search(String name) {
        MyIO.print("raiz ");
        return search(name, root);
    }

    private boolean search(String name, Node i) {
        boolean resp;
        if(i == null) {
            resp=false;
        } else if( name.compareTo(i.element.getNome()) == 0) {
            resp = true;
        } else if( name.compareTo(i.element.getNome()) < 0 ) {
            MyIO.print("esq ");
            resp = search(name, i.left);
        } else {
            MyIO.print("dir ");
            resp = search(name, i.right);
        }

        return resp;
    }

    //getters 
    public Node getRoot() { return root;   }
    
    //setters
    public void setRoot(Node root) { this.root = root;     }

}

//----------------------------Node Personagem------------------------------//

class Node {
    public Personagem element;
    public Node left;
    public Node right;
    public boolean type;

    public Node (Personagem pers) {
        this(pers, null, null, false);
    }

    public Node(Personagem pers, boolean type) {
        this(pers, null, null, type);
    }

    public Node() {
        this(null, null, null, false);
    }
    public Node(Personagem pers, Node left, Node right, boolean type) {
        this.element = pers;
        this.type = type;
        this.left = left;
    }
    
    //getters
    public Personagem getElement()      { return element; }    
    public Node getLeft()               { return left; }
    public Node getRight()              { return right; }
    public boolean isType()             { return type; }
    //setters
    public void setElement(Personagem element)  { this.element = element; }
    public void setLeft(Node left)              { this.left = left; }
    public void setRight(Node right)            { this.right = right; }
    public void setType(boolean type)           { this.type = type; }
    
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

    //compare
    public int compare(Personagem pers2) {
        int resp = -1;
        if (pers2 != null) resp = this.getNome().compareTo(pers2.getNome());
        return resp;
    }

} 
//----------------------------FileManagement-----------------------------//

class FileManagement
{
    //------------------------------atributos-----------------------------------//

    public String pathFile;
    public String contentFile;

    //------------------------------readFile---------------------------------//
    
    public void readFile() {
        Arq.openRead(this.pathFile);
        this.contentFile = Arq.readLine();
        Arq.close();
    }

    //---------------------------Separar-valor-do-Nome------------------------//
    public String separarNome() {
        int indiceDoAtributo = this.contentFile.indexOf("'name'") + 9;
        String name = "";
        while (this.contentFile.charAt(indiceDoAtributo) != 39) {
            name += contentFile.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        
        return name;
    }

    //-----------------------------Separar-Altura----------------------------//

    public String separarAltura() {
        int indiceDoAtributo = this.contentFile.indexOf("'height'") + 11;
        String altura = "";
        while (this.contentFile.charAt(indiceDoAtributo) != 39) {
            altura += contentFile.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        if(altura.equals("unknown")) altura = "0";
        return altura;
    }

    public int separarPeso() {
        int indiceDoAtributo = this.contentFile.indexOf("'mass'") + 9;
        String peso = "";
        while (this.contentFile.charAt(indiceDoAtributo) != 39) {
            if (contentFile.charAt(indiceDoAtributo) == ',') {
                peso += '.'; 
                indiceDoAtributo++;
                continue;
            }
            
            peso += contentFile.charAt(indiceDoAtributo);
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
        int indiceDoAtributo = this.contentFile.indexOf("'hair_color'") + 15;
        String corDoCabelo = "";
        while (this.contentFile.charAt(indiceDoAtributo) != 39) {
            corDoCabelo += contentFile.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        return corDoCabelo;
    }

    //-----------------------separar-cor-da-pele-------------------------//

    public String separarCorDaPele() {
        int indiceDoAtributo = this.contentFile.indexOf("'skin_color'") + 15;
        String corDaPele = "";
        while (this.contentFile.charAt(indiceDoAtributo) != 39) {
            corDaPele += contentFile.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        return corDaPele;
    }

    //-----------------------separar-cor-dos-olhos-------------------//

    public String separarCorDosOlhos() {
        int indiceDoAtributo = this.contentFile.indexOf("'eye_color'") + 14;
        String corDosOlhos = "";
        while (this.contentFile.charAt(indiceDoAtributo) != 39) {
            corDosOlhos += contentFile.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        return corDosOlhos;
    }

    //-----------------------data-de-nascimento---------------------//

    public String separarDataNascimento() {
        int indiceDoAtributo = this.contentFile.indexOf("'birth_year'") + 15;
        String dataNascimento = "";
        while (this.contentFile.charAt(indiceDoAtributo) != 39) {
            dataNascimento += contentFile.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        return dataNascimento;
    }

    //------------------------separar-genero-----------------------//
    
    public String separarGenero() {
        int indiceDoAtributo = this.contentFile.indexOf("'gender'") + 11;
        String genero = "";
        while (this.contentFile.charAt(indiceDoAtributo) != 39) {
            genero += contentFile.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        return genero;
    }

    //----------------------separar-homeworld---------------------//

    public String separarHomeworld() {
        int indiceDoAtributo = this.contentFile.indexOf("'homeworld'") + 14;
        String homeworld = "";
        while (this.contentFile.charAt(indiceDoAtributo) != 39) {
            homeworld += contentFile.charAt(indiceDoAtributo);
            indiceDoAtributo++;
        }
        return homeworld;
    }

    //----------------------set atributos personagem--------------------------//

    public void setAtributosPersonagem(Personagem pers) {
        pers.setNome           ( this.separarNome() );
        pers.setAltura         ( Integer.parseInt( this.separarAltura() ));
        pers.setPeso           ( this.getPeso() );
        pers.setCorDaPele      ( this.separarCorDaPele() );
        pers.setCorDoCabelo    ( this.separarCorDoCabelo() );
        pers.setCorDosOlhos    ( this.separarCorDosOlhos() );
        pers.setAnoNascimento  ( this.separarDataNascimento() );
        pers.setGenero         ( this.separarGenero() );
        pers.setHomeWorld      ( this.separarHomeworld() );      
            
    }

    //------------------------------setters-------------------------------//
    
    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
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
        // MyIO.setCharset("utf-8");
        
        //objetos
        RedBlack red_black_tree = new RedBlack();
        FileManagement file = new FileManagement(); 

        //leitura de objetos (personagen)
        do { 
            //ler caminho do arquivo
            file.pathFile = MyIO.readLine();
            if(isFim(file.pathFile)) break;
            
            // cria-se referencia ao novo personagem
            Personagem pers = new Personagem();
            
            //file ler valores do arquivo
            file.readFile();
            
            //set atributos do personagem
            file.setAtributosPersonagem(pers);

            //personagem é inserido na arvore avl
            red_black_tree.insert(pers); 

        } while (true);

        // avlPers.show();
        //persquina na arvore
        do { 
            //ler nome de personagem a ser procurado
            String nomePers = MyIO.readLine();
            if(isFim(nomePers)) break;
            
            MyIO.print(nomePers + ' ');
            MyIO.println(red_black_tree.search(nomePers)? "SIM":"NÃO");

        } while (true);
    }
}