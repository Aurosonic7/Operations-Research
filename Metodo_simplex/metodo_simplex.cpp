#include<iostream>
using namespace std;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void AsignacionDeValores(double **q, int FilasTotales, int ColumnasTotales, int NumeroDeRestricciones, int NumeroDeVariables){ //! Matrices y funciones Forma 4
    int i,j;
    //Vuelvo toda la matriz a 0
    for(i=0;i<FilasTotales;i++)
        for(j=0;j<ColumnasTotales;j++)
            q[i][j]=0;
    /*ImprimirTablaSimplex(&q[0][0],FilasTotales,ColumnasTotales);*/ //Visualizacion de la matriz con 0
    cout<<"#####Digite los coeficientes de la funcion objetivo#####"<<endl; //* Ingresa los valores de la funcion objetivo
    for(i=0;i<NumeroDeVariables;i++){
        cout<<"Digite X"<<i+1<<" de la funcion objetivo: "; cin>>q[FilasTotales-1][i];
        q[FilasTotales-1][i]=q[FilasTotales-1][i]*(-1); //* Vuelvo negativo los valores de la funcion objetivo
    }
    cout<<"#####Digite los coeficientes de restricciones#####"<<endl; //* Asignacion de los coeficientes de las restricciones
    for(i=0;i<NumeroDeRestricciones;i++){
        cout<<"-----Restriccion numero "<<i+1<<"-----"<<endl;
        for(j=0;j<NumeroDeVariables;j++){
            cout<<"Digite el valor de X"<<j+1<<": "; cin>>q[i][j];
        }
        cout<<"Digite el valor de Sol"<<i+1<<": "; cin>>q[i][ColumnasTotales-1];
    }
    j=NumeroDeVariables; //* Asignacion de iteraciones a realizar
    for(i=0;i<FilasTotales-1;i++) //* Asignacion de 1 en diagonal
            q[i][j++]=1;
    /*ImprimirTablaSimplex(&q[0][0],FilasTotales,ColumnasTotales);*/ //Visualizacion de la matriz con los valores ingresados
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void ImprimirTablaSimplex(double *TablaSimplex, int FilasTotales, int ColumnasTotales){ //! Matrices y funciones Forma 1
    int i,j;
    printf("\n");
    for(i=0;i<FilasTotales;i++){
        for(j=0;j<ColumnasTotales;j++)
            printf("%.2lf\t",*(TablaSimplex+i*ColumnasTotales+j));
        printf("\n");
    }
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void BuscarColumnaFilaYElementoPivote(double **q, int FilasTotales, int ColumnasTotales, int NumeroDeRestricciones, int NumeroDeVariables, int *FilaPivoteAux, int *ColumnaPivoteAux, double *ElementoPivoteAux){
    printf("----------Fila, Columna y Elemento pivote--------------------\n");
    int x=0,y=0;
    double FilaPivote,ColumnaPivote,ElementoPivote;
    FilaPivote=q[FilasTotales-1][0];
    for(int i=0;i<ColumnasTotales;i++) //* Buscar el numero menor en la fila de la funcion objetivo
        if(FilaPivote>q[FilasTotales-1][i]){
            FilaPivote=q[FilasTotales-1][i];
            x=i; //* Posicion de la columna
        }
    printf("Columna pivote: %.2lf, posicion de la columna: %d\n",FilaPivote,x);
    ColumnaPivote=q[0][ColumnasTotales-1]/q[0][x];
    if(ColumnaPivote<0) ColumnaPivote=1; //? Bug corregido!!!!
    for(int j=0;j<NumeroDeRestricciones;j++) //* Buscar el numero menor de la columna pivote
        if(ColumnaPivote>q[j][ColumnasTotales-1]/q[j][x] && q[j][ColumnasTotales-1]/q[j][x]>0){
            ColumnaPivote=q[j][ColumnasTotales-1]/q[j][x];
            y=j; //* Posicion de la fila
        }
    printf("Fila pivote: %.2lf, posicion de la fila: %d\n",ColumnaPivote,y);
    printf("Elemento pivote: %.2lf en la posicion [%d,%d]\n",q[y][x],y,x);
    *ColumnaPivoteAux=x; //* Retorno de variable
    *FilaPivoteAux=y; //* Retorno de variable
    *ElementoPivoteAux=q[y][x]; //* Retorno de variable
    printf("--------------------------------------------------------------\n");
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void DividirFilaPivoteYDividirLasFilas(double **q,int FilasTotales,int ColumnasTotales,int *FilaPivoteAux,int *ColumnaPivoteAux,double *ElementoPivoteAux, int *i){
    int FilaAux=*FilaPivoteAux, ColumnaAux=*ColumnaPivoteAux;
    double ElementoPivote=*ElementoPivoteAux;
    double Valor; //* Variable auxiliar para los valores de la columna pivote
    printf("El viejo elemento pivote es: %.2lf en la posicion [%d,%d]\n",ElementoPivote,*FilaPivoteAux,*ColumnaPivoteAux);
    printf("-----Dividir la Fila pivote entre el elemento pivote-----");
    for(int i=0;i<ColumnasTotales;i++) //* Divido toda la fila entre el elemento pivote
        q[FilaAux][i]=q[FilaAux][i]/ElementoPivote;
    ElementoPivote=q[*FilaPivoteAux][*ColumnaPivoteAux];
    printf("\nEl nuevo elemento pivote es: %.2lf en la posicion [%d,%d]",ElementoPivote,*FilaPivoteAux,*ColumnaPivoteAux);
    ImprimirTablaSimplex(&q[0][0],FilasTotales,ColumnasTotales);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //! IMPORTANTE OPERACIONES
    printf("####################ITERACION NUMERO: %d####################",*i);
    for(int Fila=0;Fila<FilasTotales;Fila++){
        if(FilaAux!=Fila){
            Valor=q[Fila][*ColumnaPivoteAux];
            Valor*=-1;
            printf("\nAgarre el valor %.2lf de la fila: %d\n",Valor,Fila);
            for(int Columna=0;Columna<ColumnasTotales;Columna++) //! Fila pivote
                printf("%.2lf\t",q[FilaAux][Columna]);
            printf("\n");
            for(int Columna=0;Columna<ColumnasTotales;Columna++) //! Fila a modificar
                printf("%.2lf\t",q[Fila][Columna]);
            printf("\n");
            for(int Columna=0;Columna<ColumnasTotales;Columna++){ //! Fila modificada
                q[Fila][Columna]+=(q[FilaAux][Columna]*Valor);
                printf("%.2lf\t",q[Fila][Columna]);
            } 
        }
        printf("\n");
    }
    ImprimirTablaSimplex(&q[0][0],FilasTotales,ColumnasTotales);
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void BuscarValorNegativo(double *Auxiliar, int FilasTotales, int ColumnasTotales, double **q){
    //printf("Ultima fila:\n");
    int bandera=0;
    for(int i=0;i<ColumnasTotales-1;i++){
            //printf("%.2lf\t",q[FilasTotales-1][i]);
            if(q[FilasTotales-1][i]<0){
                //printf("%.2lf\t",q[FilasTotales-1][i]);
                bandera--;
            }else{
                bandera++;
                //printf("%.2lf\t",q[FilasTotales-1][i]);
            }
    }
    //printf("Bandera es: %d Columnas totales menos uno es es: %d",bandera,ColumnasTotales-1);
    *Auxiliar=bandera;
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void AsignacionFilaColumnaNombreVariables(int FilasTotales, int ColumnasTotales, int NumeroDeRestricciones, int NumeroDeVariables, string VariablesDeColumnas[], string VariablesDeFilas[]){
    printf("Filas totales: %d Columnas totales: %d\n",FilasTotales,ColumnasTotales);
    //* Filas con nombre de las variables
    for(int i=0;i<ColumnasTotales-1;i++)
        if(i<NumeroDeVariables) VariablesDeColumnas[i]="X";
        else VariablesDeColumnas[i]="S";
    int j=0;
    for(int i=0;i<ColumnasTotales-1;i++)
        if(i<NumeroDeVariables) VariablesDeColumnas[i]+=i + 49;
        else VariablesDeColumnas[i]+= j++ + 49;
    //* Impresion de columnas totales
    printf("Columnas totales\n");
    for(int i=0;i<ColumnasTotales;i++) printf("%s ",VariablesDeColumnas[i].c_str());
    //* Columnas con nombre de las variables
    for(int i=0;i<NumeroDeRestricciones;i++) VariablesDeFilas[i]="S";
    for(int i=0;i<NumeroDeRestricciones;i++) VariablesDeFilas[i]+=i+49;
    //* Impresion de filas totales
    printf("\nFilas totales\n");
    for(int i=0;i<NumeroDeRestricciones;i++) printf("%s ",VariablesDeFilas[i].c_str());
    printf("\n");
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
int main(){
    int NumeroDeRestricciones/*Filas*/,NumeroDeVariables/*Columnas*/;
    int FilaPivoteAux,ColumnaPivoteAux; //* Variables auxiliares que me ayudaran a retornar dos variables de la funcion Buscar
    double ElementoPivoteAux;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    cout<<"Digita el numero de restricciones: "; cin>>NumeroDeRestricciones; //* Filas 
    cout<<"Digita el numero de variables: "; cin>>NumeroDeVariables; //* Columnas
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    double Auxiliar=0;
    int FilasTotales=NumeroDeRestricciones+1; //* Filas Totales
    int ColumnasTotales=NumeroDeRestricciones+NumeroDeVariables+1; //* Columnas Totales(Variables, Restricciones y Solución)
    double TablaSimplex[FilasTotales][ColumnasTotales]; //* Tabla Simplex (Matriz)
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    double *p[FilasTotales]; //* Asignacion de direccion al primer elemento del vector (Forma 4 Matrices y Funciones)
    for(int i=0;i<FilasTotales;i++) p[i]=&TablaSimplex[i][0];
    double **q=p;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    string VariablesDeColumnas[ColumnasTotales],VariablesDeFilas[FilasTotales];
    AsignacionDeValores(q,FilasTotales,ColumnasTotales,NumeroDeRestricciones,NumeroDeVariables);
    //(NumeroDeVariables>4)?Auxiliar++:printf("");
    //for(int i=0;i<NumeroDeVariables;i++){//Numero de variables aun
    int i=0;
    while(Auxiliar!=ColumnasTotales-1){
        printf("\n\t\t@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Tabla Simplex@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        ImprimirTablaSimplex(&TablaSimplex[0][0],FilasTotales,ColumnasTotales); //* Visualizacion de la Tabla Simplex
        BuscarColumnaFilaYElementoPivote(q,FilasTotales,ColumnasTotales,NumeroDeRestricciones,NumeroDeVariables,&FilaPivoteAux,&ColumnaPivoteAux,&ElementoPivoteAux);
        DividirFilaPivoteYDividirLasFilas(q,FilasTotales,ColumnasTotales,&FilaPivoteAux,&ColumnaPivoteAux,&ElementoPivoteAux,&i);
        BuscarValorNegativo(&Auxiliar,FilasTotales,ColumnasTotales,q);
        i++;
        if(i==10){Auxiliar=ColumnasTotales-1;} //* Forzamiento de salida mediante 10 iteraciones....
    }
    return 0;
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
    * Christian (Aurosonic), Investigación de Operaciones, Metodo Simplex, V0.8.8 (Beta),
    * Universidad La Salle Oaxaca, Ingenieria en Software y Sistemas Computacionales,
    * 4º Semestre, Grupo A, Ciclo Escolar 2023-A,
    * Dr. Carlos Miguel López Martinez
*/
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////