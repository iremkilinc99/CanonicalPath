import java.util.Scanner;
import java.util.Stack;

public class CanonicalPathAndDecodeMessageLab2 {
    public String canonicalPath(String path) {
        Stack<String> stack = new Stack<String>();
        int currentIndex = 0;
        String canPath = "";

        while (currentIndex != path.length()) {
            if (path.charAt(currentIndex) == '.' && stack.peek().equals(".")) {
                stack.pop(); // '.'
                stack.pop(); // '/'
                if(!stack.empty())  stack.pop(); // directory

                currentIndex ++ ;

            } else if (path.charAt(currentIndex) != '.' && !stack.empty() && stack.peek().equals(".")) {
                stack.pop(); // '.'

                if(stack.peek().equals("/")) { stack.pop(); // '/'
                } else stack.push("."); // file adında bulunan nokta için.

                stack.push(path.charAt(currentIndex) + "");
                currentIndex ++ ;

            } else if (path.charAt(currentIndex) == '/' &&  !stack.empty() && stack.peek().equals("/")) {
                currentIndex ++ ;

            } else
                if(currentIndex == path.length() -1 && (path.charAt(currentIndex) == '/'  || path.charAt(currentIndex) == '.'))
                    currentIndex ++;

                else {
                    stack.push(path.charAt(currentIndex) + "");
                    currentIndex ++ ;
            }
        }

        while (!stack.empty()) {
            canPath = stack.pop() + canPath ;
        }

        return canPath;
    }

    public String decodeTheMessage(String msg) {
        Stack<String> stack = new Stack<String>();
        int currentIndex = 0;
        String decodedMsg  = "";
        String message = "";
        String subMsg = "";
        char lastChar = msg.charAt(0);
        while (currentIndex != msg.length()) {
             if(msg.charAt(currentIndex) != ']') {
                 stack.push(msg.charAt(currentIndex) + "");

             }else {
                 while (!stack.peek().equals("[")) {
                      decodedMsg = stack.pop() + decodedMsg ;
                 }
                 stack.pop() ; // '[' çıktı

                 if(lastChar == ']') { // sola ekleme
                     subMsg = decodedMsg + subMsg;
                     subMsg = subMsg.repeat(Integer.parseInt(stack.pop()));
                     message = message + subMsg ;
                     subMsg = "";
                 }
                 else { // sağ taraf ekleme
                     decodedMsg = decodedMsg.repeat(Integer.parseInt(stack.pop()));
                     subMsg = subMsg + decodedMsg ;
                 }

                 decodedMsg = "";
             }
             lastChar = msg.charAt(currentIndex); // sağ sol ekleme ayrımını kontrol etmek için
             currentIndex ++;
        }
        return message;
    }

    public static void main (String args[]) {
        Scanner scanner = new Scanner(System.in);
        CanonicalPathAndDecodeMessageLab2 pathAndMessage = new CanonicalPathAndDecodeMessageLab2();

        System.out.print("ENTER A PATH: ");
        String path = scanner.nextLine();
        System.out.print("PATH: ");
        System.out.println(pathAndMessage.canonicalPath(path));
       /* System.out.println(pathAndMessage.canonicalPath("/a/b/../c/./d/../file.txt")); //ornek olarak kullanmak isterseniz
        System.out.println(pathAndMessage.canonicalPath("/a/./b/../../c/"));
        System.out.println(pathAndMessage.canonicalPath("/a/..")); //ok
        System.out.println(pathAndMessage.canonicalPath("/a/./b/./c/./d/"));
        System.out.println(pathAndMessage.canonicalPath("/a//b//c//////d"));
        System.out.println(pathAndMessage.canonicalPath("/a/../.././../../."));
        System.out.println(pathAndMessage.canonicalPath("/usr/")); */

        System.out.print("ENTER A MESSAGE: ");
        String encodedMsg = scanner.nextLine();
        System.out.print("MESSAGE: ");
        System.out.println(pathAndMessage.decodeTheMessage(encodedMsg));
      /*  System.out.println(pathAndMessage.decodeTheMessage("2[abc2[de]]")); //ornek olarak kullanmak isterseniz diye bırakıyorum
        System.out.println(pathAndMessage.decodeTheMessage("1[abc2[de]]"));
        System.out.println(pathAndMessage.decodeTheMessage("1[1[X2[y]1[A]]2[de2[ad]]3[T2[am]]")); */
    }
}
