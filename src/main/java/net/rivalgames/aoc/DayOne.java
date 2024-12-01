package net.rivalgames.aoc;

import java.io.File;
import java.util.*;

/**
 * @author alfie
 * @project advent-of-code
 * @date 01/12/2024 / 10:39
 */

public class DayOne {

    public static void main(String[] args) {
        File file = new File("src/main/resources/day1.txt");

        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                leftList.add(Integer.valueOf(line[0]));
                rightList.add(Integer.valueOf(line[1]));
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Integer> sortedLeftList = leftList.stream().sorted().toList();
        List<Integer> sortedRightList = rightList.stream().sorted().toList();

        int total = 0;
        for (int i = 0; i < sortedLeftList.size(); i++)
            total += Math.abs(sortedLeftList.get(i) - sortedRightList.get(i));

        System.out.println(total);

        Map<Integer, Integer> similarities = calculateSimilarities(sortedLeftList, sortedRightList);

        int totalSimilarityScore = 0;
        for (int key : similarities.keySet())
            totalSimilarityScore += key * similarities.get(key);

        System.out.println(totalSimilarityScore);
    }

    public static Map<Integer, Integer> calculateSimilarities(List<Integer> leftList, List<Integer> rightList) {
        Map<Integer, Integer> toReturn = new HashMap<>();

        for (int left : leftList) {
            int count = 0;

            for (int right : rightList)
                if (left == right)
                    count++;

            toReturn.put(left, count);
        }

        return toReturn;
    }


}
