package auction.service;

import auction.domain.Auction;
import auction.domain.Bid;

public class Evaluator {
    private double biggestOfAll = Double.NEGATIVE_INFINITY;

    public void evaluate(Auction auction){
        for(Bid bid : auction.getBids()){
            if(bid.getValue() > biggestOfAll)biggestOfAll = bid.getValue();
        }
    }

    public double getBiggestOfAll() {
        return biggestOfAll;
    }
}
