//------------------------------------------------------------------------//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
//------------------------------------------------------------------------//
char* mallocString(char *str);
//------------------------------------------------------------------------//

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

typedef struct 
{
    FILE *file;
    char *conteudoDoArquivo;

} GerenciadorDeArquivo;

//--------------------------------------------------------------------------//
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

bool isFim(const char *str) { return( strlen(str) == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M'); }

//---------------------------------------------------------------------------//

int main(void) {
    Personagem *p = (Personagem*)malloc(sizeof(Personagem) * 100);
    GerenciadorDeArquivo *g = (GerenciadorDeArquivo*)malloc(sizeof(GerenciadorDeArquivo));
    g->conteudoDoArquivo = mallocString(g->conteudoDoArquivo);
    int numEntrada = 0;
    char *caminho = mallocString(caminho);

    scanf("%s", caminho);
    while (!isFim(caminho))
    {
        lerArquivo(caminho, g);
        
        p[numEntrada].nome          =  separarNome(g);
        p[numEntrada].altura        =  parseInt   ( separarAltura(g) );
        p[numEntrada].peso          =  parseDouble(separarPeso(g)); 
        p[numEntrada].corDoCabelo   =  separarCorDoCabelo(g);
        p[numEntrada].corDaPele     =  separarCorDaPele(g);
        p[numEntrada].corDosOlhos   =  separarCorDosOlhos(g);
        p[numEntrada].anoNascimento =  separarDataNascimento(g);
        p[numEntrada].genero        =  separarGenero(g);
        p[numEntrada].homeworld     =  separarHomeworld(g);
        
        
        printf(" ## %s ## %d ## %.0lf ## %s ## %s ## %s ## %s ## %s ## %s ## ", p[numEntrada].nome, p[numEntrada].altura, p[numEntrada].peso,
         p[numEntrada].corDoCabelo, p[numEntrada].corDaPele, p[numEntrada].corDosOlhos, p[numEntrada].anoNascimento,p[numEntrada].genero, p[numEntrada].homeworld);
        printf("\n");
       
        numEntrada++;
        scanf("%s", caminho);
    }

    free(caminho);
    free(p);
    free(g);

    return 0;
}