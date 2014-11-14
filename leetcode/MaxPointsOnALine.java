/*
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */

import java.util.*;


public class MaxPointsOnALine {
	/**
	 * Definition for a point.
	 */
	public static class Point {
		int x;
		int y;
		Point() { x = 0; y = 0; }
		Point(int a, int b) { x = a; y = b; }
	}
	
	
	/* O(n^2) solution. We find all the combination of points, store different slops into HashMap
	 * as the key, and count the number of each slop as values . With the fact that, for all points in a line,
	 * the slop of each two of them has to be the same.
	 */
	public int maxPoints(Point[] points) {
		if (points == null || points.length == 0) {
            return 0;
        }
		
		int max = 1;
        /* Calculate all slops */
		HashMap<Double, Integer> slops = new HashMap<>();
		for (int i = 0; i < points.length; i++) {
			if (isContain(points[i])) {
				continue;
			}
			for (int j = 0; j < points.length; j++) {
				if (isContain(points[j]) || arePointsEqual(points[i], points[j])) {
					continue;
				}
				double slop = calSlop(points[i], points[j]);
				System.out.println(slop);
				if (slops.containsKey(slop)) {
					slops.put(slop, slops.get(slop) + 1);
				} else {
					slops.put(slop, 1);
				}
			}
			
			System.out.println(slops);
			if (slops.isEmpty()) {
				max = Math.max(max, 1);
				continue;
			}

			Iterator<Integer> iter = slops.values().iterator();
			while (iter.hasNext()) {
				int number = iter.next();
				max = Math.max(max, number+1);
			}
			
			slops.clear();
			singlePoints.add(hash(points[i]));
		}
		
		return max;
    }
	
	/* Single points */
	Set<Integer> singlePoints = new HashSet<Integer>();
	
	public boolean isContain(Point p) {
		return singlePoints.contains(hash(p));
	}
	
	public int hash(Point p) {
		return 3 * p.y + p.x;
	}
	
	public Boolean arePointsEqual(Point a, Point b) {
		return a.x == b.x ? a.y == b.y : false;
	}
	
	public double calSlop(Point a, Point b) {
		return (a.x - b.x) == 0 ? Integer.MAX_VALUE : (a.y - b.y) / (1.0 * (a.x - b.x)) + 0.0;
	}
	
	public static void main(String[] args) {
		/* Test1 (2,3), (3,3), (-5,3) */
		MaxPointsOnALine solution = new MaxPointsOnALine();
		Point p1 = new Point(2, 3), p2 = new Point(3, 3), p3 = new Point(-5, 3);
		Point[] points = {p1, p2 ,p3};
		System.out.println(solution.maxPoints(points));
		p1 = new Point(0, 0);
		p2 = new Point(0, 1);
		p3 = new Point(1, 1);
		Point p4 = new Point(2, 1);
		Point[] points2 = {p1, p2, p3, p4};
		System.out.println(solution.maxPoints(points2));
		p1 = new Point(1,1);
		p2 = new Point(1,1);
		p3 = new Point(1,1);
		Point[] points3 = {p1, p2, p3};
		System.out.println(solution.maxPoints(points3));
		p1 = new Point(3,1);
		p2 = new Point(12,3);
		p3 = new Point(3,1);
		p4 = new Point(-6,-1);
		Point[] points4 = {p1, p2, p3, p4};
		System.out.println(solution.maxPoints(points4));

	}
 }
