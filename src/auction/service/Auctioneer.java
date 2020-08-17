package auction.service;

import auction.domain.Auction;
import auction.domain.Bid;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Auctioneer {

    private double highestOfAll = Double.NEGATIVE_INFINITY;
    private double lowestOfAll = Double.POSITIVE_INFINITY;
    private double average = 0;
    private List<Bid> threeHighest;

    public void evaluate(Auction auction){

        double total = 0;
        for(Bid bid : auction.getBids()){
            if(bid.getValue() > highestOfAll)highestOfAll = bid.getValue();
            if(bid.getValue() < lowestOfAll) lowestOfAll = bid.getValue();
            total += bid.getValue();
        }
        if(total == 0) {
            average = 0;
            return;
        }

        average = total/auction.getBids().size();

        threeHighest = new ArrayList<Bid>(auction.getBids());

        Collections.sort(threeHighest, new Comparator<Bid>() {
            @Override
            public int compare(Bid o1, Bid o2) {
                if(o1.getValue() < o2.getValue()) return 1;
                if(o1.getValue() > o2.getValue()) return -1;
                return 0;
            }
        });

        threeHighest = threeHighest.subList(0, threeHighest.size() > 3 ? 3 : threeHighest.size());
    }

    public double getHighestBid() {
        return highestOfAll;
    }

    public double getLowestBid() {
        return lowestOfAll;
    }

    public double getAverage() {
        return average;
    }

    public List<Bid> getThreeHighest() {
        return threeHighest;
    }
}
