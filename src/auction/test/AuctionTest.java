package auction.test;

import auction.domain.Auction;
import auction.domain.Bid;
import auction.domain.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class AuctionTest {

    @Test
    public void shouldReceiveOneBid(){

        Auction auction = new Auction("Macbook Pro 15");
        assertEquals(0, auction.getBids().size());

        auction.propose(new Bid(new User("Jhon"), 2000.0));
        assertEquals(1, auction.getBids().size());
        assertEquals(2000.0, auction.getBids().get(0).getValue(), 0.0001);
    }

    @Test
    public void shouldReceiveMultipleBids(){

        Auction auction = new Auction("Macbook Pro 15");

        auction.propose(new Bid(new User("Jhon"), 2000.0));
        auction.propose(new Bid(new User("Mark"), 3000.0));

        assertEquals(2, auction.getBids().size());
        assertEquals(2000.0, auction.getBids().get(0).getValue(), 0.0001);
        assertEquals(3000.0, auction.getBids().get(1).getValue(), 0.0001);
    }
}