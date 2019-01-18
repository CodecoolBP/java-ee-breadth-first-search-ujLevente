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

    public static int getDistanceBetweenTwoUser(UserNode u1, UserNode u2) {

        if (u1 == null || u2 == null)
            throw new IllegalArgumentException("user not defined");

        Queue<UserNode> toVisit = new LinkedList<>();
        Set<UserNode> alreadyVisited = new HashSet<>();
        toVisit.add(u1);
        
        final UserNode depthMarker = null;
        int depthCounter = 0;
        toVisit.add(depthMarker);

        while (!toVisit.isEmpty()) {
            UserNode current = toVisit.remove();

            if (current == depthMarker) {
                toVisit.add(depthMarker);
                depthCounter++;
                continue;
            }

            if (current.equals(u2))
                return depthCounter;

            alreadyVisited.add(current);

            for (UserNode friend: current.getFriends()) {
                if (!alreadyVisited.contains(friend)) {
                    toVisit.add(friend);
                }
            }

            current.getFriends().forEach(friend -> {
                if (!alreadyVisited.contains(friend))
                    toVisit.add(friend);
            });

        }

        System.out.println("no path found.");
        return -1;
    }

    public static List<UserNode> getFriendsInGivenDistance(int distance) {

    }


    public static void main(String[] args) {
        populateDB();
//        Queue<UserNode> toVisit = new LinkedList<>();
//        toVisit.add(users.get(0));
//        toVisit.add(users.get(1));
//        toVisit.add(users.get(2));
//        System.out.println(toVisit);
//        System.out.println(toVisit.remove());
//        toVisit.add(users.get(3));
//        System.out.println(toVisit);
//        System.out.println(toVisit.remove());
//        System.out.println(getDistanceBetweenTwoUser(getRandomUser(), getRandomUser()));
        //System.out.println(getDistanceBetweenTwoUser(users.get(0), users.get(3)));
    }
}
