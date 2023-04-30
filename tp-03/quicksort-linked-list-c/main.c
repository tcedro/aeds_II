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
    struct CellPers *back;

} CellPers;

//malloc
CellPers* initiate_Cell(Personagem *pers) {
    CellPers *cell = (CellPers*)malloc(sizeof(CellPers));
    
    cell->element = pers;
    cell->next = NULL;
    cell->back = NULL;
    
    return cell;
}

//---------------------------------Lista de Personagem dinamico--------------------------------------------//
typedef struct ListPers
{
    CellPers *first;
    CellPers *last;
    int length;

} ListPers;

//malloc
ListPers* initiateListPers() {
    ListPers *list = (ListPers*)malloc(sizeof(ListPers));
    CellPers *tmp = (CellPers*)malloc(sizeof(CellPers)); 
    
    list->first  = tmp;
    list->last   = list->first;
    list->length = 0;

    return list;
}

//inserir no inicio
void insertInit(Personagem *pers, ListPers *list) {
    CellPers *tmp = initiate_Cell(pers);
    
    tmp->back = list->first;
    tmp->next = list->first->next;
    list->first->next = tmp;
    
    if (list->first == list->last) {
        tmp->next->back = tmp;
    }


    free(tmp);
}

//inserir no fim
void insertEnd(Personagem *pers, ListPers *list) {
    list->last->next = initiate_Cell(pers);
    list->last->next->back = list->last;
    list->last = list->last->next;

    list->length++;
}

//inserir
void insert() {

}

//remover inicio
Personagem* removeInit(ListPers *list) {
    if(list->first == list->last) {
        printf("\nError.\n");
    }
    
    CellPers *tmp = list->first;
    list->first = list->first->next;
    
    Personagem *element = list->first->element;
    tmp->next = list->first->back = NULL;
    free(tmp);

    return element;

}

//retonar posicao do elemento
CellPers* position(int pos, ListPers *list) {
    CellPers *x = NULL; int i = 0;
    for ( i = 0, x = list->first; i <= pos; i++, x = x->next);
    return x;
}

//trocar elementos da celula
void swap(int left, int right, ListPers *list) {
    CellPers *el1 = position(left, list); CellPers *el2 = position(right, list);

    Personagem* aux = el1->element;
    el1->element = el2->element;
    el2->element = aux;

}

//quicksort
void quicksort(int left, int right, ListPers *list) {
    int i = left, j = right; 
    Personagem *pivo = position( ((left + right) / 2), list )->element;
    while(i <= j) {
        
        while( compare( position(i, list)->element, pivo ) < 0) {i++;}
        while( compare( position(j, list)->element, pivo ) > 0) {j--;}
        if(i <= j) {
            swap(i, j, list);
            i++; j--;
        }
    }
    
    if(left < j)  quicksort(left, j, list);
    if(i < right) quicksort(i, right, list);
}

//sort
void sort(ListPers *list) {
    quicksort(0, (list->length - 1), list);
}

void showList(ListPers *list) {
    CellPers *i = list->first->next;
    int j = 0;
    while (i != NULL) {
        printf(" ## %s ## %d ## %.0lf ## %s ## %s ## %s ## %s ## %s ## %s ## ", i->element->nome, i->element->altura, i->element->peso,
         i->element->corDoCabelo, i->element->corDaPele, i->element->corDosOlhos, i->element->anoNascimento,i->element->genero, i->element->homeworld);
        printf("\n");
        
        i = i->next;
    }
    
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

//---------------------------------------------------------------------------//

int main(void) {
    ListPers *list            = initiateListPers();
    GerenteArquivo *gerente   = initiateGerenciador();
    
    char *caminho = mallocString(caminho);
    scanf("%s", caminho);
    
    while (!isFim(caminho))
    {
        Personagem *pers = initiatePersonagem();
        
        lerArquivo(caminho, gerente);
        
        setAtributes(pers, gerente);
        insertEnd(pers, list);
        
        scanf("%s", caminho);
    }
    
    sort(list);
    showList(list);

    free(caminho);
    free(gerente);
    free(list);

    return 0;
}