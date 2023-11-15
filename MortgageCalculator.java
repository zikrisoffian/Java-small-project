package com.MuhammadZikri;

import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {
    public static void main(String[] args) {
        // set the final integer for months and percent
        final int MONTH_IN_YEAR = 12;
        final int PERCENT = 100;

        // get principal amount
        Scanner scanner = new Scanner(System.in);
        int principal = 0;
        while (true) {
            System.out.print("Principal: ");
            principal = scanner.nextInt();
            if (principal < 10_000 || principal > 10_000_000)
                System.out.println("Enter amount between $10,000 and $10,000,000");
            else
                break;
        }

        // get annualInterest
        double annualInterest = 0;
        while (true) {
            System.out.print("Annual Interest Rate: ");
            annualInterest = scanner.nextDouble();
            if (annualInterest <= 0 || annualInterest > 30)
                System.out.println("Enter a value greeater than 1 and less than or equal to 30");
            else
                break;
        }

        // get annualPeriod
        int period = 0;
        while (true) {
            System.out.print("Period (years) : ");
            period = scanner.nextInt();
            if (period <= 0 || period > 40)
                System.out.println("Enter value between 1 and 30 (years)");
            else
                break;
        }

        double monthlyInterest = annualInterest / PERCENT / MONTH_IN_YEAR;
        int monthlyPeriod = period * MONTH_IN_YEAR;

        double mortgageByMonth = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, monthlyPeriod))
                / (Math.pow(1 + monthlyInterest, monthlyPeriod) - 1);

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String mortgageFormatted = currency.format(mortgageByMonth);
        System.out.println("Monthly payment by month: " + mortgageFormatted);

        double totalLoan = principal * monthlyInterest * monthlyPeriod
                / (1 - Math.pow(1 + monthlyInterest, -monthlyPeriod));
        String totalLoanFormatted = currency.format(totalLoan);
        System.out.println("Total loan: " + totalLoanFormatted);

        NumberFormat percent = NumberFormat.getPercentInstance();
        double interestPercentage = (totalLoan - principal) / totalLoan;
        String interestPercentageFormatted = percent.format(interestPercentage);
        System.out.println("Interest Percentage: " + interestPercentageFormatted);

        double principalPercentage = principal / totalLoan;
        String principalPercentageFormatted = percent.format(principalPercentage);
        System.out.println("Principal Percentage: " + principalPercentageFormatted);
    }
}
