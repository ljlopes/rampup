package auction.test;

import auction.domain.Auction;
import auction.domain.Bid;
import auction.domain.User;
import auction.service.Auctioneer;

public class AuctioneerTest {

    public static void main(String[] args) {

        User jhon = new User("Jhon");
        User mark = new User("Mark");
        User ana = new User("Ana");

        Auction auction = new Auction("Playstation 5");

        auction.propose(new Bid(jhon, 1500.0));
        auction.propose(new Bid(mark, 2000.00));
        auction.propose(new Bid(ana, 4000.00));

        Auctioneer auctioneer = new Auctioneer();
        auctioneer.evaluate(auction);

        System.out.println(auctioneer.getHighestBid());
        System.out.println(auctioneer.getLowestBid());

    }
}
