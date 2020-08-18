package auction.service;

import auction.domain.Auction;
import auction.domain.Bid;

import javax.swing.text.html.StyleSheet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Auctioneer {

    private double highestOfAll = Double.NEGATIVE_INFINITY;
    private double lowestOfAll = Double.POSITIVE_INFINITY;
    private double average = 0;
    private List<Bid> highests;

    public void evaluate(Auction auction) {

        if (auction.getBids().size() == 0) {
            throw new RuntimeException("It is not possible evaluate a auction without bids!");
        }

        double total = 0;

        for (Bid bid : auction.getBids()) {
            if (bid.getValue() > highestOfAll) highestOfAll = bid.getValue();
            if (bid.getValue() < lowestOfAll) lowestOfAll = bid.getValue();
            total += bid.getValue();
        }

        average = total / auction.getBids().size();

        ThreeHighest(auction);
    }

    private void ThreeHighest(Auction auction) {

        highests = new ArrayList<Bid>(auction.getBids());

        Collections.sort(highests, new Comparator<Bid>() {
            @Override
            public int compare(Bid o1, Bid o2) {
                if (o1.getValue() < o2.getValue()) return 1;
                if (o1.getValue() > o2.getValue()) return -1;
                return 0;
            }
        });

        highests = highests.subList(0, highests.size() > 3 ? 3 : highests.size());
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
        return highests;
    }
}
