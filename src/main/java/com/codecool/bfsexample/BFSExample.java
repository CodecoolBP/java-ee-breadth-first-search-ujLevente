package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import java.util.*;

public class BFSExample {

    private static List<UserNode> users;

    private static GraphPlotter graphPlotter;

    private static void populateDB() {

        RandomDataGenerator generator = new RandomDataGenerator();
        users = generator.generate();
        graphPlotter = new GraphPlotter(users);
        graphPlotter.highlightRoute(Arrays.asList(Arrays.asList(users.get(0), users.get(2))));
        System.out.println("Done!");
    }

    public static UserNode getRandomUser() {
        Random ran = new Random();
        return users.get(ran.nextInt(users.size()));
    }

    public static void main(String[] args) {
        populateDB();
        BFSimpl bfs = new BFSimpl();
        System.out.println(bfs.getDistanceBetweenTwoUser(users.get(0), users.get(2)));
        System.out.println(bfs.getFriendsInGivenDepth(users.get(2), 1));
    }
}
