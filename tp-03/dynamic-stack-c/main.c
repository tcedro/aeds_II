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
Personagem* init_personagem() {
    Personagem *pers = (Personagem*)malloc(sizeof(Personagem));
    return pers;
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


//------------------------------------CellPers-------------------------------------//

typedef struct CellPers
{
    Personagem *element;
    struct CellPers *next;

} CellPers;

//initiate new cell
CellPers* init_cell(Personagem *pers) {
    CellPers *new_cell = (CellPers*)malloc(sizeof(CellPers));
    
    new_cell->element = pers;
    new_cell->next == NULL;
    
    return new_cell;
}

//--------------------------------Stack-dynamic----------------------------------------//
typedef struct 
{
    CellPers *top;
    int length;

} StackPers;

//initiate new stack
StackPers* init_stack() {
    StackPers *new_stack = (StackPers*)malloc(sizeof(StackPers));
    
    new_stack->top == NULL;
    new_stack->length = 0;

    return new_stack;
}

//push cell in stack
void push(Personagem *pers, StackPers *stack) {
    CellPers *new_cell = init_cell(pers);

    if (stack->top == NULL) stack->top = new_cell;
    else {
        new_cell->next = stack->top;
        stack->top = new_cell;
    }
    
    stack->length++;
}

//pop Cell in stack returning Personagem
Personagem* pop(StackPers *stack) {
    if (stack->top == NULL) { printf("\nERROR:stack is empty.\n"); return NULL; }
    
    Personagem *removed = stack->top->element;
    CellPers *tmp = stack->top;
    
    stack->top = stack->top->next;
    stack->length--;
    
    free(tmp);

    return removed;    
} 

//print elements 
void printStack(StackPers *stack) {
    for (CellPers *i = stack->top; i != NULL; i = i->next) {
        printf(" ## %s ## %d ## %.0lf ## %s ## %s ## %s ## %s ## %s ## %s ## ", i->element->nome, i->element->altura, i->element->peso,
                i->element->corDoCabelo, i->element->corDaPele, i->element->corDosOlhos, i->element->anoNascimento,i->element->genero, i->element->homeworld);
        printf("\n");
    }
}

//print elements recursive
void r_printStack(int j, CellPers *i, StackPers *stack) {
    if (i->next != NULL) r_printStack(j - 1, i->next, stack);
    printf("[%d]  ## %s ## %d ## %.0lf ## %s ## %s ## %s ## %s ## %s ## %s ## ", j, i->element->nome, i->element->altura, i->element->peso,
                i->element->corDoCabelo, i->element->corDaPele, i->element->corDosOlhos, i->element->anoNascimento,i->element->genero, i->element->homeworld);
        printf("\n");  
}


//-----------------------------------------------------------------------------------//

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

//---------------------------------------------------------------------------//

int main(void) {
    StackPers *stack = init_stack();
    GerenciadorDeArquivo *gerenciador = (GerenciadorDeArquivo*)malloc(sizeof(GerenciadorDeArquivo));
    
    gerenciador->conteudoDoArquivo = mallocString(gerenciador->conteudoDoArquivo);
    
    char *caminho = mallocString(caminho);

    scanf("%s", caminho);
    
    while (!isFim(caminho))
    {
        lerArquivo(caminho, gerenciador);
        
        Personagem *pers = init_personagem();
        
        pers->nome          =  separarNome(gerenciador);
        pers->altura        =  parseInt   ( separarAltura(gerenciador) );
        pers->peso          =  parseDouble( separarPeso(gerenciador)   ); 
        pers->corDoCabelo   =  separarCorDoCabelo(gerenciador);
        pers->corDaPele     =  separarCorDaPele(gerenciador);
        pers->corDosOlhos   =  separarCorDosOlhos(gerenciador);
        pers->anoNascimento =  separarDataNascimento(gerenciador);
        pers->genero        =  separarGenero(gerenciador);
        pers->homeworld     =  separarHomeworld(gerenciador);
        
        push(pers, stack);
        
        scanf("%s", caminho);
    }
    
    //------------------------second---------------------------//

    int numOps = 0;
    char *func;

    scanf("%d", &numOps);

    for (int i = 0; i < numOps; i++) {
        char in[100];

        scanf(" %100[^\n]", in);
        func = substring(in, 0, 1);
        
        if( strcmp(func, "I") == 0 ) {
            Personagem *px = init_personagem();
            lerArquivo( substring(in, 2, strlen( in )) , gerenciador );
            
            px->nome          =  separarNome(gerenciador);
            px->altura        =  parseInt   ( separarAltura(gerenciador) );
            px->peso          =  parseDouble(separarPeso(gerenciador)); 
            px->corDoCabelo   =  separarCorDoCabelo(gerenciador);
            px->corDaPele     =  separarCorDaPele(gerenciador);
            px->corDosOlhos   =  separarCorDosOlhos(gerenciador);
            px->anoNascimento =  separarDataNascimento(gerenciador);
            px->genero        =  separarGenero(gerenciador);
            px->homeworld     =  separarHomeworld(gerenciador);

            push(px, stack);

        }

        else  { printf("(R) %s\n", pop(stack)->nome ); }
    
    }

    r_printStack(stack->length - 1, stack->top, stack);

    free(stack);
    free(caminho);
    free(gerenciador);

    return 0;
}