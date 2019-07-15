import java.util.Scanner;

class Program7 {
    Scanner sc = new Scanner(System.in);
    Cryptor c = new Cryptor();

    public static void main(String[] args) {
        System.out.println("CodeBreaker! by jacob burgess");
        Program7 p = new Program7();
        p.prompt();
    }

    // prompts user until it has correct input
    private void prompt() {
        System.out.println("(e)ncrypt or (d)ecrypt?");
        char response = sc.next().charAt(0);
        switch (response) {
        case 'd':
            System.out.print("string to decrypt? ");
            sc.nextLine();
            System.out.println("decrypted message: " + c.decrypt(sc.nextLine()));
            break;
        case 'e':
            System.out.println("page number?");
            int page = sc.nextInt();
            System.out.println("string to encrypt?");
            sc.nextLine();
            System.out.println("encrypted message:" + c.encrypt(page, sc.nextLine()));
            break;
        default:
            prompt();
            break;
        }
    }
}