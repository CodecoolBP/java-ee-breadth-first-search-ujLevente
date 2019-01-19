package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import java.util.*;

public class BFSimpl {

    private Queue<UserNode> toVisit;
    private Set<UserNode> alreadyVisited;
    private final UserNode depthMarker = null;
    private int depthCounter;

    private void BFSinit(UserNode sourceNode) {
        toVisit = new LinkedList<>();
        alreadyVisited = new HashSet<>();
        depthCounter = 0;
        toVisit.add(sourceNode);
    }

    private void fillQueueWithAdjacentNodes(UserNode userNode) {
        userNode.getFriends().forEach(friend -> {
            if (!alreadyVisited.contains(friend))
                toVisit.add(friend);
        });
    }

    public int getDistanceBetweenTwoUser(UserNode u1, UserNode u2) {

        if (u1 == null || u2 == null) throw new IllegalArgumentException("user not defined");

        BFSinit(u1);
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
            fillQueueWithAdjacentNodes(current);
        }

        System.out.println("no path found.");
        return -1;
    }

    public Set<UserNode> getFriendsInGivenDepth(UserNode sourceUser, int depth) {
        if (sourceUser == null) throw new IllegalArgumentException("source node not defined");
        if (depth < 0) throw new IllegalArgumentException("depth should not be negative");

        BFSinit(sourceUser);
        toVisit.add(depthMarker);

        while (!toVisit.isEmpty()) {
            UserNode current = toVisit.remove();

            if (current == depthMarker) {

                if (depthCounter == depth) {
                    alreadyVisited.remove(sourceUser);
                    return alreadyVisited;
                }

                toVisit.add(depthMarker);
                depthCounter++;
                continue;
            }

            alreadyVisited.add(current);
            fillQueueWithAdjacentNodes(current);
        }

        System.out.println("no friends found");
        return new HashSet<>();
    }

}
