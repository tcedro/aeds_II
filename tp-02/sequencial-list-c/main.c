//------------------------------------------------------------------------//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
//------------------------Assinaturas------------------------------//

char* mallocString(char *str);

//--------------------------DEFINES-----------------------------------//

#define MAX 100

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

// void print(Personagem *p) {
//     printf(" ## %s ## %d ## %.1lf ## %s ## %s ## %s ## %s ## %s ## %s ## ", getNome(p), getAltura(p), getPeso(p),
//     getCorDoCabelo(p), getCorDaPele(p), getCorDosOlhos(p), getAnoNascimento(p), getGenero(p), getHomeworld(p));
//     printf("\n");
// }

typedef struct  {
    Personagem *array; 
    int length;

} Lista;

void inserirInicio(Personagem elemento, Lista *list) {
    if (list->length < MAX)  {
        for (int i = list->length; i > 0; i--) {
            list->array[i] = list->array[i-1];

        }
        list->array[0] = elemento;
        list->length++;
    }
} 

void inserirFim(Personagem elemento, Lista *list) {
    if (list->length < MAX)  {
        list->array[list->length++] = elemento;
    }
} 

void inserir(int pos, Personagem elemento, Lista *list) {
        if (pos >= 0 && pos <= MAX && list->length < MAX)  {
        for (int i = list->length; i > pos; i--) {
            list->array[i] = list->array[i-1];

        }
        list->array[pos] = elemento;
        list->length++;
    }
}

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

Personagem removerFim(Lista *list) {
    Personagem tmp;
    if (list->length != 0) {
        tmp = list->array[list->length - 1];
    }
    list->length--;
    return tmp;
}

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

void printList(Lista *list) {
    for (int i = 0; i < list->length; i++) {
        printf("[%d] ", i);
        printf(" ## %s ## %d ## %.0lf ## %s ## %s ## %s ## %s ## %s ## %s ## ", list->array[i].nome, list->array[i].altura, list->array[i].peso,
         list->array[i].corDoCabelo, list->array[i].corDaPele, list->array[i].corDosOlhos, list->array[i].anoNascimento,list->array[i].genero, list->array[i].homeworld);
        printf("\n");
    }
    
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

    int numOps = 0;
    char *func;

    scanf("%d", &numOps);

    for (int i = 0; i < numOps; i++) {
        char in[100];

        scanf(" %100[^\n]", in);
        // printf("%s\n", in);
        func = substring(in, 0, 2);
        
        if( strcmp(func, "II") == 0 ) {
            Personagem px;
            lerArquivo( substring(in, 3, strlen( in )) , g );
            
            px.nome          =  separarNome(g);
            px.altura        =  parseInt   ( separarAltura(g) );
            px.peso          =  parseDouble(separarPeso(g)); 
            px.corDoCabelo   =  separarCorDoCabelo(g);
            px.corDaPele     =  separarCorDaPele(g);
            px.corDosOlhos   =  separarCorDosOlhos(g);
            px.anoNascimento =  separarDataNascimento(g);
            px.genero        =  separarGenero(g);
            px.homeworld     =  separarHomeworld(g);


            
            inserirInicio(px, listaPers);
            // printList(listaPers);
        }

        else if( strcmp(func, "IF") == 0 ) {
            Personagem px;
            lerArquivo(substring(in, 3, strlen( in )), g);
            
            px.nome          =  separarNome(g);
            px.altura        =  parseInt   ( separarAltura(g) );
            px.peso          =  parseDouble(separarPeso(g)); 
            px.corDoCabelo   =  separarCorDoCabelo(g);
            px.corDaPele     =  separarCorDaPele(g);
            px.corDosOlhos   =  separarCorDosOlhos(g);
            px.anoNascimento =  separarDataNascimento(g);
            px.genero        =  separarGenero(g);
            px.homeworld     =  separarHomeworld(g);

            inserirFim(px, listaPers);
        }

        else if( strcmp(func, "I*" ) == 0 ) {
            Personagem px;
            
            int pos = parseInt( substring(in, 3, indexOf(in , 3, ' ')) );
           
            lerArquivo( substring(in, indexOf(in, 3, ' ') + 1, strlen(in)), g );

            px.nome          =  separarNome(g);
            px.altura        =  parseInt   ( separarAltura(g) );
            px.peso          =  parseDouble(separarPeso(g)); 
            px.corDoCabelo   =  separarCorDoCabelo(g);
            px.corDaPele     =  separarCorDaPele(g);
            px.corDosOlhos   =  separarCorDosOlhos(g);
            px.anoNascimento =  separarDataNascimento(g);
            px.genero        =  separarGenero(g);
            px.homeworld     =  separarHomeworld(g);

            inserir(pos, px, listaPers);

        }

        else if( strcmp(func, "RI") == 0) {
            printf("(R) %s\n", removerInicio(listaPers).nome); 
        }

        else if( strcmp(func, "RF") == 0) {
            printf("(R) %s\n", removerFim(listaPers).nome);
        } 

        else if( strcmp(func, "R*") == 0) {
            int pos = parseInt(  substring(in, 3, strlen(in)) );
            printf("(R) %s\n", remover(pos, listaPers).nome);
        }

    }
    
    printList(listaPers);
    free(listaPers->array);
    free(caminho);
    free(g);
    free(g->conteudoDoArquivo);
    free(listaPers);

    return 0;
}