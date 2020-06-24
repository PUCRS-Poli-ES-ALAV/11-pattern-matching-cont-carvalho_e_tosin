import java.util.Random;

public class KMP {
  public static int count;
  public static long millis;
  public static long millisEnd;
  public static long total;

  public static void main(String[] args) {

    Random quantidadedeletras = new Random();


    int quantidade = 500000;
    String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";

    String stringaleatoria = "";
    int index = -1;
    for (int i = 0; i < quantidade; i++) {
      index = quantidadedeletras.nextInt(letras.length());
      stringaleatoria += Character.toString(letras.charAt(index));

    }
    
    int quantidade2 = 200;
    String stringaleatoria2 = "";
    int index1 = -1;
    for (int i = 0; i < quantidade2; i++) {
      index1 = quantidadedeletras.nextInt(letras.length());
      stringaleatoria2 += Character.toString(letras.charAt(index1));

    }

    String s1 = stringaleatoria;
    //String s1 = "ABCDCBDCBDACBDABDCBADF";
    String s2 = stringaleatoria2;
    // String s2 = "ADF";
    

    millis = System.currentTimeMillis();
    System.out.println("Resultado KMP: " + KMPSearch(s2, s1));
    millisEnd = System.currentTimeMillis();
    total = millisEnd - millis;
    System.out.println("TEMPO: " + total);
    System.out.println("INTERACAO: " + count + "\n");
    System.out.println("INSTRUCAO: 63");

    count = 0;

    System.out.println("");

    millis = System.currentTimeMillis();
    System.out.println("Resultado Grupo: " + grupo(s2, s1));
    millisEnd = System.currentTimeMillis();
    total = millisEnd - millis;
    System.out.println("INTERACAO: " + count + "\n");
    System.out.println("INSTRUCAO: 59");
    count = 0;
  }

  public static int KMPSearch(String pat, String txt) {
		int M = pat.length(); //4 >>>>
		int N = txt.length(); //4 >>>>

		// cria lps[] que vai guardar o maior 
		// valor prefixo sufixo para o padrão 
		int lps[] = new int[M]; //3 >>>>
		int j = 0; // index for pat[] //2 >>>>

		// Calcula lps[] 
		computeLPSArray(pat, M, lps);

        int i = 0; // index for txt[] //3 >>>>
        while (i < N) { //1 >>>>
          count++; //<<<<<<
          if (pat.charAt(j) == txt.charAt(i)) { //5 >>>>
            j++; //1 >>>>
            i++; //1 >>>>
          }
          if (j == M) { //1 >>>>
            System.out.println("Found pattern " + "at index " + (i - j));
            j = lps[j - 1]; //2 >>>>
            return i; // ocorrência? colisão? //1 >>>>
          }

          // mismatch após j matches
          else if (i < N && pat.charAt(j) != txt.charAt(i)) { //6 >>>>
            // Não faz match dos caracteres lps[0..lps[j-1]],
            // não é necesssário, eles combinarão
            if (j != 0) //1 >>>>
              j = lps[j - 1]; //2 >>>>
            else
              i = i + 1; //2 >>>>
          }
        }
        return N; // nenhuma ocorrência //1 >>>>
      }
      //40

    public static void computeLPSArray(String pat, int M, int lps[]) {
		// tamanho do maior prefixo sufixo anterior 
		int len = 0; //2 >>>>
		int i = 1; //2 >>>>
		lps[0] = 0; // lps[0] is always 0 //2 >>>>

		// loop calcula lps[i] for i = 1 to M-1 
		while (i < M) { //1 >>>>
      count++; //<<<<<<
			if (pat.charAt(i) == pat.charAt(len)) { //5 >>>>
				len++; //1 >>>>
				lps[i] = len; //1 >>>>
				i++; //2 >>>>
			} 
			else // (pat[i] != pat[len]) 
			{ 
				if (len != 0) { //1 >>>>
					len = lps[len - 1]; //2 >>>>
				} 
				else // if (len == 0) //1 >>>>
				{ 
					lps[i] = len; //2 >>>>
					i++; //2 >>>>
				} 
			} 
		} 
  } 
  // 23

  private static int grupo(String s2, String s1) {
    int N = s1.length(); //4 >>>>
    System.out.println("N: " + N);

    String aux = ""; //2 >>>>
    int contador = 0; //2 >>>>

    int x; //1 >>>>
    int y; //1 >>>>

    for (int i = 0; i < s1.length(); i++) { //7 >>>>
      count++; //<<<<<<

      for (int j = 0; j < s2.length(); j++) { //7 >>>>
        count++; //<<<<<<
        if (s1.charAt(i) == s2.charAt(j)) { //5 >>>>

          x = i; //1 >>>>
          y = j; //1 >>>>

          while (s1.charAt(x) == s2.charAt(y)) { //5 >>>>
            count++; //<<<<<<
            aux += Character.toString(s2.charAt(y)); //6 >>>>
            if (aux.equals(s2)) { //3 >>>>
              System.out.println("houve ocorrência ou colisão");
              return contador; //1 >>>>
            }
            x += 1; //2 >>>>
            y += 1; //2 >>>>
          }

          aux = ""; //1 >>>>

        } else { //1 >>>>
          break; //1 >>>>
        }

      }

      if (aux.equals(s2)) { //2 >>>>
        break; //1 >>>>
      }
      contador++; //2 >>>>
    }
	  return contador; //1 >>>>

  }

}