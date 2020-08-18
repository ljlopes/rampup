package auction.test;

import auction.builder.AuctionCreator;
import auction.domain.*;
import auction.service.Auctioneer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AuctioneerTest {

    private Auctioneer auctioneer;
    private User jhon;
    private User mark;
    private User ana;

    @BeforeEach
    public void setup() {
        this.auctioneer = new Auctioneer();
        this.jhon = new User("Jhon");
        this.mark = new User("Mark");
        this.ana = new User("Ana");
    }

    @Test
    public void shouldRecognizeBidsInAscendingOrder() {

        Auction auction = new AuctionCreator()
                .to("Playstation 5")
                .bid(jhon, 1500.00)
                .bid(mark, 2000.00)
                .bid(ana, 4000.00)
                .build();

        auctioneer.evaluate(auction);

        double highestExpected = 4000.00;
        double lowestExpected = 1500.00;

        assertEquals(highestExpected, auctioneer.getHighestBid(), 0.00001);
        assertEquals(lowestExpected, auctioneer.getLowestBid(), 0.00001);
    }

    @Test
    public void shouldCalculateAverageValueOfBids() {

        Auction auction = new AuctionCreator()
                .to("Headphone Bluetooth JBL")
                .bid(jhon, 350.00)
                .bid(mark, 100.00)
                .bid(ana, 150.00)
                .build();

        auctioneer.evaluate(auction);

        assertEquals(200.00, auctioneer.getAverage(), 0.0001);
    }
    
    @Test
    public void shouldRecognizeAuctionWithJustOneBid() {

        Auction auction = new AuctionCreator()
                .to("Playstation 5")
                .bid(jhon, 1000.0)
                .build();

        auctioneer.evaluate(auction);

        assertEquals(1000.0, auctioneer.getHighestBid(), 0.0001);
        assertEquals(1000.0, auctioneer.getLowestBid(), 0.0001);
    }

    @Test
    public void shouldRecognizeThreeHighestOfFiveBids() {

        Auction auction = new AuctionCreator()
                .to("Iphone 11")
                .bid(jhon, 2000.0)
                .bid(ana, 3000.0)
                .bid(jhon, 5000.0)
                .bid(mark, 4500.0)
                .bid(ana, 1000.0)
                .build();

        auctioneer.evaluate(auction);

        List<Bid> threeHighest = auctioneer.getThreeHighest();
        assertEquals(3, threeHighest.size());
        assertEquals(5000.0, threeHighest.get(0).getValue());
        assertEquals(4500.0, threeHighest.get(1).getValue());
        assertEquals(3000.0, threeHighest.get(2).getValue());
    }

    @Test
    public void shouldRecognizeRandomBids() {

        Auction auction = new AuctionCreator()
                .to("Drone")
                .bid(jhon, 200.0)
                .bid(mark, 450.0)
                .bid(jhon, 120.0)
                .bid(mark, 700.0)
                .bid(jhon, 630.0)
                .bid(mark, 230.0)
                .build();

        auctioneer.evaluate(auction);

        assertEquals(120.0, auctioneer.getLowestBid(), 0.0001);
        assertEquals(700.0, auctioneer.getHighestBid(), 0.0001);
    }

    @Test
    public void shouldRecognizeBidsInDescendingOrder() {

        Auction auction = new AuctionCreator()
                .to("Playstation 5")
                .bid(jhon, 400.0)
                .bid(mark, 200.00)
                .bid(ana, 100.00)
                .build();

        auctioneer.evaluate(auction);

        double highestExpected = 400.0;
        double lowestExpected = 100.0;

        assertEquals(highestExpected, auctioneer.getHighestBid(), 0.00001);
        assertEquals(lowestExpected, auctioneer.getLowestBid(), 0.00001);
    }

    @Test
    public void shouldRecognizeThreeHighestOfTwoBids() {

        Auction auction = new AuctionCreator()
                .to("Iphone 11")
                .bid(jhon, 1000.0)
                .bid(mark, 1800.0)
                .build();

        auctioneer.evaluate(auction);

        List<Bid> threeHighest = auctioneer.getThreeHighest();
        assertEquals(2, threeHighest.size());
        assertEquals(1800.0, threeHighest.get(0).getValue());
        assertEquals(1000.0, threeHighest.get(1).getValue());
    }

    @Test
    public void shouldNotEvaluateAuctionWithoutBids() {
        Auction auction = new AuctionCreator().to("Playstation 5").build();

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            auctioneer.evaluate(auction);
        });

        String expectedMessage = "It is not possible evaluate a auction without bids!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}


