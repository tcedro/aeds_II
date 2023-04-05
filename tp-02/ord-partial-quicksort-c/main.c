//---------------------------Bibliotecas----------------------------------//
#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
//------------------------Assinaturas------------------------------//

char* mallocString(char *str);

//--------------------------DEFINES-----------------------------------//

#define MAX 100

//--------------------------VarGlobal------------------------------//

int numSwapsQuickSort    = 0;
int numCompsQuickSort = 0;

//----------------------------Personagens----------------------------//

typedef struct 
{
    char *nome;
    int altura;
    double peso;
    char *corDoCabelo;
    char *corDosOlhos;
    char *corDaPele;
    char *anoNascimento;
    char *genero;
    char *homeworld;    

} Personagem;
//--------------------------------clone------------------------------------------//

Personagem clone(Personagem clone, Personagem old) {
    clone.nome          = old.nome;
    clone.altura        = old.altura;
    clone.peso          = old.peso;
    clone.corDoCabelo   = old.corDoCabelo;
    clone.corDosOlhos   = old.corDosOlhos;
    clone.corDaPele     = old.corDaPele;  
    clone.anoNascimento = old.anoNascimento;
    clone.genero        = old.genero;
    clone.homeworld     = old.homeworld;
    
    return clone;
}

//-------------------------------setters-----------------------------------------//

void setNome          ( char *nome, Personagem *p )          { p->nome = nome; }
void setAltura        ( int altura, Personagem *p )          { p->altura = altura; }
void setPeso          ( double peso, Personagem *p )         { p->peso = peso; }
void setCorDaPele     ( char *corDaPele, Personagem *p )     { p->corDaPele = corDaPele;     }
void setCorDosOlhos   ( char *corDosOlhos, Personagem *p )   { p->corDosOlhos = corDosOlhos; }
void setCorDoCabelo   ( char *corDoCabelo, Personagem *p )   { p->corDoCabelo = corDoCabelo; }
void setAnoNascimento ( char *anoNascimento, Personagem *p ) { p->anoNascimento = anoNascimento; }
void setGenero        ( char *genero, Personagem *p )        { p->genero = genero; }
void setHomeworld     ( char *nome, Personagem *p )          { p->homeworld = nome; }

//-------------------------------getters---------------------------------------------//

char*  getNome(Personagem *p)          { return p->nome; }
int    getAltura(Personagem *p)        { return p->altura; }
double getPeso(Personagem *p)          { return p->peso;  }
char*  getCorDaPele(Personagem *p)     { return p->corDaPele; }
char*  getCorDosOlhos(Personagem *p)   { return p->corDosOlhos; }
char*  getCorDoCabelo(Personagem *p)   { return p->corDoCabelo; }
char*  getAnoNascimento(Personagem *p) { return p->anoNascimento; }
char*  getGenero(Personagem *p)        { return p->genero; }
char*  getHomeworld(Personagem *p)     { return p->homeworld; }

//-------------------------- ListaPersonagens ------------------------------------------//

typedef struct  {
    Personagem *array; 
    int length;

} Lista;

//---------------------------InserirInicio----------------------------------//
void inserirInicio(Personagem elemento, Lista *list) {
    if (list->length < MAX)  {
        for (int i = list->length; i > 0; i--) {
            list->array[i] = list->array[i-1];

        }
        list->array[0] = elemento;
        list->length++;
    }
} 

//---------------------------InserirFim----------------------------------//

void inserirFim(Personagem elemento, Lista *list) {
    if (list->length < MAX)  {
        list->array[list->length++] = elemento;
    }
} 

//---------------------------Inserir----------------------------------//

void inserir(int pos, Personagem elemento, Lista *list) {
        if (pos >= 0 && pos <= MAX && list->length < MAX)  {
        for (int i = list->length; i > pos; i--) {
            list->array[i] = list->array[i-1];

        }
        list->array[pos] = elemento;
        list->length++;
    }
}

//---------------------------RemoverInicio----------------------------------//

