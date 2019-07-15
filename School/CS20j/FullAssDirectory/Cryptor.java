import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class Cryptor {
    private int rows, columns, page;
    private char[][] grid;
    private String secret;
    private HashMap<Character, Integer> hashbrowns = new HashMap<>();
    String msg;
    private BookCode bc;

    // encryptor
    String encrypt(int page, String msg) {
        this.msg = msg.toLowerCase();
        this.page = page;
        setup();
        block();
        fillGridOut();
        return readOut();
    }

    // decryptor
    String decrypt(String msg) {
        int page = 0;
        hashbrowns.put('a', 1);
        hashbrowns.put('b', 2);
        hashbrowns.put('c', 3);
        hashbrowns.put('d', 4);
        hashbrowns.put('e', 5);
        hashbrowns.put('f', 6);
        hashbrowns.put('g', 7);
        hashbrowns.put('h', 8);
        hashbrowns.put('i', 9);
        hashbrowns.put('k', 0);
        // removes spaces and first 10 characters
        this.msg = msg.replace(" ", "").toLowerCase();
        this.msg = this.msg.substring(10, this.msg.length());
        for (int i = 0; i < 5; i++)
            if (this.msg.charAt(i) != 'x') {
                page = (page * 10) + hashbrowns.get(this.msg.charAt(i));

            }
        this.page = page;
        // then remove the next 5 chars
        this.msg = this.msg.substring(5, this.msg.length()).replace("x", "");
        setup();
        block();
        fillGridIn();
        printGrid();
        return readIn();
    }

    // general init
    private void setup() {
        try {
            bc = new BookCode("codebreaker-words.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        secret = bc.lookUp(page);
        columns = secret.length();
        rows = msg.length() / columns;
        rows++;
        grid = new char[rows][columns];
    }

    // reads out encrypted message
    private String readOut() {
        ArrayList<Character> result = new ArrayList<>();
        int[] pagevalues = seperate(page);
        Random rand = new Random();
        // turns grid into arraylist and removing spaces
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                if (grid[j][i] != ' ')
                    result.add(grid[j][i]);
        // takes new char arr and adds proper spaceing.
        for (int i = 0; i < result.size(); i += 5) {
            result.add(i++, ' ');
            if (i + 5 > result.size())
                for (int j = result.size(); j < i + 5; j++)
                    result.add('x');
        }
        // adds x's where one and two arent
        // and adds hashvalues of page num: ex: xbxxb = 22
        for (int i = 0; i < 5; i++)
            if (pagevalues.length > i)
                result.add(i, (char) (pagevalues[i] + 96));
            else
                result.add(i, 'x');
        // adds 10 rando chars in front plus 2 more spaces between groups
        for (int i = 0; i < 12; i++)
            if (i == 6 || i == 0)
                result.add(0, ' ');
            else
                result.add(0, (char) (rand.nextInt(26) + 96));
        // turns arraylist of chars into string
        String str = " ";
        for (int i = 0; i < result.size(); i++)
            str += result.get(i);
        return str;
    }

    // reads out decrypted message
    private String readIn() {
        char[] car = new char[rows * columns];
        int count = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                if (grid[i][j] != ' ' && grid[i][j] != 'x')
                    car[count++] = grid[i][j];
        return new String(car);
    }

    // blocks out grid spots with spaces given secret
    private void block() {
        int count = 0, msgCount = 0, index = 0;
        char c = secret.charAt(index++);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                count++;
                msgCount++;
                if (count == c - 96) {
                    grid[i][j] = ' ';
                    count = 0;
                    msgCount--;
                    c = secret.charAt(index++);
                }
                if (msgCount > msg.length())
                    grid[i][j] = ' ';
            }

    }

    // for testing--remove me
    void printGrid() {
        System.out.printf("Rows: %d%nColumns: %d%n%s%n", rows, columns, secret);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                System.out.print(grid[i][j] + " ");
            System.out.println();
        }
    }

    // fills grid with msg to be encrypted
    private void fillGridOut() {
        int count = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                if (count == msg.length())
                    grid[i][j] = ' ';
                else if (grid[i][j] != ' ')
                    grid[i][j] = msg.charAt(count++);
    }

    // fills grid with encrypted msg to be decrypted
    private void fillGridIn() {
        int count = 0;
        for (int i = 0; i < columns; i++)
            for (int j = 0; j < rows; j++)
                if (count == msg.length())
                    grid[j][i] = ' ';
                else if (grid[j][i] != ' ')
                    grid[j][i] = msg.charAt(count++);
    }

    // seperate page num: ex: 123 = 1,2,3 and 33 = 3,3 etc
    private int[] seperate(double num) {
        boolean finished = false;
        int count = 0;
        while (finished == false) {
            if (num / 10 < 10)
                finished = true;
            num /= 10;
            count++;
        }
        int[] result = new int[2];
        count = 0;
        while (num != 0 && count < result.length) {
            result[count] = (int) Math.floor(num);
            num -= result[count++];
            num *= 10;
        }
        return result;
    }

}

