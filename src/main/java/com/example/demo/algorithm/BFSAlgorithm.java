package com.example.demo.algorithm;

import org.springframework.lang.Nullable;

import java.util.*;

public class BFSAlgorithm implements SearchAlgorithm {
    private final Map<String, List<String>> graph;
    private final Set<String> visitedNodes;
    private final Queue<LinkedList<String>> queue;

    public BFSAlgorithm(Map<String, List<String>> graph){
        this.graph = graph;
        this.queue = new LinkedList<>();
        this.visitedNodes = new HashSet<>();
    }

    @Nullable
    public LinkedList<String> getRoute(String originNode, String destinationNode){
        if(Objects.equals(originNode, destinationNode)
                || !graph.containsKey(originNode)
                || !graph.containsKey(destinationNode)){
            return null;
        }

        visitedNodes.add(originNode);
        queue.add(new LinkedList<>(List.of(originNode)));

        while (!queue.isEmpty()){
            LinkedList<String> path = queue.poll();
            List<String> adjacentNodes = graph.get(path.getLast());

            for (String adjacentNode : adjacentNodes) {
                if(visitedNodes.contains(adjacentNode)){
                    continue;
                }

                if(destinationNode.equals(adjacentNode)){
                    path.add(adjacentNode);
                    return path;
                }

                LinkedList<String> currentPath = new LinkedList<>(path);
                currentPath.add(adjacentNode);

                queue.add(currentPath);
                visitedNodes.add(adjacentNode);
            }

        }

        return null;
    }
}
