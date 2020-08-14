package auction.service;

import auction.domain.Auction;
import auction.domain.Bid;

public class Auctioneer {

    private double highestOfAll = Double.NEGATIVE_INFINITY;
    private double lowestOfAll = Double.POSITIVE_INFINITY;
    private double average = 0;

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
}
