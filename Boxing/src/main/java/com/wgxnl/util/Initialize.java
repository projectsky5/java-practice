package com.wgxnl.util;

import com.wgxnl.entity.Box;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Initialize {

    private static final Initialize instance = new Initialize();

    private Initialize() {
    }

    public static Initialize getInstance() {
        return instance;
    }

    public List<Box> initialize() {
        List<Box> boxList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(15) + 1; i++) {
            boxList.add(new Box(random.nextDouble(16), random.nextDouble(16)));
        }

        return boxList;
    }
}
