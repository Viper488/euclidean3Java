/**
 ** Java Program to implement Extended Euclid  Algorithm
 **/

import java.util.*;

/** Class ExtendedEuclid **/
public class ExtendedEuclid
{
    /** Function to solve **/
    public static Map<String, Long> gcd2(long a, long b)
    {
        long x = 0, y = 1, lastx = 1, lasty = 0, temp;
        while (b != 0)
        {
            long q = a / b;
            long r = a % b;

            a = b;
            b = r;

            temp = x;
            x = lastx - q * x;
            lastx = temp;

            temp = y;
            y = lasty - q * y;
            lasty = temp;
        }

        long finalY = a;
        long finalLastx = lastx;
        long finalLasty = lasty;
        return new HashMap<>() {
            {
                put("gcd", finalY);
                put("x", finalLastx);
                put("y", finalLasty);
            }
        };
    }

    public static Map<String, Long> gcd3(long a, long b, long c) {
        Map<String, Long> xy0 = gcd2(b,c);
        Map<String, Long> xy1 = gcd2(a,xy0.get("gcd"));
        //System.out.println("(b, c) = " + xy0.get("gcd") + "\nx = "+ xy0.get("x") +"\ny ="+ xy0.get("y"));
        //System.out.println("(a, (b, c)) = " + xy1.get("gcd") + "\nx = "+ xy1.get("x") +"\ny ="+ xy1.get("y"));
        return new HashMap<>() {
            {
                put("gcd", xy1.get("gcd"));
                put("x", xy1.get("x"));
                put("y", xy1.get("y") * xy0.get("x"));
                put("z", xy1.get("y") *  xy0.get("y"));
            }
        };
    }

    /** Main function **/
    public static void main (String[] args)
    {
        System.out.println("Extended Euclid Algorithm Test\n");
        /** Make an object of ExtendedEuclid class **/
        long a = 60;
        long b = 34;
        long c = 14;
        /** Call function solve of class ExtendedEuclid **/
        Map<String, Long> euclidean = gcd3(a, b, c);

        System.out.println("(a, b, c) = " + euclidean.get("gcd") + "\nx = "+ euclidean.get("x") +"\ny = "+ euclidean.get("y") +"\nz ="+ euclidean.get("z"));
        System.out.println(a + " * " + euclidean.get("x") + " + " + b  + " * " + euclidean.get("y") + " + "+ c + " * " + euclidean.get("z") + " = " + euclidean.get("gcd"));

        /**
         * Problem
         * We are looking for x, y, z and (a, b, c)
         * (a, b, c) = a * x + b * y + c * z
         *
         * Hint
         * (a, b, c) = (a, (b, c))
         *
         * Solution
         *
         * Step 1 - Lets find x0, y0 and (b, c)
         * (b, c) = b * x0 + c *y0
         *
         * Step 2 - Lets find x1, y1, and (a, (b, c))
         * (a, (b, c)) = a * x1 + (b, c) * y1
         *
         * Step 3
         * (a, b, c) = a * x1 + b * (x0 * y1) + c * (y0 * y1)
         *
         * Results
         *
         * (a, b, c) = (a, (b, c))
         * x = x1
         * y = x0 * y1
         * z = y0 * y1
         */
    }
}