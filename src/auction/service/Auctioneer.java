package auction.service;

import auction.domain.Auction;
import auction.domain.Bid;

public class Auctioneer {

    private double highestOfAll = Double.NEGATIVE_INFINITY;
    private double lowestOfAll = Double.POSITIVE_INFINITY;

    public void evaluate(Auction auction){
        for(Bid bid : auction.getBids()){
            if(bid.getValue() > highestOfAll)highestOfAll = bid.getValue();
            if(bid.getValue() < lowestOfAll) lowestOfAll = bid.getValue();
        }
    }

    public double getHighestBid() {
        return highestOfAll;
    }

    public double getLowestBid() {
        return lowestOfAll;
    }
}
