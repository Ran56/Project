package com.ascending.training;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ABC{

    private static Logger logger = LoggerFactory.getLogger(ABC.class);


  //public void study(){ logger.info("this is study of ABC");}
//
//
//  int learning()
//  {
//      return 1;
//  }
//
//
//    public String toString()
//    {
//      //  logger.info("this is toString method");
//        return "this is toString method";
//    }

    public static void main(String[] args) {
            ABC abc = new ABC();
            logger.info(""+abc);



    }

}
