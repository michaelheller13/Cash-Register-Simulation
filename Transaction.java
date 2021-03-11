/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sami Dunn and Michael Heller
 * Section: 11am
 * Date: 9/13/19
 * Time: 11:42 AM
 *
 * Project: csci205_labs
 * Package: lab06
 * Class: Transaction
 *
 * Description:  Class that will be implemented with CashRegister.  The Transaction class describes/simulates
 * the processes of a cash register.
 *
 * ****************************************
 */

package lab06;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;


/**
 * Initializes a transaction class to simulate the processes of a cash register.
 * @author Michael Heller and Sami Dunn -- UPDATED VERSION BY DR. KING
 * @version 0.2
 */
public class Transaction {

//    /** Number of items in the transaction */
//    private int numItems;
//
//    /**
//     * Transaction total
//     */
//    private double totalCost;


    /** List of the price of every item in this transaction */
    private ArrayList<Double> itemList;
    /** Tracks summary stats over all prices in this transaction */
    private DoubleSummaryStatistics dss;

    /**
     * A new transaction will have 0 items and 0 totalCost
     */
    public Transaction() {
        this.itemList = new ArrayList<>();
        this.dss = new DoubleSummaryStatistics();
    }



    /**
     * Add the price of a new item being purchased to this transaction
     * @param price the price of the item
     */
    public void addItemToTransaction(double price) {
        this.itemList.add(price);
        this.dss.accept(price);
    }


    /**
     * Getter method for a list of prices
     * @return itemList
     */
    public List<Double> getListOfPrices() { return itemList; }

    /**
     * Getter method for numItems
     * @return numItems
     */
    public long getNumItems() { return this.dss.getCount(); }

    /**
     * Getter method for total cost using dss
     * @return total cost
     */
    public double getTotalCost() { return this.dss.getSum(); }

    /**
     * Getter method for minimum cost using dss
     * @return minimum cost
     */
    public double getMinCost() { return this.dss.getMin(); }

    /**
     * Getter method for the maximum cost using dss
     * @return maximum cost
     */
    public double getMaxCost() { return this.dss.getMax(); }

    /**
     * Getter method for average cost using dss
     * @return average cost
     */
    public double getAveCost() { return this.dss.getAverage(); }

    /**
     * Shows the state of this Transaction instance
     * @return void
     */
    @Override
    public String toString() {
        return "Transaction{" +
                "itemList=" + itemList +
                ", count=" + dss.getCount() +
                ", totalCost=" + dss.getSum() +
                ", minCost=" + dss.getMin() +
                ", maxCost=" + dss.getMax() +
                ", aveCost=" + dss.getAverage() +
                '}';
    }
}