Personagem removerInicio(Lista *list) {
    Personagem tmp;
    if (list->length != 0) {
        tmp = list->array[0];
        for (int i = 0; i < list->length - 1; i++) {
            list->array[i] = list->array[i+1];
        }
        list->length--;
    }
    return tmp;
}

//---------------------------RemoverFim----------------------------------//

Personagem removerFim(Lista *list) {
    Personagem tmp;
    if (list->length != 0) {
        tmp = list->array[list->length - 1];
    }
    list->length--;
    return tmp;
}

//---------------------------Remover----------------------------------//

Personagem remover(int pos, Lista *list) {
    Personagem tmp;
    if (pos >= 0 && pos <= list->length && list->length != 0) {
       tmp = list->array[pos]; 

        for (int i = pos; i < list->length - 1; i++) {
            list->array[i] = list->array[i+1];
        }
        list->length--;
    }
    return tmp;
}

//-----------------------Pesquisa Biniaria---------------------------//

bool binSearch(char *name, Lista *list) {
    bool resp = false;
    int esq = 0 , dir = (list->length - 1), meio = 0;

    while ( esq <= dir ) {
        meio = ( esq + dir )  / 2 ;
    
        if ( strcmp(name, list->array[meio].nome) == 0) {
            resp = true;
            esq = dir + 1;
        } else if( strcmp(name, list->array[meio].nome) > 0) {
            esq = meio + 1;
        } else { 
            dir = meio - 1;
        }
    }
    return resp;
}

//-----------------------------Swap--------------------------------------------//

void swap(int pos1, int pos2, Lista *pers) {
    Personagem aux = pers->array[pos1];
    pers->array[pos1] = pers->array[pos2];
    pers->array[pos2] = aux;
}

//------------------------------Func Comp------------------------------------//

bool compareStr(Personagem p1, Personagem p2) {
    bool resp = false;
    if     (   strcmp(p1.corDoCabelo, p2.corDoCabelo) < 0)  resp = true;
    else if(  strcmp(p1.corDoCabelo, p2.corDoCabelo) == 0 ) resp = ( strcmp(p1.nome , p2.nome ) < 0);
    return resp;
}


//----------------------------quickSort--------------------------------------//

void quickSort(int k, int esq, int dir, Lista *listaPers) {
        int i = esq, j = dir;
        Personagem pivo = clone( pivo, listaPers->array[(dir+esq)/2] );
        
        while (i <= j) {
            while ( compareStr (listaPers->array[i], pivo) ) i++; numCompsQuickSort++;
            while ( compareStr(pivo, listaPers->array[j] ) ) j--; numCompsQuickSort++;
            if (i <= j) {
                swap(i, j, listaPers); 
                numSwapsQuickSort++;
                i++;
                j--;
            }
        }
    if(esq < j)           quickSort(k, esq, j, listaPers);
    if( i < k && i < dir) quickSort(k, i, dir, listaPers); 
}

//--------------------------Printlist---------------------------------//

void printList(Lista *list) {
    for (int i = 0; i < 10; i++) {
        // printf("[%d] ", i);
        printf(" ## %s ## %d ## %.0lf ## %s ## %s ## %s ## %s ## %s ## %s ## ", list->array[i].nome, list->array[i].altura, list->array[i].peso,
         list->array[i].corDoCabelo, list->array[i].corDaPele, list->array[i].corDosOlhos, list->array[i].anoNascimento,list->array[i].genero, list->array[i].homeworld);
        printf("\n");
    }
    
}

//--------------------------Gerenciador de Arquivo ------------------------//

typedef struct 
{
    FILE *file;
    char *conteudoDoArquivo;

} GerenciadorDeArquivo;

//-------------------------------Ler Arquivo------------------------------//

void lerArquivo(const char *caminho, GerenciadorDeArquivo *g) {
    // printf("%d ", g->file == NULL);
    g->file = fopen(caminho, "r");
    // g->conteudoDoArquivo = mallocString(g->conteudoDoArquivo);
    fgets(g->conteudoDoArquivo, 1000, g->file);
    fclose(g->file);
}

