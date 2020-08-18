package auction.test;

import auction.builder.AuctionCreator;
import auction.domain.*;
import auction.service.Auctioneer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuctionTest {

    private Auctioneer auctioneer;
    private User jhon;
    private User mark;

    @BeforeEach
    public void setup(){
        this.auctioneer = new Auctioneer();
        this.jhon = new User("Jhon");
        this.mark = new User("Mark");
    }

    @Test
    public void shouldReceiveOneBid(){

        Auction auction = new AuctionCreator()
                .to("Macbook Pro 15")
                .build();
        assertEquals(0, auction.getBids().size());

        auction.propose(new Bid(new User("Jhon"), 2000.0));

        assertEquals(1, auction.getBids().size());
        assertEquals(2000.0, auction.getBids().get(0).getValue(), 0.0001);
    }

    @Test
    public void shouldReceiveMultipleBids(){

        Auction auction = new AuctionCreator()
                .to("Macbook Pro 15")
                .bid(jhon, 2000.0)
                .bid(mark, 3000.0)
                .build();

        assertEquals(2, auction.getBids().size());
        assertEquals(2000.0, auction.getBids().get(0).getValue(), 0.0001);
        assertEquals(3000.0, auction.getBids().get(1).getValue(), 0.0001);
    }

    @Test
    public void shouldNotAllowTwoBidsInARowFromSameTheUser(){

        Auction auction = new AuctionCreator()
                .to("Macbook Pro 15")
                .bid(jhon, 2000.0)
                .bid(jhon, 3000.0)
                .build();

        assertEquals(1, auction.getBids().size());
        assertEquals(2000.0, auction.getBids().get(0).getValue(), 0.0001);
    }

    @Test
    public void shouldNotAllowMoreThanFiveBidsFromTheSameUser(){

        Auction auction = new AuctionCreator()
                .to("Macbook Pro 15")
                .bid(jhon, 2000.0)
                .bid(mark, 3000.0)
                .bid(jhon, 4000.0)
                .bid(mark, 5000.0)
                .bid(jhon, 6000.0)
                .bid(mark, 7000.0)
                .bid(jhon, 8000.0)
                .bid(mark, 9000.0)
                .bid(jhon, 10000.0)
                .bid(mark, 11000.0)
                .bid(jhon, 12000.0)
                .build();

        assertEquals(10, auction.getBids().size());
        assertEquals(11000.0, auction.getBids().get(auction.getBids().size() - 1).getValue(), 0.0001);
    }
}