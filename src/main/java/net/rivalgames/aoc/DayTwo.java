package net.rivalgames.aoc;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author alfie
 * @project advent-of-code
 * @date 02/12/2024 / 08:05
 */

public class DayTwo {

    public static void main(String[] args) {
        File file = new File("src/main/resources/day2.txt");
        int safeCount = 0;

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                int[] levels = Arrays.stream(line.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                if (!isSafe(levels))
                    continue;

                safeCount++;
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Safe count: " + safeCount);
    }

    public static boolean isSafe(int[] row) {
        if (checkSafety(row))
            return true;

        for (int i = 0; i < row.length; i++) {
            int[] modifiedRow = new int[row.length - 1];
            for (int j = 0, k = 0; j < row.length; j++) {
                if (j != i)
                    modifiedRow[k++] = row[j];
            }

            if (checkSafety(modifiedRow))
                return true;
        }

        return false;
    }

    private static boolean checkSafety(int[] row) {
        int[] inc = new int[row.length - 1];
        for (int i = 0; i < row.length - 1; i++)
            inc[i] = row[i + 1] - row[i];

        boolean allPositive = true;
        boolean allNegative = true;

        for (int diff : inc) {
            if (diff < 1 || diff > 3)
                allPositive = false;

            if (diff > -1 || diff < -3)
                allNegative = false;
        }

        return allPositive || allNegative;
    }

}
