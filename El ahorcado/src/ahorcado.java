import java.util.Random;
import java.util.Scanner;

public class ahorcado {
	public static void main(String[] args) {
		
		String palabras[]= {"pelota", "perro", "ordenador", "escalada", "matematicas", "programacion", "mecanica", "esternocleidomastoideo", "barcelona", "cuerda", "mosqueton",
								"arista", "desnivel", "rimaya", "glaciar", "everest", "mochila", "vivac", "saco", "hornillo"};
		char letras_acertadas[]= new char[30];
		char letras_falladas[]= new char [6];
		String palabra_adivinar;
		char letra_actual='0';
		int num_letras_palabra;
		int valor_random;
		int contador1;
		int contador2;
		int contador_letras=1;
		int contador_fallos=6;
		int contador_falladas=0;
		int contador_aciertos=0;
		boolean confirmada=false;
		boolean acertada= false;
		boolean fallada= false;
		boolean repetida= false;
		boolean ya_guardada= false;
		boolean ya_fallada= false;
		Scanner teclado= new Scanner(System.in);
		Random random = new Random();

		System.out.println("Aquest programa recrea el joc de l'ahorcado. El programa pensarà una paraula, i s'ha d'encertar introduint lletres.");
		valor_random= (int)(random.nextFloat()*20+0);							//Piensa un numero entre el 0 y el numero de palabras opcionales del array.
		palabra_adivinar= palabras[valor_random];								//Selecciona del array la palabra que estaba en el lugar del numero pensado.
		num_letras_palabra=palabra_adivinar.length();							//Calcula el numero de letras que tiene la palabra escogida.
		
		while((contador_fallos>0)&&(contador_aciertos<num_letras_palabra)){			//Mientras queden vidas y no se haya acertado la palabra, se sigue jugando.
				
				acertada=false;
				repetida=false;
				ya_guardada=false;
				ya_fallada=false;
				contador_aciertos=0;
			
				System.out.println("Introdueixi una lletra 'en minuscules':");
				letra_actual=teclado.next().charAt(0);
				teclado.nextLine();
				
			for (contador1=0;contador1<palabra_adivinar.length();contador1++){		//Hace tantas vueltas como letras tiene la palabra a adivinar.
				if (palabra_adivinar.charAt(contador1)==letra_actual){				//Si la letra entrada, coincide con la letra actual de la palabra:		
					for(contador2=0; contador2<contador_letras;contador2++){		//Bucle para recorrer el array de letras correctas ya guardadas.
						if (letras_acertadas[contador2]==letra_actual){				//Comparo cada letra del array con la letra entrada.
							ya_guardada=true;										//Si la letra ya esta guardada, asigno true a la variable.
						}
					}
					if(ya_guardada==false){											//Si la variable esta en false, quiere decir que la letra entrada no se encuentra en el array.
						letras_acertadas[contador_letras]=letra_actual;				//La guardo en el array.
						contador_letras++;											//Sumo uno al contador, para que la siguiente letra correcta se guarde en la siguiente posicion del array.
						System.out.println("Letra correcta!");
						acertada=true;												//Pongo la variable a true, para saber que la letra entrada, es correcta y no ha sido ya entrada.
					}else{
						repetida=true;												//Si la variable 'ya_guardada' tiene valor true, asigo true a la variable 'repetida', para-
					}																//saber que ya estaba en el array.		
				}	
			}
			
			if (acertada==false && repetida==false){										//Si la letra entrada, no se a guardado en el array, ni tampoco se habia entrado anteriormente la letra no pertenece a la palabra.																		
				for(contador2=0; contador2<contador_falladas;contador2++){					//Creo un bucle para recorrer el array de letras falladas.
					if (letras_falladas[contador2]==letra_actual){							//Si la letra fallada ya se ha guardado,
						ya_fallada=true;													//Pongo la variable a true, para no volver a guardar la letra fallada en el array.
					}
				}
				if(ya_fallada==false){														//Si la variable esta en false, quiere decir que no se encontraba la letra en el array.
					letras_falladas[contador_falladas]=letra_actual;						//La guardo en el array.
					contador_falladas++;													//Sumo uno al contador, para guardar la siguiente letra en el posicion correcta del array.												
				}
				System.out.println("Letra incorrecta! No se encuentra en esta palabra");
				System.out.print("Ha introducido las siguientes letras incorrectas: ");
				for(contador2=0; contador2<contador_falladas;contador2++){					//Bucle para sacar por pantalla las letras ya falladas.
					System.out.print(letras_falladas[contador2] + ",");									
					}
				System.out.println("");									
				contador_fallos--;															//Cuenta el numero de intenos restantes.
				System.out.println("Le quedan " + contador_fallos+ " intentos.");
			}
			
			if ((repetida==true)&&(acertada==false)){										//Si la letra entrada, no se ha guardado en el array, pero si que estaba en el:
				System.out.println("Letra repetida! Ya habias puesto esta letra");			//La letra entrada a sido repetida.
				contador_fallos--;															//Cuenta el numero de intenos restantes.
				System.out.println("Le quedan " + contador_fallos+ " intentos.");
			}
			if (contador_fallos>0){																//Mientras queden vidas, se ejecutara todo el bloque, sino que no haga nada.
				for (contador1=0;contador1<palabra_adivinar.length();contador1++){				// Hace tantas vueltas como letras tiene la palabra a adivinar.
					for (contador2=1; contador2<contador_letras;contador2++){					// Hace tantas vueltas como letras tiene el array de letras que coincidentes.
						if (palabra_adivinar.charAt(contador1)==letras_acertadas[contador2]){	//Si la letra de la palabra coincide con alguna de la del array, la saca por pantalla.
							System.out.print(letras_acertadas[contador2]);
							confirmada=true;													//Si ha escrito alguna letra por pantalla esa vuelta, la variable confirmada se pone a true.
							contador_aciertos++;
						}
					}	
					if (confirmada==false){														//Si confirmada=false, quiere decir que no se ha escrito ninguna letra por pantalla en esa vuelta, y saca un asterisco.
						System.out.print("*");	
					}
					confirmada=false;															//Pongo confirmada en false de nuevo para la siguiente vuelta.
				}
			}
			System.out.println("");
	
		}	
		teclado.close();
		if (contador_fallos==0){																//Cuando salga del while, quiere decir que ha perdido o ganado, en este caso ha perdido.
			System.out.println("Ha perdido! la palabra a adivinar, era: " + palabra_adivinar );	
		}else{																					//Cuando salga del while, quiere decir que ha perdido o ganado, en este caso ha ganado.
			System.out.println("Ha ganado!, ha acertado la palabra.");	
	
		}
		
	}
}
