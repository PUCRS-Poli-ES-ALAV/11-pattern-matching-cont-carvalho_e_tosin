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
    System.out.println("COUNT: " + count + "\n");
    count = 0;

    System.out.println("");

    millis = System.currentTimeMillis();
    System.out.println("Resultado Grupo: " + grupo(s2, s1));
    millisEnd = System.currentTimeMillis();
    total = millisEnd - millis;
    System.out.println("TEMPO: " + total);
    System.out.println("COUNT: " + count + "\n");
    count = 0;
  }

  public static int KMPSearch(String pat, String txt) {
		int M = pat.length(); 
		int N = txt.length(); 

		// cria lps[] que vai guardar o maior 
		// valor prefixo sufixo para o padrão 
		int lps[] = new int[M]; 
		int j = 0; // index for pat[] 

		// Calcula lps[] 
		computeLPSArray(pat, M, lps);

        int i = 0; // index for txt[]
        while (i < N) {
          count++; //<<<<<<
          if (pat.charAt(j) == txt.charAt(i)) {
            j++;
            i++;
          }
          if (j == M) {
            System.out.println("Found pattern " + "at index " + (i - j));
            j = lps[j - 1];
            return i; // ocorrência? colisão?
          }

          // mismatch após j matches
          else if (i < N && pat.charAt(j) != txt.charAt(i)) {
            // Não faz match dos caracteres lps[0..lps[j-1]],
            // não é necesssário, eles combinarão
            if (j != 0)
              j = lps[j - 1];
            else
              i = i + 1;
          }
        }
        return N; // nenhuma ocorrência
      }

    public static void computeLPSArray(String pat, int M, int lps[]) {
		// tamanho do maior prefixo sufixo anterior 
		int len = 0; 
		int i = 1; 
		lps[0] = 0; // lps[0] is always 0 

		// loop calcula lps[i] for i = 1 to M-1 
		while (i < M) { 
      count++; //<<<<<<
			if (pat.charAt(i) == pat.charAt(len)) { 
				len++; 
				lps[i] = len; 
				i++; 
			} 
			else // (pat[i] != pat[len]) 
			{ 
				if (len != 0) { 
					len = lps[len - 1]; 
				} 
				else // if (len == 0) 
				{ 
					lps[i] = len; 
					i++; 
				} 
			} 
		} 
	} 

  private static int grupo(String s2, String s1) {
    int N = s1.length();
    System.out.println("N: " + N);

    String aux = "";
    int contador = 0;

    int x;
    int y;

    for (int i = 0; i < s1.length(); i++) {
      count++; //<<<<<<

      for (int j = 0; j < s2.length(); j++) {
        count++; //<<<<<<
        if (s1.charAt(i) == s2.charAt(j)) {

          x = i;
          y = j;

          while (s1.charAt(x) == s2.charAt(y)) {
            count++; //<<<<<<
            aux += Character.toString(s2.charAt(y));
            if (aux.equals(s2)) {
              System.out.println("houve ocorrência ou colisão");
              return contador;
            }
            x += 1;
            y += 1;
          }

          aux = "";

        } else {
          break;
        }

      }

      if (aux.equals(s2)) {
        break;
      }
      contador++;
    }
	  return contador;

  }

}