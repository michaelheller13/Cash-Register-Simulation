/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sami Dunn and Michael Heller
 * Section: 11am
 * Date: 9/13/19
 * Time: 11:41 AM
 *
 * Project: csci205_labs
 * Package: lab06
 * Class: CashRegister
 *
 * Description:  Cash register class to implement a program that simulates registers at a store.
 *
 * ****************************************
 */

package lab06;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;

/**
 * Declares cash register object to simulate a register at a store.
 * @author Michael Heller and Sami Dunn
 * @version 0.2
 */
public class CashRegister {
    /** Name of the cash drawer */
    private String sName;

    /** Amount of cash in the drawer */
    private double cashInDrawer;

    /** Current transaction */
    private Transaction currentTransaction;

    /** Amount of money paid toward the current transaction */
    private double amountPaid;

    /** List of the price of every item in this transaction */
    private ArrayList<Transaction> transactionList;
    /** Tracks summary stats over all prices in this transaction */
    private DoubleSummaryStatistics dss;

    /**
     * Initialize a default, empty cash register
     * @return void
     */
    public CashRegister() {
        this.transactionList = new ArrayList<>();
        this.dss = new DoubleSummaryStatistics();
        this.cashInDrawer = 0;
        this.currentTransaction = null;
        this.amountPaid = 0;
        this.sName = "Default";
    }

    /**
     * Initialize an empty cash register with a specified name
     *
     * @param sName - the name of the register
     * @return void
     */
    public CashRegister(String sName) {
        this();
        this.sName = sName;
    }

    /**
     * Getter method for name
     * @return sName
     */
    public String getsName() {
        return sName;
    }

    /**
     * Setter method for name
     * @param sName
     * @return void
     */
    public void setName(String sName) {
        this.sName = sName;
    }

    /**
     * Getter method for transaction
     * @return currentTransaction
     */
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    /**
     * Checks if the register is currently in a transaction
     * @return true/false for in or out of transaction
     */
    public boolean isInTransaction() {
        if (currentTransaction == null)
            return false;
        else {
            return true;
        }

    }

    /**
     * Getter method for the amount paid
     * @return amountPaid
     */
    public double getAmountPaid() {
        return amountPaid;
    }

    /**
     * Returns amount owed in a transaction - returns 0 if not in a transaction
     * @return amount owed to register
     */
    public double getAmountOwed() {
        if (isInTransaction() == false)
            return 0.0;
        else
            return currentTransaction.getTotalCost() - amountPaid;
    }

    /**
     * Initializes amount of cash to be placed in register drawer
     * @return void
     */
    public void startShift(double initCash) {
        cashInDrawer = initCash;
    }

    /**
     * Initializes amount of cash to be placed in register drawer
     * @return -1 if in the middle of a transaction
     */
    public double finishShift() {
        double currCash = cashInDrawer;
        if (isInTransaction() == false) {
            printEndOfShiftResults();
            cashInDrawer = 0.0;
            return currCash;
        }
        else
            return -1;
    }

    /**
     * Checks the state of the register to see if a transaction should be started
     * @return true or false if transaction should be started
     */
    public boolean startTransaction() {
        if (cashInDrawer >= 0.0 && isInTransaction() == false) {
            currentTransaction = new Transaction();
            return true;
        }
        else
            return false;
    }
    /**
     * Checks to see if the transaction should be finished
     * @return true if nothing more is owed, false if transaction is still in progress
     */
    public boolean finishTransaction() {
        if (getAmountOwed() == 0.0 && isInTransaction() == true) {
            transactionList.add(currentTransaction);
            currentTransaction = null;
            amountPaid = 0.0;
            return true;
        }
        else
            return false;
    }

    /**
     * Adds the price of the item to the transaction price and increments the total amount of items
     * @param amount
     */
    public void scanItem(double amount) {
        currentTransaction.addItemToTransaction(amount);
    }

    /**
     * Collects payment for transaction
     * @return remaining amount owed
     */
    public double collectPayment(double value) {
        if (value > getAmountOwed()) {
            double returnVal = value - getAmountOwed();
            cashInDrawer += getAmountOwed();
            amountPaid += getAmountOwed();
            return returnVal;

        }
        else if (value == getAmountOwed()) {
            amountPaid += value;
            cashInDrawer += value;
            return 0.0;
        }
        else {
            amountPaid += value;
            cashInDrawer += value;
            return amountPaid - currentTransaction.getTotalCost();
        }
    }


    /**
     * Getter method for dss sum value
     * @return sum costs
     */
    public double getTotal() {
        double total = 0;
        for (int i = 0; i < transactionList.size(); i++) {
            total += transactionList.get(i).getTotalCost();
        }
        return total;
    }

    /**
     * Getter method for minimum transaction using dss
     * @return minimum transaction
     */
    public double getMinTransaction() {
        double min = transactionList.get(0).getTotalCost();
        for (int i = 0; i < transactionList.size(); i++) {
            if(transactionList.get(i).getTotalCost() < min)
                min = transactionList.get(i).getTotalCost();
        }
        return min;
    }

    /**
     * Getter method for the maximum transaction using dss
     * @return maximum cost
     */
    public double getMaxTransaction() {
        double max = transactionList.get(0).getTotalCost();
        for (int i = 1; i < transactionList.size(); i++) {
            if(transactionList.get(i).getTotalCost() > max)
                max = transactionList.get(i).getTotalCost();
        }
        return max;
    }

    /**
     * Getter method for average cost using dss
     * @return average transaction
     */
    public double getAveTransaction() {
        double average = getTotal() / transactionList.size();
        return average;
    }


    /**
     * prints string of values after simulation
     */
    @Override
    public String toString() {
        return  "CashRegister{" +
                "sName='" + sName + '\'' +
                ", cashInDrawer=" + cashInDrawer +
                ", currentTransaction=" + currentTransaction +
                ", amountPaid=" + amountPaid +
                ", transactionList=" + transactionList +
                ", dss=" + dss +
                '}';
    }

    /**
     * prints statistics for the shift that just concluded
     */
    public void printEndOfShiftResults() {
        String cash = String.format("%.2f", cashInDrawer);
        System.out.println("CASH IN REGISTER: $" + cash);
        System.out.println("TRANSACTIONS:");
        for (int i = 0; i < transactionList.size(); i++) {
            int k = i + 1;
            System.out.println(k + ": " + transactionList.get(i));
        }
        System.out.println("SUMMARY:");
        String min = String.format("%.2f", getMinTransaction());
        System.out.println("Min transaction: $" + min);
        String max = String.format("%.2f", getMaxTransaction());
        System.out.println("Max transaction: $" + max);
        String ave = String.format("%.2f", getAveTransaction());
        System.out.println("Average transaction: $" + ave);
        String total = String.format("%.2f", getTotal());
        System.out.println("TOTAL: $" + total);
        System.out.println("GOODBYE!");
    }

}