//---------------------------Separar-valor-do-Nome------------------------//
    
char* separarNome(GerenciadorDeArquivo *this) {
    int i = 0;
    char* ptr = strstr(this->conteudoDoArquivo,"'name': '");
    char* name = mallocString(name);
    ptr += 9;
    while (*ptr != '\'') {
        name[i++] = *ptr;
         ptr++;
    } name[i] = '\0';
        
    return name;
}

//-----------------------------Separar-Altura----------------------------//

char* separarAltura(GerenciadorDeArquivo *this) {
    int i = 0; 
    char* ptr = strstr(this->conteudoDoArquivo,"'height': '");
    ptr += 11;
    char* altura = mallocString(altura);
    while (*ptr != 39) {
        altura[i++] = *ptr;
        ptr++;
          
    } altura[i] = '\0';

    if(strcmp(altura, "unknown") == 0) altura = "0";
    return altura;
}

//-----------------------------Separar-peso----------------------------//

char* separarPeso(GerenciadorDeArquivo *this) {
    int i = 0;
    char* ptr = strstr(this->conteudoDoArquivo,"'mass': '");
    char* peso = mallocString(peso);
    ptr += 9;
    while (*ptr != '\'') {
        peso[i++] = *ptr;
        ptr++;
    } peso[i] = '\0';
        
    return peso;
}
    
//---------------------------Separar-Cor-Do-Cabelo----------------------//

    char* separarCorDoCabelo(GerenciadorDeArquivo *this) {
        int i = 0;
        char* ptr = strstr(this->conteudoDoArquivo,"'hair_color': '");
        char* corDoCabelo = mallocString(corDoCabelo);
        ptr += 15;
        while (*ptr != '\'') {
            corDoCabelo[i++] = *ptr;
            ptr++;
        } corDoCabelo[i] = '\0';
        
        return corDoCabelo;
    }

//-----------------------separar-cor-da-pele-------------------------//

char* separarCorDaPele(GerenciadorDeArquivo *this) {
    int i = 0;
    char* ptr = strstr(this->conteudoDoArquivo,"'skin_color': '");
    char* corDaPele = mallocString(corDaPele);
    ptr += 15;
    while (*ptr != '\'') {
        corDaPele[i++] = *ptr;
        ptr++;
    } corDaPele[i] = '\0';
        
    return corDaPele;
}

//-----------------------separar-cor-dos-olhos-------------------//

char* separarCorDosOlhos(GerenciadorDeArquivo *this) {
    int i = 0;
    char* ptr = strstr(this->conteudoDoArquivo,"'eye_color': '");
    char* corDosOlhos = mallocString(corDosOlhos);
    ptr += 14;
    while (*ptr != '\'') {
        corDosOlhos[i++] = *ptr;
        ptr++;
    } corDosOlhos[i] = '\0';
        
    return corDosOlhos;
}

//-----------------------data-de-nascimento---------------------//

char* separarDataNascimento(GerenciadorDeArquivo *this) {
    int i = 0;
    char* ptr = strstr(this->conteudoDoArquivo,"'birth_year': '");
    char* dataNascimento = mallocString(dataNascimento);
    ptr += 15;
    while (*ptr != '\'') {
        dataNascimento[i++] = *ptr;
        ptr++;
    } dataNascimento[i] = '\0';
        
    return dataNascimento;
}

//------------------------separar-genero-----------------------//

char* separarGenero(GerenciadorDeArquivo *this) {
    int i = 0;
    char* ptr = strstr(this->conteudoDoArquivo,"'gender': '");
    char* gender = mallocString(gender);
    ptr += 11;
    while (*ptr != '\'') {
        gender[i++] = *ptr;
        ptr++;
    } gender[i] = '\0';
        
    return gender;
}

//----------------------separar-homeworld---------------------//

