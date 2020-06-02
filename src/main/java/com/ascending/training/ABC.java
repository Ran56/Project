package com.ascending.training;

public class ABC extends App{
    public void methodImple()
    {

    }


    protected int A = 28;
    int a[] = {9,9,0,9,1,-9,9,9};
    int[] a1 = new int[a.length+1];
    public static void main( String[] args )

    {
   ABC abc =new ABC();

       /*   int i = abc.GetPivot(a);
    System.out.println(i);
    System.out.println(Math.log(16));*/

    abc.plusOne(abc.a);
    if(abc.a1[0] == 0)
    for(int i : abc.a)
        System.out.print(i+" ");
    else
    for(int i : abc.a1)
        System.out.print(i+" ");

    }

    public int GetPivot(int[] a)
    {

        for(int center = 0; center < a.length ; center++)
        {
            if(center == 0) {
                if (GetLeftSum(a, center + 1, a.length - 1) == 0)
                return center;
            }
            if(center == a.length-1)
            {
                if (GetRightSum(a,center-1) == 0)
                return center;
            }

            if(center !=0 && center != a.length-1) {
                if (GetRightSum(a, center - 1) == GetLeftSum(a, center+ 1, a.length - 1))
                    return center;
            }
        }

        return -1;
    }

    public int GetRightSum(int[]a, int r)
    {
        int sum = 0;
        for(int i = 0; i<= r; i++)
        {
            sum += a[i];
        }
        return sum;
    }

    public int GetLeftSum(int[]a, int c, int l)
    {
        int sum = 0;
        for(int i = c; i<= l; i++)
        {
            sum += a[i];
        }
        return sum;
    }


    public int[] plusOne(int[] a)
    {
        if(a != null) {
            int i = a.length - 1;
            a[i] = (a[i] + 1)%10;
        while(a[i] == 0)
        {
            if(i-1>=0) {
                a[i - 1] = (a[i - 1] + 1)%10;
                i--;
            }
            else if(a[0] == 0)
            {
                a1[0] = 1;
                return a1;
            }
            else break;
        }
        }
        return a;
    }





}
