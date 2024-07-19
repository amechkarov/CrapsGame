package com.gangames.crapsgame.utils.randomprovider;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomProviderImpl implements RandomProvider {

    private Random random = new Random();


    @Override
    public int roll() {
        return random.nextInt(6) + random.nextInt(6);
    }
}
