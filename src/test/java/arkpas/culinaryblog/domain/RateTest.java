package arkpas.culinaryblog.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class RateTest {

    private Rate rate;

    @Before
    public void setup () {
        rate = new Rate();
    }

    @Test
    public void addUserRate () {

        //set should be initially empty
        assertTrue(rate.getUserRates().isEmpty());

        UserRate userRate = null;
        rate.addUserRate(userRate);

        //set should not save null elements
        assertTrue(rate.getUserRates().isEmpty());

        userRate = new UserRate();
        rate.addUserRate(userRate);

        //set should have 1 element now
        assertEquals(1, rate.getUserRates().size());

        //added element should have reference to his parent
        assertEquals(rate, userRate.getRate());
    }

    @Test
    public void calculateRating () {
        //votes and rating should be initially 0
        assertEquals(0, rate.getVotes());
        assertEquals(0, rate.getRating(), 0.01);


        rate.calculateRating();
        //votes and rating should be 0 after method call when there is no elements in set
        assertEquals(0, rate.getVotes());
        assertEquals(0, rate.getRating(), 0.01);

        UserRate sample = new UserRate();
        sample.setRateValue(4);
        rate.addUserRate(sample);

        sample = new UserRate();
        sample.setRateValue(4);
        rate.addUserRate(sample);

        sample = new UserRate();
        sample.setRateValue(2);
        rate.addUserRate(sample);

        rate.calculateRating();
        //votes should be set to proper value
        assertEquals(3, rate.getVotes());
        //rating should be calculated properly
        assertEquals(3.33, rate.getRating(), 0.01);


    }


    //collection from getter should be unmodifiable
    @Test (expected = UnsupportedOperationException.class)
    public void checkIfUnmodifiable () {
        Set<UserRate> set = rate.getUserRates();
        set.clear();
    }
}
