/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaranch13;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author Piet
 */
public class AmadeusPan {

    public static void main(String... args) {
        String date = "05051998";
        LocalDate locDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        locDate = LocalDate.parse(date, formatter);
        DateTimeFormatter out = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.US);
        System.out.println(locDate);
        System.out.println(locDate.format(out));
    }
}
