class Trie
{
    private Node root;

    public Trie() { root = new Node(); }
   
    public void insert(String s) {
        insert(s, root, 0);
    }

    private void insert(String s, Node node, int idx) {
        if(node.next[s.charAt(idx)] == null) {
            node.next[s.charAt(idx)] = new Node(s.charAt(idx));

            if(idx == s.length() - 1) {
                node.next[s.charAt(idx)].isString = true;
            } else {
                insert(s, node.next[s.charAt(idx)], idx+1);
            }

        } else if(node.next[s.charAt(idx)].isString == false && idx < s.length() - 1) {
            insert(s, node.next[s.charAt(idx)], idx+1);
        }
    }

    public boolean search(String s) {
        return search(s, root, 0);
    }
    
    private boolean search(String s, Node node, int idx) {
        boolean ans = false;
        
        if(node.next[s.charAt(idx)] == null) ans = false;
        else if(idx == s.length() - 1)       ans = (node.next[s.charAt(idx)].isString == true);
        else if(idx < s.length() - 1)        ans = search(s, node.next[s.charAt(idx)], idx+1);

        return ans;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}


class Node
{
    public char element;
    public final int len = 255;
    public Node[] next;
    public boolean isString;

    public Node() {
        this(' ');
    }

    public Node(char element) {
        this.element = element;
        next = new Node[len];
        for (int i = 0; i < len; i++) next[i] = null;
        isString = false;
    }

    public static int hash( char x ) {
        return (int)x;
    }
}
//-------------------------------Character  ----------------------------------//

class Character 
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
    Character () {
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
    Character (String nome, int altura, double peso, String corDoCabelo, String corDaPele,
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

    public Character  clone() {
        Character  clone = new Character ();

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
    public int compare(Character  pers2) {
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

    public void setAtributosPersonagem(Character  pers) {
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
        Trie trieChar = new Trie();
        FileManagement file = new FileManagement(); 

        do { 
            file.pathFile = MyIO.readLine();
            if(isFim(file.pathFile)) break;
            
            Character pers = new Character ();
            
            file.readFile();
            file.setAtributosPersonagem(pers);

            trieChar.insert(pers.getNome()); 

        } while (true);
        do { 
            file.pathFile = MyIO.readLine();
            if(isFim(file.pathFile)) break;
            
            Character pers = new Character ();
            
            file.readFile();
            file.setAtributosPersonagem(pers);

            trieChar.insert(pers.getNome()); 

        } while (true);

        do { 
            String nomePers = MyIO.readLine();
            if(isFim(nomePers)) break;
            
            MyIO.print(nomePers + ' ');
            MyIO.println(trieChar.search(nomePers)? "SIM":"N\uFFFDO");

        } while (true);
    }
}