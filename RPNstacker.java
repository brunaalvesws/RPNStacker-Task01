import java.util.*;
import java.io.File;
import java.io.IOException;

class Token {
  public int value;
  public Token abaixo;

  Token(int caractere_entrada) {
    value = caractere_entrada;
    abaixo = null;
  }
}

class Stack {
  public Token topo;

  Stack() {
    topo = null;
  }

  public void push(Token elemento) {
    Token last_topo = topo;
    topo = elemento;
    elemento.abaixo = last_topo;
  }

  public int pop() {
    Token new_topo = topo.abaixo;
    Token old_topo = topo;
    topo = new_topo;
    return old_topo.value;
  }
}

class Main {
  public static void main(String[] args) throws IOException {

    Stack stack = new Stack();
    String file = "entry.txt";
    Scanner scanner = new Scanner(new File(file));
    scanner.useDelimiter("\n");
    while (scanner.hasNext()) {
      String entry = scanner.next();
      boolean isNumeric = entry.chars().allMatch(Character::isDigit);
      if (isNumeric) {
        Token numero = new Token(Integer.parseInt(entry));
        stack.push(numero);
      } else {
        if (entry.equals("+")) {
          int snd_oper = stack.pop();
          int fst_oper = stack.pop();
          int soma = fst_oper + snd_oper;
          Token result = new Token(soma);
          stack.push(result);
        } else if (entry.equals("*")) {
          int snd_oper = stack.pop();
          int fst_oper = stack.pop();
          int mult = fst_oper * snd_oper;
          Token result = new Token(mult);
          stack.push(result);
        } else if (entry.equals("-")) {
          int snd_oper = stack.pop();
          int fst_oper = stack.pop();
          int sub = fst_oper - snd_oper;
          Token result = new Token(sub);
          stack.push(result);
        } else {
          int snd_oper = stack.pop();
          int fst_oper = stack.pop();
          int div = fst_oper / snd_oper;
          Token result = new Token(div);
          stack.push(result);
        }
      }
    }
    int resultado_final = stack.pop();
    System.out.println(resultado_final);
    scanner.close();
  }
}