
public class Sudoku {

    static Set empty = new Set(9);
    static Set fullSet;
    static int[][] board;
    static Set[][] allowedSets;

    public static void main(string[] args) {
        initializeBoard();
        if (args.length == 0) {
            readGame();
        } else {
            readGame(args[0]);
        }
        boolean solved = solveGame();
        if (!solved) {
            printSets();
        }
        printGame();
    }

    /**
     * tworzenie tablicy
*
     */
    public static void initializeBoard() {
        board = new int[9][9];
        allowedSets = new Set[9][9];

        Set fullSet = new Set(9);
        for (int i = 1; i <= 9; i++) {
            fullSet.insert(i);
        }
        //jezeli nie ma zadnego ruchu jakikolwiek cyfra moze znależć sie wkazdym położeniu
        for (int j = 0; j < 9; j++) {
            allowedSets[i][j] = new Set(fullSet);
        }
    }

    
        public static void printSets
{
for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                allowedSets[i][j].print();
            }
        }
    }

    public static void move(int x, int y, int val) {
        board[x][y] = val;
        if (val == 0) {
            return;
        }
        allowedSets[x][y].intersection(empty);
        for (int i = 0; i < 9; i++) {
            allowedSets[x][i].delete(val);
        }
        for (int i = 0; i < 9; i++) {
            allowedSets[i][y].delete(val);
        }
        int boxX = x / 3;
        int boxY = y / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                allowedSets[3 * boxX + i][3 * boxY +j].delete(val);
            }
        }
    }/**
	 * Read in a game from the command line and put moves on the board
	 *
	**/
	public static void readGame()
	{
		try
		{
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader stdin = new BufferedReader(isr);

			String line = "";

			for(int i = 0; i < 9; i++)
			{
				line = stdin.readLine();
				for(int j = 0; j < 9; j++)
					move(i,j,line.charAt(j)-'0');
			}
		}
		catch(java.io.IOException e)
		{
			System.out.println(e);
		}
	}

	
	public static void readGame(String filename)
	{
		try
		{
			FileReader fr = new FileReader(filename);
			BufferedReader stdin = new BufferedReader(fr);

			String line = "";

			for(int i = 0; i < 9; i++)
			{
				line = stdin.readLine();
				for(int j = 0; j < 9; j++)
					move(i,j,line.charAt(j)-'0');
			}
		}
		catch(java.io.IOException e)
		{
			System.out.println(e);
		}
	}

	public static int getElement(Set s)
	{
		for(int i = 1; i <= 9; i++)
			if(s.isMember(i))
				return i;

		System.out.println("uh oh");
		return 0;
	}

	
	public static boolean solveGame()
	{
		boolean solved = false;
		boolean moved = false;
		int moves = 0;
		while(!solved)
		{
			solved = true;
			moved = false;
			for(int i = 0; i < 9; i++)
			{
				for(int j = 0; j < 9; j++)
				{
					if(allowedSets[i][j].size() == 1)
					{
						move(i,j,getElement(allowedSets[i][j]));
						moved = true;
						moves++;
					}
					else if(!allowedSets[i][j].isEmpty())
						solved = false;
				}
			}
			if(!moved) 
				break;
		}

		System.out.println("moves made: " + moves);
		return solved;
	}

	
	public static void printGame()
	{
		System.out.println();
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
				System.out.print(board[i][j]);
			System.out.println();
		}
			
	}
}

}
