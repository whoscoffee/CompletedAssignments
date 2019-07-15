import java.util.Scanner;

class Program7 {
    Scanner sc = new Scanner(System.in);
    Cryptor c = new Cryptor();

    public static void main(String[] args) throws Exception {
        System.out.println("CodeBreaker! by jacob burgess");
        Program7 p = new Program7();
        p.prompt();
    }

    // prompts user until it has correct input
    void prompt() throws Exception {
        System.out.println("(e)ncrypt or (d)ecrypt? e");
        char response = sc.next().charAt(0);
        switch (response) {
        case 'd':
            decrypt();
            break;
        case 'e':
            encrypt();
            break;
        default:
            prompt();
            break;
        }
    }

    void encrypt() throws Exception {
        System.out.println("page number?");
        int page = sc.nextInt();
        System.out.println("string to encrypt?");
        sc.nextLine();
        System.out.println("encrypted message:" + c.encrypt(page, sc.nextLine()));
    }

    void decrypt() {
        System.out.print("string to decrypt? ");
        sc.nextLine();
        System.out.println("decrypted message: " + c.decrypt(sc.nextLine()));
    }
}
