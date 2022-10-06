package com.WAM;

import com.WAM.model.Admin;
import com.WAM.view.*;

public class Main {

    public static void main(String[] args) {
       LoginMenu loginMenu = LoginMenu.getInstance();
       loginMenu.show();
    }
}
