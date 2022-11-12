/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package examenlaboratoriojoedsosa;

import java.util.Scanner;

/**
 *
 * @author joeds
 */
public class ExamenLaboratorioJoedSosa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        Scanner stc = new Scanner(System.in);
        String resp = "";
        do {
            System.out.println("1) Conjuntos");
            System.out.println("2) Cifrado por substitucion");
            System.out.println("3) Sobre");
            int opciones = sc.nextInt();
            switch (opciones) {

                case 1:// llamar metodo
                {
                    System.out.println("INGRESE CONJUNTO A");
                    System.out.println("CON EL SIGUIENTE FORMATO -> {a,b,c}");
                    String conjuntoA = sc.nextLine().toLowerCase();
                    System.out.println("INGRESE CONJUNTO B");
                    System.out.println("CON EL SIGUIENTE FORMATO -> {a,b,c}");
                    String conjuntoB = sc.nextLine().toLowerCase();
                    conjuntos(conjuntoA,conjuntoB);
                    
                    break;
                }
                case 2: // llamar metodo
                {
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Ingrese cadena de texto para Encriptar");
                    String cadena = sc1.nextLine().toLowerCase();
                    String cadenaEncrypt = encrypt(cadena);
                    System.out.println("Cadena Ingresada ->" + cadena);
                    System.out.println("Cadena Encriptada ->" + cadenaEncrypt);

                    break;
                }
                case 3: // llamar metodo
                {

                    crearCarta();

                    break;
                }

            }
            System.out.println("Desea continuar S para continuar, cualquier otra tecla para salir!");
            resp = stc.nextLine();
        } while ("s".equalsIgnoreCase(resp) || "S".equalsIgnoreCase(resp));

    }

    public static void conjuntos(String conjuntoA, String conjuntoB) {
        // VALIDAR CONJUNTOS
        boolean conjuntosValidos = false;
        if (conjuntoA.length() > 2) { // PARA PERMITIR AL MENOS UN CONJUNTO {a}
            // existe el caracter { al inicio de la cadena 
            // existe el caracter } al final de la cadena 
            if (String.valueOf(conjuntoA.charAt(0)).equals("{") && String.valueOf(conjuntoA.charAt(conjuntoA.length() - 1)).equals("}")) {
                conjuntosValidos = true;
            }
        }
        if (conjuntoB.length() > 2) { // PARA PERMITIR AL MENOS UN CONJUNTO {a}
            // existe el caracter { al inicio de la cadena 
            // existe el caracter } al final de la cadena 
            if (String.valueOf(conjuntoB.charAt(0)).equals("{") && String.valueOf(conjuntoB.charAt(conjuntoB.length() - 1)).equals("}")) {
                conjuntosValidos = true;
            }
        }

        if (conjuntosValidos) {
            // VERIFICAR SI LOS CONJUNTOS TIENEN UN CARACTER POR CADA SEPARADOR ","
            boolean caracterPorSeparador = true;
            conjuntoA = conjuntoA.substring(1, conjuntoA.length() - 1);
            String[] arrConjuntoA = conjuntoA.split(",");
            for (int i = 0; i < arrConjuntoA.length; i++) {
                if (arrConjuntoA[i].length() != 1) {
                    caracterPorSeparador = false;
                }
            }

            conjuntoB = conjuntoB.substring(1, conjuntoB.length() - 1);
            String[] arrConjuntoB = conjuntoB.split(",");
            for (int j = 0; j < arrConjuntoB.length; j++) {
                if (arrConjuntoB[j].length() != 1) {
                    caracterPorSeparador = false;
                }
            }

            if (caracterPorSeparador) {
                // VERIFICAR SI AMBOS CONJUNTOS SON IGUALES
                int encontrados = 0;
                String arrayUnion = "{";
                String arrayIntersect = "{";
                for (int i = 0; i < arrConjuntoA.length; i++) {
                    if (!arrayUnion.contains(arrConjuntoA[i])) {
                        arrayUnion += arrConjuntoA[i] + ",";
                    }
                    for (int j = 0; j < arrConjuntoB.length; j++) {
                        if (!arrayUnion.contains(arrConjuntoB[j])) {
                            arrayUnion += arrConjuntoB[j] + ",";
                        }
                        if (arrConjuntoA[i].equals(arrConjuntoB[j])) {
                            encontrados++;
                            arrayIntersect += arrConjuntoA[i] + ",";
                        }
                    }
                }

                if (encontrados == arrConjuntoA.length && arrConjuntoA.length == arrConjuntoB.length) {
                    System.out.println("AMBOS CONJUNTOS SON IGUALES");
                } else {
                    // LOS CONJUNTOS NO SON IGUALES 
                    System.out.println("AMBOS CONJUNTOS NO SON IGUALES");
                    System.out.println("UNION: C = " + arrayUnion.substring(0, arrayUnion.length() - 1) + "}");
                    System.out.println("INTERSECCION: D = " + arrayIntersect.substring(0, arrayIntersect.length() - 1) + "}");
                }

            } else {
                System.out.println("DEBE INGRESAR EL FORMATO -> {a,b,c} <- PARA AMBOS CONJUNTOS");
            }

        } else {
            System.out.println("DEBE INGRESAR EL FORMATO -> {a,b,c} <- PARA AMBOS CONJUNTOS");
        }
    }

    public static String encrypt(String cadena) {
        String letrasAbc = "abcdrfghijklmnñopqrstuvwxyz";
        String cadenaEncrypt = "";
        for (int i = 0; i < cadena.length(); i++) {
            String charCadena = String.valueOf(cadena.charAt(i));
            int pos = 0;
            for (int j = 0; j < letrasAbc.length(); j++) {
                String charAbc = String.valueOf(letrasAbc.charAt(j));
                if (charCadena.equals(charAbc)) {
                    pos = j;
                }
            }
            for (int k = 27; k > 0; k--) {
                if ((27 - pos) == k) {
                    cadenaEncrypt += letrasAbc.charAt(k - 1);
                }
            }
        }
        return cadenaEncrypt;
    }

    public static void crearCarta(int base, String caracter) {
        int baseFinal = (base * 2) + 1;
        String aa = "";
        int paraLLegarAlCentro = base + 1;

        for (int i = 0; i < baseFinal; i++) {
            //System.err.println(i);
            for (int j = 0; j < baseFinal; j++) {
                if (i == 0 || i == (baseFinal - 1)) {
                    aa += "*";
                } else {
                    System.err.println(j);
                    //aa+="P";
                    if (j == 0 || j == (baseFinal - 1)) {
                        aa += "*";
                    } else {
                        String line = "";

                        aa += "+";
                    }
                }

            }
            aa += "\n";
        }

        /*for (int i = 0; i < baseFinal; i++) {
            aa+="*";
        }*/
        System.out.println(aa);
    }
}
