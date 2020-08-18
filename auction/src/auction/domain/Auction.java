package auction.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Auction {

    private String description;
    private List<Bid> bids;

    public Auction(String description){
        this.description = description;
        this.bids = new ArrayList<Bid>();
    }

    public void propose(Bid bid){
        if(bids.isEmpty() || canBid(bid.getUser())) {
            bids.add(bid);
        }
    }

    private boolean canBid(User user) {
        return (!getLastBid().getUser().equals(user)) && getTotalOfBidsByUser(user) < 5;
    }

    private int getTotalOfBidsByUser(User user) {
        int total=0;

        for (Bid b: bids) {
            if(b.getUser().equals(user)) total++;
        }
        return total;
    }

    private Bid getLastBid() {
        return bids.get(bids.size() - 1);
    }

    public String getDescription(){
        return description;
    }

    public List<Bid> getBids(){
        return Collections.unmodifiableList(bids);
    }
}