char* separarHomeworld(GerenciadorDeArquivo *this) {
    int i = 0;
    char* ptr = strstr(this->conteudoDoArquivo,"'homeworld': '");
    char* homeworld = mallocString(homeworld);
    ptr += 14;
    while (*ptr != '\'') {
        homeworld[i++] = *ptr;
        ptr++;
    } homeworld[i] = '\0';
        
    return homeworld;
    }

//--------------------------Parse------------------------------//

int parseInt(char *str) {
    int i = 0, result = 0, sign = 1;
    if (str[0] == '-') {
        sign = -1;
        i++;
    }
    for (; str[i] != '\0'; i++) {
        if (str[i] >= '0' && str[i] <= '9') {
            result = result * 10 + (str[i] - '0');
        } else {
            break;
        }
    }
    return sign * result;
}

//--------------------------Parse-------------------------------//

double parseDouble(char *str) {
    int i = 0, sign = 1;
    double result = 0, fraction = 1;
    if (str[0] == '-') {
        sign = -1;
        i++;
    }
    for (; str[i] != '\0'; i++) {
        if (str[i] == '.') {
            i++;
            break;
        }
        if (str[i] >= '0' && str[i] <= '9') {
            result = result * 10 + (str[i] - '0');
        } else {
            return 0;
        }
    }
    for (; str[i] != '\0'; i++) {
        if (str[i] >= '0' && str[i] <= '9') {
            fraction /= 10;
            result += (str[i] - '0') * fraction;
        } else {
            return 0;
        }
    }
    return sign * result;
}

//-----------------------------mallocstring----------------------------------//

char* mallocString(char *str) {str = (char*)malloc(sizeof(char) * 1000); return str; }

//----------------------------IsFim-------------------------------------//

bool isFim(const char *str) { return ( strlen(str) == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M'); }

//-----------------------------indexOf-----------------------------------//

int indexOf(char str[], int start, char target) {
    for(int i = start; i < strlen(str); i++) {
        if(str[i] == target) {
            return i;
        }
    }
    return -1;
}

//--------------------------substring------------------------------//

char* substring(char* string, int inicio, int fim) {
    int tamanho = fim - inicio;
    char* sub = malloc((tamanho + 1) * sizeof(char));
    
    for (int i = 0; i < tamanho; i++) {
        sub[i] = string[inicio + i];
    }
    
    sub[tamanho] = '\0';
    
    return sub;
}

//-------------------------------------------------------------------------//

int main( void ) {
    // Personagem *p = (Personagem*)malloc(sizeof(Personagem) * 100);
    GerenciadorDeArquivo *g = (GerenciadorDeArquivo*)malloc(sizeof(GerenciadorDeArquivo));
    Lista *listaPers = (Lista*)malloc(sizeof(Lista));
    
    listaPers->array = (Personagem*)malloc(sizeof(Personagem) * MAX);
    listaPers->length = 0;
    g->conteudoDoArquivo = mallocString(g->conteudoDoArquivo);
    
    char *caminho = mallocString(caminho);

    scanf("%s", caminho);

    while (!isFim(caminho))
    {
        Personagem pers;
        lerArquivo(caminho, g);

        pers.nome          =  separarNome(g);
        pers.altura        =  parseInt   ( separarAltura(g) );
        pers.peso          =  parseDouble(separarPeso(g)); 
        pers.corDoCabelo   =  separarCorDoCabelo(g);
        pers.corDaPele     =  separarCorDaPele(g);
        pers.corDosOlhos   =  separarCorDosOlhos(g);
        pers.anoNascimento =  separarDataNascimento(g);
        pers.genero        =  separarGenero(g);
        pers.homeworld     =  separarHomeworld(g);

        inserirFim(pers, listaPers);
        // numEntrada++;
        scanf("%s", caminho);
    }

//--------------------------------------------------------------------------//

    quickSort(10, 0, listaPers->length - 1, listaPers);
    printList(listaPers);

    free(listaPers->array);
    free(caminho);
    free(g);
    free(g->conteudoDoArquivo);
    free(listaPers);

    return 0;
}