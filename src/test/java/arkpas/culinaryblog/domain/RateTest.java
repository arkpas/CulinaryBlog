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
    public void addUserRateShouldAddElementToSet () {
        rate.addUserRate(new UserRate());
        assertFalse(rate.getUserRates().isEmpty());
    }

    @Test
    public void addUserRateWithNullArgumentShouldNotAddElement () {
        rate.addUserRate(null);
        assertTrue(rate.getUserRates().isEmpty());
    }

    @Test
    public void addUserRateShouldAssignReferenceInArgumentObject () {
        UserRate userRate = new UserRate();
        rate.addUserRate(userRate);
        assertEquals(rate, userRate.getRate());
    }

    @Test
    public void calculateRatingCalledWithoutSettingValuesShouldNotProduceArtithmeticException () {
        rate.calculateRating();
    }

    @Test
    public void calculateRatingWithDataInSetShouldAssignValueToRatingField () {
        UserRate sample = new UserRate();
        sample.setRateValue(5);
        rate.addUserRate(sample);

        sample = new UserRate();
        sample.setRateValue(2);
        rate.addUserRate(sample);

        rate.calculateRating();
        assertEquals(3.5, rate.getRating(), 0.001);
    }

    @Test
    public void calculateRatingWithDataInSetShouldAssignValueToVotesField () {
        UserRate sample = new UserRate();
        sample.setRateValue(2);
        rate.addUserRate(sample);

        sample = new UserRate();
        sample.setRateValue(3);
        rate.addUserRate(sample);

        rate.calculateRating();
        assertEquals(2, rate.getVotes());
    }


    @Test (expected = UnsupportedOperationException.class)
    public void getUserRatesShouldReturnUnmodifiableSet () {
        Set<UserRate> set = rate.getUserRates();
        set.clear();
    }
}
