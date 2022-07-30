import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        String[][] cinema = new String[rows][seats];
        int seatPrice = 10;
        int totalSeats = rows * seats;
        int currentIncome = 0;
        int totalIncome = rows * seats <= 60 ? totalSeats * 10 : rows % 2 == 0 ?
        		seats * (rows / 2) * 10 + seats * (rows / 2) * 8 :
        			seats * (rows / 2) * 10 + seats * (rows / 2 + 1) * 8;
        
        for (int i = 0; i < rows; i++) {
    	    for (int j = 0; j < seats; j++) {
    		    cinema[i][j] = "S";
    	    }
        }
        
        while (true) {
            instructions(scanner);
        	int input = scanner.nextInt();
        	if (input == 1) {
        		showSeats(cinema, rows, seats);
        		continue;
        	} else if (input == 2) {
        		buyTicket(scanner, cinema, rows, seats, seatPrice, currentIncome);
        		continue;
        	} else if (input == 3) {
        		statistics(cinema, totalSeats, currentIncome, totalIncome);
        	} else {
        		break;
        	}
        }
        
    }
    
    public static void instructions(Scanner scanner) {
    	System.out.println();
    	System.out.println("1. Show the seats");
    	System.out.println("2. Buy a ticket");
    	System.out.println("3. Statistics");
    	System.out.println("0. Exit");
    	System.out.println();
    	
    }
    
    public static void showSeats(String[][] cinema, int rows, int seats) {
    	System.out.println();
    	System.out.print("Cinema:\n ");
    	
        for(int i = 1; i <= seats; i++) {
    	    System.out.print(" " + i);
        }
        System.out.println();
        
       for (int i = 0; i < rows; i++) {
           System.out.print(i + 1 + " ");
           
           for (int j = 0; j < seats; j++) {
               System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    	
    }
    
    public static void buyTicket(Scanner scanner, String[][] cinema, int rows, int seats, int seatPrice,
    		int currentIncome) {
    	int x = 0;
    	int y = 0;
    	
    	while (true) {
	    	System.out.println();
	        System.out.println("Enter a row number:");
	        x = scanner.nextInt();
	        System.out.println("Enter a seat number in that row:");
	        y = scanner.nextInt();
	        
	        if (x > rows || y > seats) {
	        	System.out.println("Wrong input");
	        	continue;
	        } else if (cinema[x - 1][y - 1].contentEquals("B")) {
	        	System.out.println("That ticket has already been purchased!");
	        	continue;
	        } else {
	        	break;
	        }
    	}    
       
        if (rows * seats > 60) {
    	    if (x > rows / 2) {
    		    seatPrice = 8;
            }
        }
        cinema[x - 1][y - 1] = "B";
       
        System.out.println();
        System.out.println("Ticket price: $" + seatPrice);
    }
    
    public static void statistics(String [][] cinema, int totalSeats, int currentIncome, int totalIncome) {
    	
    	int soldTickets = 0;
    	
    	
    	for (String[] a : cinema) {
    		for (String s : a) {
    			if (s.contentEquals("B")) {
    				soldTickets++;
    			}
    		}
    	}
    	System.out.println();
    	System.out.printf("Number of purchased tickets: %d%n", soldTickets);
    	
    	if (totalSeats <= 60) {
    		currentIncome = soldTickets * 10;
    	} else {
    		for (int i = 0; i < cinema.length; i++) {
    			for (int j = 0; j < cinema[i].length; j++) {
    				if (cinema[i][j].contentEquals("B") && i + 1 > cinema.length / 2) {
    					currentIncome += 8;
    				} else if (cinema[i][j].contentEquals("B")) {
    					currentIncome += 10;
    				}
    			}
    		}
    	}
    	
    	float percentage = (float) (soldTickets * 100) / (float) totalSeats;
    	
    	System.out.printf("Percentage: %.2f%c%n", percentage, '%');
    	System.out.printf("Current income: $%d%n", currentIncome);
    	System.out.printf("Total income: $%d%n", totalIncome);
    	
    	
    	
    }
    
}
