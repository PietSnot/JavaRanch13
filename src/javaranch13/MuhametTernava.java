/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaranch13;

import java.util.Scanner;

/**
 *
 * @author Piet
 */
public class MuhametTernava {
    public static void main(String [] args)
    {
         
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you like to write your personal information: Yes or No ? ");
        String answer = sc.nextLine();
 
        if (answer.equalsIgnoreCase("yes"))
        {
 
            System.out.println("Name: ");
            String name = sc.nextLine();
            System.out.println("Surname: ");
            String surname = sc.nextLine();
            System.out.println("Your name is: "+name+" and your surname is: "+surname);
 
          }
 
          else{
           System.out.println("Good day.");
          }
    } 
}
