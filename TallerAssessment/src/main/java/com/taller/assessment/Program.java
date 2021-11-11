package com.taller.assessment;

import org.paukov.combinatorics3.Generator;
import org.paukov.combinatorics3.IGenerator;

import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Program {
    private List<List<Integer>> getPossibleIndexes (int maxArray) {

        List<Integer> values = IntStream.range(0,maxArray).mapToObj(i->i).collect(Collectors.toList());

        return Generator.combination(values)
                .simple(4)
                .stream().collect(Collectors.toList());
    }
    private List<Integer[]> generateAllQuadruplets(int[] array) {
        List<Integer[]> result = new ArrayList<>();

        List<List<Integer>> indexes = getPossibleIndexes(array.length);
        for(List<Integer> index : indexes) {
            Integer[] quadruplets = new Integer[4];
            for(int i = 0; i < index.size(); i++) {
                quadruplets[i] = array[index.get(i)];
            }
            result.add(quadruplets);
        }
        return result;
    }
    private List<Integer[]> filterWithTarget(List<Integer[]> quadruplets, int targetSum) {
        List<Integer[]> result = new ArrayList<>();
        for(Integer[] values : quadruplets) {
            if(Arrays.stream(values).mapToInt(Integer::intValue).sum() == targetSum) {
                result.add(values);
            }
        }
        return result;
    }
    private static String printValues(List<Integer[]> values) {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for(Integer [] value : values) {
            if(!first) {
                sb.append(",");
            }
            sb.append("{");
            boolean valueFirst = true;
            for(Integer i : value) {
                if(!valueFirst) {
                    sb.append(", ");
                }
                sb.append(i);
                valueFirst = false;
            }
            sb.append("}");
            first = false;
        }
        sb.append("]");
        return sb.toString();
    }
    public static List<Integer[]> fourNumberSum(int[] array, int targetSum) {
        Program program = new Program();
        List<Integer[]> quadruplets = program.generateAllQuadruplets(array);
        return program.filterWithTarget(quadruplets, targetSum);
    }

    public static void main(String[] args) {

        System.out.println("Test Case 1\n" +
                "        {\n" +
                "            \"array\": [7, 6, 4, -1, 1, 2],\n" +
                "            \"targetSum\": 16\n" +
                "        }" +
                "Output Expected\n" +
                "                [\n" +
                "  [7, 6, 4, -1],\n" +
                "  [7, 6, 1, 2]\n" +
                "]");
        List<Integer[]> result = fourNumberSum(new int[]{7, 6, 4, -1, 1, 2}, 16);
        if(printValues(result)
                .equals("[{7, 6, 4, -1},{7, 6, 1, 2}]")) {
            System.out.println("Test Case 1 - Passed");
        } else {
            System.out.print("Test Case 1 - Failed - ");
            System.err.println(printValues(result));
        }
        System.out.println(" ------------------------");
        System.out.println(" Test Case 2\n" +
                "        {\n" +
                "            \"array\": [1, 2, 3, 4, 5, 6, 7],\n" +
                "            \"targetSum\": 10\n" +
                "        }\n" +
                "        Output Expected\n" +
                "                [\n" +
                "  [1, 2, 3, 4]\n" +
                "]");
        result = fourNumberSum(new int[]{1, 2, 3, 4, 5, 6, 7}, 10);
        if(printValues(result)
                .equals("[{1, 2, 3, 4}]")) {
            System.out.println("Test Case 2 - Passed");
        } else {
            System.out.print("Test Case 2 - Failed - ");
            System.err.println(printValues(result));
        }
        System.out.println(" ------------------------");
        System.out.println(" Test Case 3\n" +
                "        {\n" +
                "            \"array\": [5, -5, -2, 2, 3, -3],\n" +
                "            \"targetSum\": 0\n" +
                "        }\n" +
                "        Output Expected\n" +
                "                [\n" +
                "  [5, -5, -2, 2],\n" +
                "  [5, -5, 3, -3],\n" +
                "  [-2, 2, 3, -3]\n" +
                "]");
        result = fourNumberSum(new int[]{5, -5, -2, 2, 3, -3}, 0);
        if(printValues(result)
                .equals("[{5, -5, -2, 2},{5, -5, 3, -3},{-2, 2, 3, -3}]")) {
            System.out.println("Test Case 3 - Passed");
        } else {
            System.out.print("Test Case 3 - Failed - ");
            System.err.println(printValues(result));
        }
        System.out.println(" ------------------------");
        System.out.println(" Test Case 4\n" +
                "        {\n" +
                "            \"array\": [-2, -1, 1, 2, 3, 4, 5, 6, 7, 8, 9],\n" +
                "            \"targetSum\": 4\n" +
                "        }\n" +
                "        Output\n" +
                "                [\n" +
                "  [-2, -1, 1, 6],\n" +
                "  [-2, 1, 2, 3],\n" +
                "  [-2, -1, 2, 5],\n" +
                "  [-2, -1, 3, 4]\n" +
                "]");
        result = fourNumberSum(new int[]{-2, -1, 1, 2, 3, 4, 5, 6, 7, 8, 9}, 4);
        if(printValues(result)
                .equals("[{-2, -1, 1, 6},{-2, -1, 2, 5},{-2, -1, 3, 4},{-2, 1, 2, 3}]")) {
            System.out.println("Test Case 4 - Passed");
        } else {
            System.out.print("Test Case 4 - Failed - ");
            System.err.println(printValues(result));
        }
        System.out.println(" ------------------------");
        System.out.println(" Test Case 5\n" +
                "        {\n" +
                "            \"array\": [-1, 22, 18, 4, 7, 11, 2, -5, -3],\n" +
                "            \"targetSum\": 30\n" +
                "        }\n" +
                "        Output\n" +
                "                [\n" +
                "  [-1, 22, 7, 2],\n" +
                "  [22, 4, 7, -3],\n" +
                "  [-1, 18, 11, 2],\n" +
                "  [18, 4, 11, -3],\n" +
                "  [22, 11, 2, -5]\n" +
                "]");
        result = fourNumberSum(new int[]{-1, 22, 18, 4, 7, 11, 2, -5, -3}, 30);
        if(printValues(result)
                .equals("[{-1, 22, 7, 2},{-1, 18, 11, 2},{22, 4, 7, -3},{22, 11, 2, -5},{18, 4, 11, -3}]")) {
            System.out.println("Test Case 5 - Passed");
        } else {
            System.out.print("Test Case 5 - Failed - ");
            System.err.println(printValues(result));
        }
        System.out.println(" ------------------------");
        System.out.println(" Test Case 6\n" +
                "        {\n" +
                "            \"array\": [-10, -3, -5, 2, 15, -7, 28, -6, 12, 8, 11, 5],\n" +
                "            \"targetSum\": 20\n" +
                "        }\n" +
                "        Output\n" +
                "                [\n" +
                "  [-5, 2, 15, 8],\n" +
                "  [-3, 2, -7, 28],\n" +
                "  [-10, -3, 28, 5],\n" +
                "  [-10, 28, -6, 8],\n" +
                "  [-7, 28, -6, 5],\n" +
                "  [-5, 2, 12, 11],\n" +
                "  [-5, 12, 8, 5]\n" +
                "]");
        result = fourNumberSum(new int[]{-10, -3, -5, 2, 15, -7, 28, -6, 12, 8, 11, 5}, 20);
        if(printValues(result)
                .equals("[{-10, -3, 28, 5},{-10, 28, -6, 8},{-3, 2, -7, 28},{-5, 2, 15, 8},{-5, 2, 12, 11},{-5, 12, 8, 5},{-7, 28, -6, 5}]")) {
            System.out.println("Test Case 6 - Passed");
        } else {
            System.out.print("Test Case 6 - Failed - ");
            System.err.println(printValues(result));
        }
        System.out.println(" ------------------------");
        System.out.println(" Test Case 7\n" +
                "        {\n" +
                "            \"array\": [1, 2, 3, 4, 5],\n" +
                "            \"targetSum\": 100\n" +
                "        }\n" +
                "        Output\n" +
                "[]");
        result = fourNumberSum(new int[]{1, 2, 3, 4, 5}, 100);
        if(printValues(result)
                .equals("[]")) {
            System.out.println("Test Case 7 - Passed");
        } else {
            System.out.print("Test Case 7 - Failed - ");
            System.err.println(printValues(result));
        }
        System.out.println(" ------------------------");
        System.out.println(" Test Case 8\n" +
                "        {\n" +
                "            \"array\": [1, 2, 3, 4, 5, -5, 6, -6],\n" +
                "            \"targetSum\": 5\n" +
                "        }\n" +
                "        Output\n" +
                "                [\n" +
                "  [2, 3, 5, -5],\n" +
                "  [1, 4, 5, -5],\n" +
                "  [2, 4, 5, -6],\n" +
                "  [1, 3, -5, 6],\n" +
                "  [2, 3, 6, -6],\n" +
                "  [1, 4, 6, -6]\n" +
                "]");
        result = fourNumberSum(new int[]{1, 2, 3, 4, 5, -5, 6, -6}, 5);
        if(printValues(result)
                .equals("[{1, 3, -5, 6},{1, 4, 5, -5},{1, 4, 6, -6},{2, 3, 5, -5},{2, 3, 6, -6},{2, 4, 5, -6}]")) {
            System.out.println("Test Case 8 - Passed");
        } else {
            System.out.print("Test Case 8 - Failed - ");
            System.err.println(printValues(result));
        }
    }

}
