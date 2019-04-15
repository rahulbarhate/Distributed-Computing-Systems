import java.util.Scanner;

public class bully
{
    
    static int coordinator;

	
    public static void up(boolean[] state, int up, int noofproc)
    {
        if (state[up - 1])
        {
            System.out.println("Process number " + up + " is already up");
        } 
//        else
//        {
//            int i;
//            state[up - 1] = true;
//            System.out.println("Process number " + up + " held an election");
//            for (i = up; i < noofproc; ++i)
//            {
//                System.out.println("Election message sent from process " + up + " to process" + (i + 1));
//            }
//            for (i = up + 1; i <= noofproc; ++i) 
//            {
//                if (!state[i - 1])
//                {
//                	continue;
//                }
//                System.out.println("Alive message sent from process " + i + " to process" + up);
//                break;
//            }
//        }
        else {
        	System.out.println("Holding an Election");
        	state[up-1]=true;
        	bully.mess(state, up, noofproc);
        }
    }

    public static void down(boolean[] state, int down, int noofproc) 
    {
        if (!state[down - 1]) 
        {
            System.out.println("process " + down + "is already dowm.");
        } 
        else 
        {
            state[down - 1] = false;
            System.out.println("process " + down + " has been crashed.");
            for(int i = 0;i < noofproc;i++)
            	if (state[i])
            		System.out.println("Process " + (i+1) + " is active");
        }  
    }

    public static void mess(boolean[] state, int mess, int noofproc) 
    {
    	System.out.println("Entering");

        if (state[mess - 1]) 
        {
        	if (state[bully.coordinator])
        		 System.out.println("Process " +(bully.coordinator+1)+" is coordinator");
            if (state[noofproc-1]) 
            {
            	bully.coordinator = noofproc - 1;
                System.out.println("Process " +(bully.coordinator+1)+" is coordinator");
            } 
            else if (!state[noofproc-1]) 
            {
                int i;
                System.out.println("process" + mess + "election");
                for (i = mess; i < noofproc; ++i) 
                {
                    System.out.println("election send from process" + mess + "to process " + (i + 1));
                }
                if (mess <= noofproc) {
                    mess(state, mess + 1, noofproc);
                }
               
                int highest = noofproc;
                for(int j = noofproc-1;j >= 0;j--)
                {
                	if (!state[j])
                		highest-=1             ;   		
                }
               
                if (mess == highest)
                	System.out.println("Coordinator is Process " + highest);
                bully.coordinator = highest;
//                for (i = noofproc; i >= mess; --i) 
//                {
//                    if (!state[i - 1]) continue;
//                    System.out.println("Coordinator message send from process" + i + "to all");
//                    break;
//                }
            }
        } 
//        else 
//        {
//            System.out.println("Prccess" + mess + "is down");
//        }
    }

    public static void main(String[] args) 
    {
        int choice, noofproc;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes: ");
        noofproc = sc.nextInt(); 
        bully.coordinator=noofproc-1;
        System.out.println("You have entered " + noofproc + " processes");

        boolean[] state = new boolean[noofproc];
        
        for (int i = 0; i < noofproc; ++i) 
        {
            state[i] = true;
        }
        System.out.println("The number of active processes are: " +noofproc);
        System.out.println("They are named as - p1 p2 and so on");
        System.out.println("Process " +noofproc+ " is the coordinator");
        
        do 
        {
            System.out.println("\n");
            System.out.println("1. Recover a process.");
            System.out.println("2. Crash a process.");
            System.out.println("3. Send an election message.");
            System.out.println("4. Exit.");
            System.out.println("\n");
            
            choice = sc.nextInt();
            switch (choice)
            {
                case 1: 
                {
                    System.out.println("Recover a process by providing it's process number: ");
                    int up = sc.nextInt();
                    bully.up(state, up, noofproc);
                    break;
                }
                case 2: 
                {
                    System.out.println("Bring down a process by providing it's process number: ");
                    int down = sc.nextInt();
                    bully.down(state, down, noofproc);
                    break;
                }
                case 3: 
                {
                    System.out.println("Process number which intends to send an election message");
                    int mess = sc.nextInt();
                    bully.mess(state, mess, noofproc);
                }
            }
        } while (choice != 4);
        sc.close();
    }
}
