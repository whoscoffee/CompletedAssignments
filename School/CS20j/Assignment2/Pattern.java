import java.util.Scanner;

class Pattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = { "00000000000000000000000000000000", "01010101010101010101010101010101",
			 "00110011001100110011001100110011", "01100110011001100110011001100110",
			 "00001111000011110000111100001111", "01011010010110100101101001011010",
			 "00111100001111000011110000111100", "01101001011010010110100101101001",
			 "00000000111111110000000011111111", "01010101101010100101010110101010",
			 "00110011110011000011001111001100", "01100110100110010110011010011001",
			 "00001111111100000000111111110000", "01011010101001010101101010100101",
			 "00111100110000110011110011000011", "01101001100101100110100110010110",
			 "00000000000000001111111111111111", "01010101010101011010101010101010",
			 "00110011001100111100110011001100", "01100110011001101001100110011001",
			 "00001111000011111111000011110000", "01011010010110101010010110100101",
			 "00111100001111001100001111000011", "01101001011110011001011010010110",
			 "00000000111111111111111100000000", "01010101101010101010101001010101",
			 "00110011110011001100110000110011", "01100110100110011001100101100110",
			 "00001111111100001111000000001111", "01011010101001011010010101011010",
			 "00111100110000111100001100111100", "01101001100101101001011001101001" };
        String in; 
        int index;
        while (sc.hasNext()) {
            in = sc.next();
            index = Index(in, arr);
            if (index == -1)
                System.out.println(in);
            else
                System.out.println(index);
        }
    }

    public static int Index(String in, String[] arr) {
        int smallest = 7, difcount, count = 0, indexOfSmallest = -1;
        for (String comparor : arr) {
            difcount = difference(in, comparor);
            if (difcount <= smallest) {
                smallest = difcount;
                indexOfSmallest = count;
            }
            count++;
        }
        return indexOfSmallest;
    }

    public static int difference(String in, String comparor) {
        char[] arrIn = in.toCharArray(), arrComp = comparor.toCharArray();
        int count = 0;
        for (int i = 0; i < 32; i++)
            if (arrIn[i] != arrComp[i])
                count++;
        return count;
    }
}
