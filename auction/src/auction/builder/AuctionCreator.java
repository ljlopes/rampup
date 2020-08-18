package auction.builder;

import auction.domain.Auction;
import auction.domain.Bid;
import auction.domain.User;

public class AuctionCreator {

    private Auction auction;

    public AuctionCreator to(String description){
        this.auction = new Auction(description);
        return this;
    }

    public AuctionCreator bid(User user, double value){
        auction.propose(new Bid(user, value));
        return this;
    }

    public Auction build(){
        return auction;
    }
}
