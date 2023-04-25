package practice.AtmBackend.presentation;

import practice.AtmBackend.dao.Atmdao;
import practice.AtmBackend.dto.Atmdto;

import java.util.ArrayList;
import java.util.Scanner;

public class Atmpresentation {

    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        boolean status=true;
        while(status) {
            System.out.println("Welcome to the Atm machine");
            System.out.println("1.sign in");
            System.out.println("2.withdraw amount");
            System.out.println("3.check balance");
            System.out.println("4.Exit");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    signin();
                    break;
                case 2:
                    withdrawamt();
                    break;
                case 3:
                    checkbalance();
                    break;
                case 4:
                    status=false;
                    break;
            }
        }
    }
    private static void checkbalance() {
        System.out.println("Enter the acc no");
        String accno=sc.next();
        System.out.println("Enter the password");
        String pass=sc.next();
        Atmdto a1=new Atmdto();
        a1.setAccno(accno);
        a1.setPassword(pass);
        Atmdao a2=new Atmdao();
        ArrayList<Atmdto>list=a2.checkbalance(a1);
        for(Atmdto a3:list)
        {
            System.out.println(a3.getAmount());
        }
    }

    private static void withdrawamt() {
        int count=0;
        System.out.println("Enter the accno");
        String accno=sc.next();
        System.out.println("Enter the password");
        String pass=sc.next();
        System.out.println("Enter the withdraw amount");
        double amt=sc.nextDouble();
        Atmdto a1=new Atmdto();
        a1.setAccno(accno);
        a1.setPassword(pass);
        a1.setAmount(amt);
        Atmdao a2=new Atmdao();
       count= a2.withdrawamt(a1);
        System.out.println(count+" table is updated successfully");
    }

    private static void signin() {
        int count=0;
        System.out.println("Enter the id");
        int id=sc.nextInt();
        System.out.println("Enter the name of customer");
        String name=sc.next();
        System.out.println("Enter the Acc no of Bank");
        String accno=sc.next();
        System.out.println("Enter the account branch");
        String branch=sc.next();
        System.out.println("Add the amount inside the account");
        double amount=sc.nextDouble();
        System.out.println("Enter the password");
        String pass=sc.next();
        Atmdto a1=new Atmdto();
        a1.setId(id);
        a1.setUsername(name);
        a1.setAccno(accno);
        a1.setBranch(branch);
        a1.setAmount(amount);
        a1.setPassword(pass);
        Atmdao a2=new Atmdao();
       count=a2.signin(a1);
        System.out.println(count+" record is inserted successfully");

    }
}
