public class MancalaBoard {

    int[][] bucket;
    int []storage;
    int noOfBuckets;
    int noOfStoneEachBucket;

        MancalaBoard(int noOfBuckets,int noOfStoneEachBucket){

            bucket=new int[2][noOfBuckets];
            storage=new int[2];
            this.noOfBuckets=noOfBuckets;
            this.noOfStoneEachBucket=noOfStoneEachBucket;

            for(int i = 0; i < 2; i++)
            {
                storage[i] = 0;
                for(int j = 0; j < noOfBuckets; j++)
                {
                    bucket[i][j] = noOfStoneEachBucket;
                }
            }

        }


    int updateBoard(int player, int position)
    {

        if(bucket[player][position]==0) return player;

        int currentSide = player;
        int currentStones = bucket[currentSide][position];
        int currentBucket = position + 1;
        int nextPlayer = 1-player;
        bucket[player][position] = 0;

        Game temp=new Game();
        while(currentStones != 0)
        {
            if(currentBucket == noOfBuckets) //changing side
            {
                currentBucket = 0;

                if(currentSide == player)
                {
                    storage[player]++;
                    currentStones--;
                    if(currentStones==0)
                    {
                        nextPlayer = player; //additional move
                    }
                }
                currentSide = 1-currentSide;
                continue;
            }

            if(currentStones == 1 && bucket[currentSide][currentBucket] == 0 && currentSide == player && bucket[nextPlayer][noOfBuckets-1 - currentBucket]>0) //capture
            {
                storage[player] += bucket[nextPlayer][noOfBuckets-1 - currentBucket] + 1;
                temp.stonesCaptured[player] += bucket[nextPlayer][noOfBuckets-1 - currentBucket];
                bucket[nextPlayer][noOfBuckets-1 - currentBucket] = 0;
                currentStones--;
                continue;
            }

            if(currentBucket < noOfBuckets)
            {
                bucket[currentSide][currentBucket]++;
                currentStones--;
                currentBucket++;
                continue;
            }
        }

        return nextPlayer;
    }

    int gameState()
    {
        int total0 = 0;
        int total1 = 0;

        for(int i = 0; i < noOfBuckets; i++)
        {
            total0 += bucket[0][i];
            total1 += bucket[1][i];
        }

        //giving remaining seeds to other player
        if(total0 == 0)
        {
            storage[1] += total1;
            for(int i = 0; i < noOfBuckets; i++)
            {
                bucket[1][i] = 0;
            }

        }
        else if(total1 == 0)
        {
            storage[0] += total0;
            for(int i = 0; i < noOfBuckets; i++)
            {
                bucket[0][i] = 0;
            }

        }
        //0 for player 0, 1 for player 1, 2 for draw, 3 for continuing game
        if(total0 == 0 || total1 == 0)
        {
            if(storage[0] == storage[1]) return 2;
            else if(storage[0] > storage[1]) return 0;
            else return 1;
        }
        else return 3;
    }

    void mirrorBoardTo(MancalaBoard mirrorBoard){

            mirrorBoard.storage[0] = this.storage[0];
            mirrorBoard.storage[1] = this.storage[1];


        for(int i = 0; i < noOfBuckets ; i++)
        {
            mirrorBoard.bucket[0][i] = bucket[0][i];
            mirrorBoard.bucket[1][i] = bucket[1][i];
        }



    }



    void printBoard(){

        System.out.println("\n                      <== (Player 1) ==>");
        System.out.println("-----------------------------------------------------------------");
        System.out.print("    |");
        for(int i=noOfBuckets;i>0;i--)
            System.out.print("  ("+i+")   |");
        System.out.println("    \n    =======================================================");

        for(int i = 0; i < noOfBuckets; i ++)
        {
            System.out.print("    |   "+bucket[0][noOfBuckets-i-1]);
        }
        System.out.print("    |");
        System.out.print("\n"+storage[0]+"   ");

        for(int j=0;j<noOfBuckets;j++)
         System.out.print("*********");

        System.out.println("*   "+storage[1]);

        for(int i = 0; i < noOfBuckets; i ++)
        {
            System.out.print("    |   "+bucket[1][i]);
        }
        System.out.println("    |");
        System.out.println("    =======================================================");
        System.out.print("    |");
        for(int i=1;i<=noOfBuckets;i++)
            System.out.print("  ("+i+")   |");
        System.out.println("     \n-----------------------------------------------------------------");
        System.out.println("                      <== (Player 2) ==>");

    }

}
