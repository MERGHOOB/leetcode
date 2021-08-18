package com.margub.splitwise.driver;

import com.margub.splitwise.SplitWise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String EXIT = "EXIT";
    public static final String EQUAL = "EQUAL";

    // you live with 3 friends
    // you: User1(id: 1)
    // Friends: user2 (id:2) and user3(id:3) user4(id:4)

    // electricity bill: 1000
    // go to the app and say you paid: 1000

    // select all other people
//    Input: u1 1000 4 u1 u2 u3 u4 EQUAL
    // for this transaction every one owes: 250

    // you buy something for u2 u3
    // Input: u1 1250 2 u2 u3 EXACT 370 880

//    Now, you go out with your flatmates and take your brother/sister along with you.
//    User4 pays and everyone splits equally. You owe for 2 people.
//            Input: u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20

//    For this transaction, User1 owes 480 to User4, User2 owes 240 to User4 and User3 owes 240 to User4.
//
//    The app should update the balances in each of the profiles accordingly.
//    User1 owes User4: 230 (250-480)
//    User2 owes User1: 620 (620+0)
//    User2 owes User4: 240 (0+240)
//    User3 owes User1: 1130 (1130+0)
//    User3 owes User4: 240 (0+240)


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String next = scanner.nextLine();
            if (next.equalsIgnoreCase(EXIT)) {
                break;
            } else if (next.startsWith("EXPENSE")) {
                //EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
                if (next.contains(EQUAL)) {
                    String[] s = next.split(" ");
                    String lander = s[1];
                    String money = s[2];
                    int userCount = Integer.parseInt(s[3]);
                    List<String> users = new ArrayList<>();
                    for(int i = 1; i<=userCount; i++) {
                        users.add(s[3 + i]);
                    }

                    SplitWise.getInstance().addEqualExpense(lander, users, money);
                } else if (next.contains("EXACT")) {
                    //EXPENSE u1 1250 2 u2 u3 EXACT 370 880
                    String [] s = next.split(" ");
                    String lander = s[1];
                    String money = s[2];
                    int userCount = Integer.parseInt(s[3]);
                    List<String> users = new ArrayList<>();
                    for(int i = 1; i<=userCount; i++) {
                        users.add(s[3+i]);
                    }
                    List<String> moneys = new ArrayList<>();
                    for(int i = 1; i<=userCount; i++) {
                        moneys.add(s[4+userCount+i]);
                    }
                    SplitWise.getInstance().addExactExpense(lander, users, moneys);

                } else if (next.contains("PERCENT")) {
                    //EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
                    String [] s = next.split(" ");
                    String lander = s[1];
                    String money = s[2];
                    int userCount = Integer.parseInt(s[3]);
                    List<String> users = new ArrayList<>();
                    for(int i = 1; i<=userCount; i++) {
                        users.add(s[3+i]);
                    }
                    List<String> percentages = new ArrayList<>();
                    for(int i = 1; i<=userCount; i++) {
                        percentages.add(s[4+userCount+i]);
                    }
                    SplitWise.getInstance().addPercentageExpense(lander, money, users, percentages);
                }



            } else if (next.startsWith("SHOW")) {
                String[] s = next.trim().split(" ");
                if (s.length == 2) {
                    SplitWise.getInstance().show(s[1]);
                } else {
                    SplitWise.getInstance().show();
                }
            }
        }
    }

}
