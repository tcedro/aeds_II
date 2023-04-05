//------------------------------------------------------------------------//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>
//------------------------Assinaturas------------------------------//

char* mallocString(char *str);

//--------------------------DEFINES-----------------------------------//

#define MAX 100
#define MAX_SIZE 5

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

//-------------------------------setters-----------------------------------------//

void setNome(char *nome, Personagem *p)                   { p->nome = nome; }
void setAltura(int altura, Personagem *p)                 { p->altura = altura; }
void setPeso(double peso, Personagem *p)                  { p->peso = peso; }
void setCorDaPele(char *corDaPele, Personagem *p)         { p->corDaPele = corDaPele;     }
void setCorDosOlhos(char *corDosOlhos, Personagem *p)     { p->corDosOlhos = corDosOlhos; }
void setCorDoCabelo(char *corDoCabelo, Personagem *p)     { p->corDoCabelo = corDoCabelo; }
void setAnoNascimento(char *anoNascimento, Personagem *p) { p->anoNascimento = anoNascimento; }
void setGenero(char *genero, Personagem *p)               { p->genero = genero; }
void setHomeworld(char *nome, Personagem *p)              { p->homeworld = nome; }

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

//-----------------------------------------------------------------------------------//


typedef struct {
    Personagem *array;
    int inicio;
    int fim;
    int tamanho;
} FilaCircular;

// Inicializa a fila vazia
void inicializaFilaCircular(FilaCircular *fila) {
    fila->array = (Personagem*)malloc(sizeof(Personagem) * MAX_SIZE);
    fila->inicio = 0;
    fila->fim = 0;
    fila->tamanho = 0;
}

// Verifica se a fila está vazia
int filaCircularVazia(FilaCircular *fila) {
    return fila->tamanho == 0;
}

// Verifica se a fila está cheia
int filaCircularCheia(FilaCircular *fila) {
    return fila->tamanho == MAX_SIZE;
}

// Remove o personagem na frente da fila
Personagem removeFilaCircular(FilaCircular *fila) {
    Personagem personagem;
    if (fila->tamanho > 0) {
        // Remove o personagem do início da fila circular
        personagem = fila->array[fila->inicio];
        // Avança o início da fila circular
        fila->inicio = (fila->inicio + 1) % (MAX_SIZE);
        // Diminui o tamanho da fila circular
        fila->tamanho--;
    } else {
        printf("Erro: a fila circular está vazia\n");
    }
    return personagem;
}

// Insere um personagem na fila
void insereFilaCircular(FilaCircular *fila, Personagem personagem) {
    if (fila->tamanho == MAX_SIZE) {
        // printf("(R) %s\n", removeFilaCircular(fila).nome);
        removeFilaCircular(fila);
    }
    // Insere o personagem no fim da fila circular
    fila->array[fila->fim] = personagem;
    // Avança o fim da fila circular
    fila->fim = (fila->fim + 1) % (MAX_SIZE);
    // Aumenta o tamanho da fila circular
    fila->tamanho++;
}

// Calcula a média do nível de todos os personagens na fila circular
int mediaAltura(FilaCircular *fila) {
    double soma = 0;
    int i;
    for (i = 0; i < fila->tamanho; i++) {
        // Índice do personagem na fila circular
        int indice = (fila->inicio + i) % MAX_SIZE;
        // Soma o nível do personagem à soma total
        soma += fila->array[indice].altura;
    }
    // Divide a soma pelo tamanho da fila circular para obter a média
    double float_part = (soma / fila->tamanho) - (int)(soma / fila->tamanho);

    return float_part >= .5 ? ceil(soma / fila->tamanho) : floor(soma / fila->tamanho); 
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

//------------------------------TratarString---------------------------------//

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
//------------------------------------------------------------------//

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

//------------------------------------------------------------------//

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

//---------------------------------mallocstring--------------------------------------//

char* mallocString(char *str) {str = (char*)malloc(sizeof(char) * 1000); return str; }

//---------------------------------------------------------------------------//

bool isFim(const char *str) { return ( strlen(str) == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M'); }

//-------------------------indexOf-----------------------------------//

int indexOf(char str[], int start, char target) {
    for(int i = start; i < strlen(str); i++) {
        if(str[i] == target) {
            return i;
        }
    }
    return -1;
}


//------------------------------substring------------------------------//

char* substring(char* string, int inicio, int fim) {
    int tamanho = fim - inicio;
    char* sub = malloc((tamanho + 1) * sizeof(char));
    
    for (int i = 0; i < tamanho; i++) {
        sub[i] = string[inicio + i];
    }
    
    sub[tamanho] = '\0';
    
    return sub;
}

//-----------------------------list---------------------------------//

void printList(FilaCircular *queue) {
    printf("%d - %d\n", queue->inicio, queue->fim);
    
    for (int i = 0; i < queue->tamanho; i++) {
        printf("[%d] ", i);
        printf(" ## %s ## %d ## %.0lf ## %s ## %s ## %s ## %s ## %s ## %s ## ", queue->array[i].nome, queue->array[i].altura, queue->array[i].peso,
         queue->array[i].corDoCabelo, queue->array[i].corDaPele, queue->array[i].corDosOlhos, queue->array[i].anoNascimento,queue->array[i].genero, queue->array[i].homeworld);
        printf("\n");
    }
    
}

//-------------------------------------------------------------------------//

int main( void ) {
    GerenciadorDeArquivo *g = (GerenciadorDeArquivo*)malloc(sizeof(GerenciadorDeArquivo));
    
    FilaCircular *fila = (FilaCircular*)(malloc(sizeof(FilaCircular)));
    inicializaFilaCircular(fila);
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

        insereFilaCircular(fila, pers); 

        
        printf("%d\n", mediaAltura(fila));
        scanf("%s", caminho);
    }


//--------------------------------------------------------------------------//

    int numOps = 0;
    char *func;

    scanf("%d", &numOps);

    for (int i = 0; i < numOps; i++) {
        char in[100];
        scanf(" %100[^\n]", in);
        func = substring(in, 0, 1);
        if( strcmp(func, "I" ) == 0 ) {
            Personagem px;
            lerArquivo( substring(in, indexOf(in, 1, ' ') + 1, strlen(in)), g );

            px.nome          =  separarNome(g);
            px.altura        =  parseInt   ( separarAltura(g) );
            px.peso          =  parseDouble(separarPeso(g)); 
            px.corDoCabelo   =  separarCorDoCabelo(g);
            px.corDaPele     =  separarCorDaPele(g);
            px.corDosOlhos   =  separarCorDosOlhos(g);
            px.anoNascimento =  separarDataNascimento(g);
            px.genero        =  separarGenero(g);
            px.homeworld     =  separarHomeworld(g);

            
            insereFilaCircular(fila, px);
      
            printf("%d\n", mediaAltura(fila));
        }

        else if( strcmp(func, "R") == 0) {
            printf("(R) %s\n", removeFilaCircular(fila).nome );
        }

    }
    
    printList(fila);
    free(fila->array);
    free(caminho);
    free(g);
    free(g->conteudoDoArquivo);
    free(fila);

    return 0;
}
