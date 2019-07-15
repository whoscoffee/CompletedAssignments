import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class Cryptor {
    private int rows, columns, page;
    private char[][] grid;
    private String secret;
    private HashMap<Character, Integer> hashbrowns = new HashMap<>();
    String msg;
    BookCode bc;

    // encryptor
    String encrypt(int page, String msg) {
        this.msg = msg.toLowerCase().replace(" ", "");
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
        // sets page
        for (int i = 0; i < 5; i++)
            if (hashbrowns.containsKey(this.msg.charAt(i))) {
                page = (page * 10) + hashbrowns.get(this.msg.charAt(i));

            }
        this.page = page;
        // then remove the next 5 chars
        this.msg = this.msg.substring(5, this.msg.length());
        // takes out x's in the rear
        int count = 0;
        for (int i = this.msg.length() - 1; i > 0; i--)
            if (this.msg.charAt(i) == 'x')
                count++;
            else
                break;
        if (count > 0)
            this.msg = this.msg.substring(0, this.msg.length() - count);
        setup();
        block();
        fillGridIn();
        return readIn();
    }

    // general init
    private void setup() {
        int sum = 0, count = 0;
        try {
            bc = new BookCode("codebreaker-words.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        secret = bc.lookUp(page);
        columns = secret.length();
        for (char c : secret.toCharArray())
            if (sum < msg.length() + count) {
                sum += c - 96;
                count++;
            }

        rows = (msg.length() + count) / columns;
        if ((msg.length() + count) % columns != 0)
            rows++;

        grid = new char[rows][columns];
    }

    // reads out encrypted message
    private String readOut() {
        ArrayList<Character> result = new ArrayList<>();
        int[] pagevalues = seperate(page);
        Random rand = new Random();
        // turns grid into arraylist and removes spaces
        for (int i = 0; i < columns; i++)
            for (int j = 0; j < rows; j++)
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
                if (pagevalues[i] == 0)
                    result.add(i, 'k');
                else
                    result.add(i, (char) (pagevalues[i] + 96));
            else
                result.add(i, 'x');
        // adds 10 rando chars in front plus 2 more spaces between groups
        for (int i = 0; i < 12; i++)
            if (i == 6 || i == 0)
                result.add(0, ' ');
            else
                result.add(0, (char) (rand.nextInt(26) + 97));
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
                if (grid[i][j] != ' ')
                    car[count++] = grid[i][j];
        return new String(car);
    }

    // blocks out grid spots with spaces given secret
    private void block() {
        int count = 0, msgCount = 0, index = 0;
        char c = secret.charAt(index++);
        boolean atEnd = false;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                count++;
                msgCount++;
                if (count == c - 96 && secret.length() > index) {
                    grid[i][j] = ' ';
                    count = 0;
                    msgCount--;
                    if (atEnd)
                        index++;
                    if (secret.length() == index + 1) {
                        c = secret.charAt(index);
                        atEnd = true;
                    } else if (secret.length() > index)
                        c = secret.charAt(index++);
                }
                if (msgCount > msg.length())
                    grid[i][j] = ' ';
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
    private int[] seperate(int num) {
        int temp = num, count = 0;
        // counts how many ints
        while (temp != 0) {
            temp /= 10;
            count++;
        }
        // sets and returns results
        int[] result = new int[count];
        while (num != 0) {
            result[--count] = num % 10;
            num /= 10;
        }
        return result;
    }
}
