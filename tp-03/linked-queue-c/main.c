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

//initiate personagem
Personagem* initiatePersonagem() {
    Personagem *pers = (Personagem*)malloc(sizeof(Personagem));
    return pers;
}

//compare
int compare(Personagem *p1, Personagem *p2) {
    int resp = -1;
    resp = strcmp(p1->corDoCabelo, p2->corDoCabelo);
    
    if(resp == 0) {
        resp = strcmp( p1->nome , p2->nome);
        return resp;
    }
    
    return resp;
}

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

//-------------------------------------Celula de Personagem----------------------------------------//
typedef struct CellPers
{
    Personagem *element;
    struct CellPers *next;

} CellPers;

//malloc
CellPers* initiate_Cell(Personagem *pers) {
    CellPers *cell = (CellPers*)malloc(sizeof(CellPers));
    
    cell->element = pers;
    cell->next = NULL;
    
    return cell;
}

CellPers* initiateCell_Empty() {
    CellPers *cell = (CellPers*)malloc(sizeof(CellPers));
    
    cell->element = NULL;
    cell->next = NULL;
    
    return cell;
}

//---------------------------------Lista de Personagem dinamico--------------------------------------------//

typedef struct  
{
    CellPers *first, *last;
    int capacity;
    int length;

} LinkedQueue;

LinkedQueue* initiateQueue(int capacity, LinkedQueue *this) {
    this = (LinkedQueue*)malloc(sizeof(LinkedQueue));
    this->first = this->last = NULL;
    this->length = 0;
    this->capacity = capacity;

    return this;
}

int length(LinkedQueue *this) {
    return this->length;
}


Personagem* removePers(LinkedQueue *this) {
        Personagem *pers = NULL;
        if(this->length > 0) {
            pers = this->first->element;

            if(this->first == this->last) {
                this->first = this->last = NULL;
            }else {
                this->first = this->first->next;
                this->last->next = this->first;
            }

            this->length--;
            
            return pers;
        }

        return pers;
    }

void insert(Personagem *pers, LinkedQueue *this) {
        if(this->length == this->capacity) removePers(this); 

        CellPers *tmp = initiate_Cell(pers);

        if(this->first == NULL) {
            this->first = tmp;
        }else {
            this->last->next = tmp;
        }
        
        this->last = tmp;
        this->last->next = this->first;
        // free(tmp);

        this->length++;
}

void showList(LinkedQueue *list) {
    CellPers *i = list->first->next;
    int j = 0;
    while (i != NULL) {
        printf(" ## %s ## %d ## %.0lf ## %s ## %s ## %s ## %s ## %s ## %s ## ", i->element->nome, i->element->altura, i->element->peso,
         i->element->corDoCabelo, i->element->corDaPele, i->element->corDosOlhos, i->element->anoNascimento,i->element->genero, i->element->homeworld);
        printf("\n");
        
        i = i->next;
    }
}


int getMedia(LinkedQueue *this) {
    float media = 0;
        
    if(this->length > 0) {
        media += this->first->element->altura;
        CellPers *aux = this->first->next;

        if(this->first != this->last) {
             while(aux != this->first) {
                media += aux->element->altura;
                aux = aux->next;
            }
        }

        media /= this->length;
    }
    return media;
}
    

//-------------------------------------------------------------------------------------//

typedef struct 
{
    FILE *file;
    char *conteudoDoArquivo;

} GerenteArquivo;

//initiate gerente
GerenteArquivo* initiateGerenciador() {
    GerenteArquivo *ge = (GerenteArquivo*)malloc(sizeof(GerenteArquivo));
    ge->conteudoDoArquivo = mallocString(ge->conteudoDoArquivo);
    return ge;
}
//--------------------------------------------------------------------------//

void lerArquivo(const char *caminho, GerenteArquivo *g) {
    g->file = fopen(caminho, "r");
    fgets(g->conteudoDoArquivo, 1000, g->file);
    fclose(g->file);
}

//------------------------------TratarString---------------------------------//

//---------------------------Separar-valor-do-Nome------------------------//
    
    char* separarNome(GerenteArquivo *this) {
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

    char* separarAltura(GerenteArquivo *this) {
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

    char* separarPeso(GerenteArquivo *this) {
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

    char* separarCorDoCabelo(GerenteArquivo *this) {
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

    char* separarCorDaPele(GerenteArquivo *this) {
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

    char* separarCorDosOlhos(GerenteArquivo *this) {
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

    char* separarDataNascimento(GerenteArquivo *this) {
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

    char* separarGenero(GerenteArquivo *this) {
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

    char* separarHomeworld(GerenteArquivo *this) {
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


void setAtributes(Personagem *pers, GerenteArquivo *gerente) {
    pers->nome          =  separarNome(gerente);
    pers->altura        =  parseInt   ( separarAltura(gerente) );
    pers->peso          =  parseDouble(separarPeso(gerente)); 
    pers->corDoCabelo   =  separarCorDoCabelo(gerente);
    pers->corDaPele     =  separarCorDaPele(gerente);
    pers->corDosOlhos   =  separarCorDosOlhos(gerente);
    pers->anoNascimento =  separarDataNascimento(gerente);
    pers->genero        =  separarGenero(gerente);
    pers->homeworld     =  separarHomeworld(gerente);
}


//---------------------------------mallocstring--------------------------------------//

char* mallocString(char *str) {str = (char*)malloc(sizeof(char) * 1000); return str; }

//---------------------------------------------------------------------------//

bool isFim(const char *str) { return( strlen(str) == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M'); }

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

//-------------------------------------------------------------------------------------
int main(void) {
    LinkedQueue *queue = initiateQueue(5, queue);
    GerenteArquivo *gerente   = initiateGerenciador();
    
    char *caminho = mallocString(caminho);
    scanf("%s", caminho);
    
    while (!isFim(caminho))
    {
        Personagem *pers = initiatePersonagem();
        
        lerArquivo(caminho, gerente);
        
        setAtributes(pers, gerente);
        insert(pers, queue);
        
        printf("%d\n", getMedia(queue) ); 
        
        scanf("%s", caminho);
    }

    int numOps = 0;
    char *func;

    scanf("%d", &numOps);

    for (int i = 0; i < numOps; i++) {
        char in[100];

        scanf(" %100[^\n]", in);
        func = substring(in, 0, 1);
        
        if( strcmp(func, "I") == 0 ) {
            Personagem *px = initiatePersonagem();
            lerArquivo( substring(in, 2, strlen( in )) , gerente );
            

            setAtributes(px, gerente);
            insert(px, queue);

            printf("%d\n", getMedia(queue));

        }

        else  { printf("(R) %s\n", removePers(queue)->nome ); }
    
    }

    free(caminho);
    free(gerente);
    free(queue);

    return 0;
}