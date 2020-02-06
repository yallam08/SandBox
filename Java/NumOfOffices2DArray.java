/*
You are the owner of a coworking space like WeWork and your office building is rectangular.
Your team just built wall partitions to create mini offices for startups.
This office campus is represented by a 2D array of 1s (floor spaces) and 0s (walls).
Each point on this array is a one foot by one foot square.
You need to calculate the number of offices. A single office is bordered by walls and is constructed by placing floors next to each other,
horizontally and/or vertically.
Two 1s adjacent to each other horizontally or vertically are always part of the same office.
Function numOffices() has one parameter: grid - a 2D grid/array of 1s and 0s

In this problem, our input format is as follows: 
The first line is the number of rows in the 2D array. 
The second line is the number of columns in the 2D array.
The rest of the input contains the data to be processed.

Here is an example of the raw input:
4 
5 
11110 
11010 
11000 
00000

Expected: Output returns the number of valid offices in the grid.

Constraints:
Assume all four edges of the grid are all surrounded by walls. 
Assume that the bounds of the array are the following: 
The total amount of elements in the array: width x height <= 10^6

Example numOffices() Input
4
5
11110
00000
00100
00011

Example Output
3

Solution:
There's 3 offices in this grid, one made of four 1s on the top left, 
one made of one 1 in the middle, and one made of two 1s in the bottom right.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class NumOfOffices2DArray {
    private static String[][] getMatrix() {
        Scanner sc = new Scanner(System.in);
        int rowsLen = sc.nextInt();
        int colsLen = sc.nextInt();
        String[][] matrix = new String[rowsLen][colsLen];
        int i;
        for (i = 0; i < rowsLen; i++) {
            String line = sc.next();
            for (int j = 0; j < line.length(); j++) {
                matrix[i][j] = String.valueOf(line.charAt(j));
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int result = Solution.numOffices(getMatrix());
        System.out.println(result);
    }
}

class Solution {
    static int numOffices(String[][] grid) {
        List<Office> offices = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].equals("0")) continue;

                Office.Space s = new Office.Space(i, j);
                Office o = getOfficeThatContainsNeighbours(offices, s, grid);
                if (o == null) {
                    Office newOffice = new Office();
                    newOffice.addSpace(s);
                    offices.add(newOffice);
                } else {
                    o.addSpace(s);
                }
            }
        }

        return offices.size();
    }

    static Office getOfficeThatContainsNeighbours(List<Office> offices, Office.Space space, String[][] grid) {
        List<Office.Space> neighbours = new ArrayList<>();
        if (space.i < grid.length - 1 && grid[space.i + 1][space.j].equals("1"))
            neighbours.add(new Office.Space(space.i + 1, space.j));
        if (space.i > 0 && grid[space.i - 1][space.j].equals("1"))
            neighbours.add(new Office.Space(space.i - 1, space.j));
        if (space.j < grid[space.i].length - 1 && grid[space.i][space.j + 1].equals("1"))
            neighbours.add(new Office.Space(space.i, space.j + 1));
        if (space.j > 0 && grid[space.i][space.j - 1].equals("1"))
            neighbours.add(new Office.Space(space.i, space.j - 1));

        for (Office office : offices) {
            if (office.hasANeighbour(neighbours)) {
                return office;
            }
        }

        return null;
    }
}

class Office {
    private List<Space> spaces = new ArrayList<>();

    void addSpace(Space s) {
        this.spaces.add(s);
    }

    boolean hasANeighbour(List<Space> neighbours) {
        for (Space neighbour : neighbours) {
            for (Space s : spaces) {
                if (neighbour.i == s.i && neighbour.j == s.j)
                    return true;
            }
        }
        return false;
    }

    public static class Space {
        int i;
        int j;

        Space(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}

