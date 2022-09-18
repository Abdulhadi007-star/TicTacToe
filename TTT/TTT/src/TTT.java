import java.util.*;

public class TTT {

    static int[] playerPositions = new int[0];
    static int[] cpuPositions = new int[0];

    public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);

        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter any position where you wannna place (from 1 to 9):");
            int Playerloc = input.nextInt();
            while(containAll(playerPositions, Playerloc) ||containAll(cpuPositions, Playerloc)){
                System.out.println("Position taken! Enter a correct Position");
                Playerloc = input.nextInt();
            }
            pieceLocation(gameBoard, Playerloc, "Player");

            String result = checker();
            if(result.length()>0) {
                System.out.print(result);
                break;
            }

            Random rand = new Random();
            int cpuloc = rand.nextInt(9) + 1;
            while(containAll(playerPositions, cpuloc) ||containAll(cpuPositions, cpuloc)) {
                cpuloc = rand.nextInt(9) + 1;
            }
            pieceLocation(gameBoard, cpuloc, "cpu");

            printGameBoard(gameBoard);

            result = checker();
            if(result.length()>0) {
                System.out.print(result);
                break;
            }

        }
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void pieceLocation(char[][] gameBoard, int loc, String user) {
        char sym = ' ';
        if (user.equals("Player")) {
            sym = 'x';
            playerPositions = addElement(playerPositions, loc);
        } else if (user.equals("cpu")) {
            sym = 'o';
            cpuPositions = addElement(cpuPositions, loc);
        }
        switch (loc) {
            case 1:
                gameBoard[0][0] = sym;
                break;
            case 2:
                gameBoard[0][2] = sym;
                break;
            case 3:
                gameBoard[0][4] = sym;
                break;
            case 4:
                gameBoard[2][0] = sym;
                break;
            case 5:
                gameBoard[2][2] = sym;
                break;
            case 6:
                gameBoard[2][4] = sym;
                break;
            case 7:
                gameBoard[4][0] = sym;
                break;
            case 8:
                gameBoard[4][2] = sym;
                break;
            case 9:
                gameBoard[4][4] = sym;
                break;
            default:
                System.out.println("Please enter available location!");
                break;

        }
    }

    public static String checker() {
        int[][] winning = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {1, 4, 7},
                {2, 5, 8},
                {3, 6, 9},
                {1, 5, 9},
                {7, 5, 3}
        };
        for (int[] l : winning) {
            if (containAll(playerPositions, l)) {
                return "Congratulations you won!";
            } else if (containAll(cpuPositions, l)) {
                return "CPU wins! Sorry :(";
            } else if (playerPositions.length + cpuPositions.length == 9) {
                return "Match has Drawn!";
            }
        }



        return "";
    }
    public static boolean containAll(int[] Array1, int[] Array2)
    {
        boolean contain;
        for(int i = 0; i<Array2.length; i++)
        {
            contain = false;
            for(int j = 0; j<Array1.length; j++)
            {
                if(Array1[j] == Array2[i])
                    contain = true;
            }
            if(!contain)
                return false;
        }

        return true;
    }
    public static boolean containAll(int[] Array1, int element)
    {
            for(int j = 0; j<Array1.length; j++) {
                if (Array1[j] == element)
                    return true;
            }
            return false;
    }
    public static int[] addElement(int[] Array1, int element)
    {
        int[] newArray = new int[Array1.length +1];
        for(int i = 0; i<Array1.length; i++)
        {
            newArray[i] = Array1[i];
        }
        newArray[newArray.length - 1] = element;
        return newArray;
    }
}
