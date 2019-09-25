package arkpas.culinaryblog.domain;

import javax.persistence.*;

@Entity
public class UserRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne (cascade = {}, fetch = FetchType.EAGER, targetEntity = UserDetails.class)
    @JoinColumn (name = "user_id")
    private UserDetails userDetails;

    @ManyToOne (cascade = {}, fetch = FetchType.EAGER, targetEntity = Rate.class)
    @JoinColumn (name = "rate_id")
    private Rate rate;

    private int rateValue;

    public int getId() {
        return id;
    }
    public UserDetails getUserDetails() {
        return userDetails;
    }
    public Rate getRate() {
        return rate;
    }
    public int getRateValue() {
        return rateValue;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
    public void setRate(Rate rate) {
        this.rate = rate;
    }
    public void setRateValue(int rateValue) {
        this.rateValue = rateValue;
    }
}
