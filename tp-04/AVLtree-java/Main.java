class AVL 
{
    public NodePers root;
    public int height;
    
    public AVL() {
        this.root = null;
        this.height = 0;
    }
    
    public AVL(NodePers root) {
        this.root = root;
        this.height++;
    }

    //print
    public void show() {
        show_pre(root);
    }
    public void show_pre(NodePers i)  {
        if(i != null) {
            MyIO.println(i.element.getNome());
            show_pre(i.left);
            show_pre(i.right);
        }
    }

    //inserir
    public void insert(Personagem pers) throws Exception {
        root = insert(pers, root);
    }
    
    //inserir recursivo
    public NodePers insert(Personagem pers, NodePers i) throws Exception {
        if(i == null) { i = new NodePers(pers); }
        else if( pers.compare(i.getElement()) < 0 ) {
            i.left = insert(pers, i.left);
        
        } 
        else if( pers.compare(i.getElement()) > 0 ) {
            i.right = insert(pers, i.right);
        
        } else { throw new Exception("ERROR."); }
        
        return balance(i);
    }
    
    //simple rotate left
    private NodePers rotateLeft(NodePers node) {
        NodePers rightNode = node.right;
        NodePers rigLefNode = rightNode.left;

        rightNode.left = node;
        node.right = rigLefNode;

        node.setNivel();
        rightNode.setNivel();

        return rightNode;
    }
    
    //simple rotate right
    private NodePers rotateRight(NodePers node) {
        NodePers leftNode = node.left;
        NodePers lefRigNode = leftNode.left;

        leftNode.right = node;
        node.left = lefRigNode;

        node.setNivel();
        leftNode.setNivel();

        return leftNode;
    }

    //balancear
    private NodePers balance(NodePers node)  {
        if(node != null) {
            int fator = node.getNivel(node.right) - node.getNivel(node.left);
            
            //balanceada
            if( Math.abs(fator) <= 1 ) {
                node.setNivel();
            
            } else if(fator == 2) {
                int fatorFilhoDir = node.getNivel(node.right.right) - node.getNivel(node.right.left);
                if( fatorFilhoDir == -1 ) {
                    node.right = rotateRight(node.right);
                }
                
                node = rotateLeft(node);
            }
            
            //se balanceada para esquerda
            else if(fator == -2) {
                int fatorFilhoEsq = node.getNivel(node.left.right) - node.getNivel(node.left.left);
                //se o filho a esquerda tambem estiver desbalanceado
                if(fatorFilhoEsq == 1) {
                    node.left = rotateLeft(node.left);
                }
                
                node = rotateRight(node);
            }
        } 
        
        return node;
    }

    //pesquisar
    public boolean search(String name) {
        MyIO.print("raiz ");
        return search(name, root);
    }

    //persquisar recursivo
    public boolean search(String name, NodePers i) {
        boolean ans = false;

        if(i == null) ans = false; 
        else if( name.compareTo(i.getElement().getNome() ) == 0 ) ans = true;
        else if( name.compareTo(i.element.getNome()) < 0) { 
            MyIO.print("esq ");
            ans = search(name, i.left);
        }
        else {
            MyIO.print("dir ");
            ans = search(name, i.right);
        }     
        
        return ans;
    }

    //getters 
    public int getHeight()    { return height; }
    public NodePers getRoot() { return root;   }
    
    //setters
    public void setRoot(NodePers root) { this.root = root;     }
    public void setHeight(int height)  { this.height = height; }

}

//----------------------------Node Personagem------------------------------//

class NodePers 
{
    public Personagem element;
    public NodePers left;
    public NodePers right;
    public int nivel;

    NodePers(Personagem pers) {
        this(pers, null, null);
    }
    
    NodePers(Personagem pers, NodePers left, NodePers right) {
        this.element = pers;
        this.left = left;
        this.right = right;
    }

    //has
    public boolean hasLeft()  { return this.left != null;  }
    public boolean hasRight() { return this.right != null; }

    //getters
    public Personagem getElement()              { return element; }
    public NodePers getLeft()                   { return left; }
    public NodePers getRight()                  { return right; }
    public int getNivel(NodePers node)   { return (node == null) ? 0 : node.nivel; }
    
    //setters
    public void setLeft(NodePers left)          { this.left = left; }
    public void setRight(NodePers right)        { this.right = right; }
    public void setNivel()                      { this.nivel = 1 + Math.max(getNivel(left), getNivel(right)); }
    public void setElement(Personagem element)  { this.element = element; }
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
        //objetos
        AVL avlPers = new AVL();
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
            avlPers.insert(pers); 

        } while (true);

        // avlPers.show();
        //persquina na arvore
        do { 
            //ler nome de personagem a ser procurado
            String nomePers = MyIO.readLine();
            if(isFim(nomePers)) break;
            
            MyIO.print(nomePers + ' ');
            MyIO.println(avlPers.search(nomePers)? "SIM":"NÃO");

        } while (true);
    }
}