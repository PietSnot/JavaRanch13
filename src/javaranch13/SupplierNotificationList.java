/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaranch13;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Piet
 */
public class SupplierNotificationList {
    String vendor;
    public static void main(String... args) {
        Comparator<SupplierNotificationList> vendorComparator = (c1, c2) -> c1.vendor.compareToIgnoreCase(c2.vendor);
        List<SupplierNotificationList> list = new ArrayList<>();
        // fill list
        list.sort(vendorComparator);
        
    }
}



