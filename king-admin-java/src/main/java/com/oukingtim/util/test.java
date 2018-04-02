package com.oukingtim.util;

/**
 * Created by chenjian on 2018/3/30.
 */

public class test {
    public static void main(String[] args) {
        int fun = fun(4);
        System.out.println(fun);
    }

    public static int fun(int i) {
        if (i == 1) {
            return 1;
        } else if (i <= 0) {
            return 0;
        } else {
            return fun(i - 1) + fun(i - 2);
        }
    }
}
