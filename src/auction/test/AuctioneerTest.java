package auction.test;

import auction.domain.Auction;
import auction.domain.Bid;
import auction.domain.User;
import auction.service.Auctioneer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AuctioneerTest {

    @Test
    public void shouldRecognizeBidsInAscendingOrder() {

        User jhon = new User("Jhon");
        User mark = new User("Mark");
        User ana = new User("Ana");

        Auction auction = new Auction("Playstation 5");

        auction.propose(new Bid(jhon, 1500.0));
        auction.propose(new Bid(mark, 2000.00));
        auction.propose(new Bid(ana, 4000.00));

        Auctioneer auctioneer = new Auctioneer();
        auctioneer.evaluate(auction);

        double highestExpected = 4000.00;
        double lowestExpected = 1500.00;

        assertEquals(highestExpected, auctioneer.getHighestBid(), 0.00001);
        assertEquals(lowestExpected, auctioneer.getLowestBid(), 0.00001);

    }

    @Test
    public void shouldCalculateAverageValueOfBids(){

        User jhon = new User("Jhon");
        User mark = new User("Mark");
        User ana = new User("Ana");

        Auction auction = new Auction("Headphone Bluetooth JBL");

        auction.propose(new Bid(jhon, 350.00));
        auction.propose(new Bid(mark, 100.00));
        auction.propose(new Bid(ana, 150.00));

        Auctioneer auctioneer = new Auctioneer();

        auctioneer.evaluate(auction);

        assertEquals( 200.00, auctioneer.getAverage(), 0.0001);
    }

    @Test
    public void shouldCalculateAverageValueOfZeroBids(){

        User jhon = new User("Jhon");

        Auction auction = new Auction("Iphone 11");

        Auctioneer auctioneer = new Auctioneer();

        auctioneer.evaluate(auction);

        assertEquals(0, auctioneer.getAverage(), 0.0001);
    }

    @Test
    public void shouldRecognizeAuctionWithJustOneBid(){

        User jhon = new User("Jhon");

        Auction auction = new Auction("Playstation 5");

        auction.propose(new Bid(jhon, 1000.0));

        Auctioneer auctioneer = new Auctioneer();
        auctioneer.evaluate(auction);

        assertEquals(1000.0, auctioneer.getHighestBid(), 0.0001);
        assertEquals(1000.0, auctioneer.getLowestBid(), 0.0001);

    }

    @Test
    public void shouldRecognizeThreeHighestBids(){

        User jhon = new User("Jhon");
        User ana = new User("Ana");

        Auction auction = new Auction("Iphone 11");
        auction.propose(new Bid(jhon, 2000.0));
        auction.propose(new Bid(ana, 3000.0));
        auction.propose(new Bid(jhon, 5000.0));
        auction.propose(new Bid(jhon, 4500.0));

        Auctioneer auctioneer = new Auctioneer();
        auctioneer.evaluate(auction);

        List<Bid> threeHighest = auctioneer.getThreeHighest();
        assertEquals(3, threeHighest.size());
        assertEquals(5000.0, threeHighest.get(0).getValue());
        assertEquals(4500.0, threeHighest.get(1).getValue());
        assertEquals(3000.0, threeHighest.get(2).getValue());

    }
}
