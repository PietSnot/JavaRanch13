package javaranch13;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * zie we website. We spelen monopoly, en de vraag is welke drie velden
 * we het vaakst bezoeken, gegeven enkele bijzondere spelregels, en gegeven
 * dat we het spel spelen met twee dobbelstenen van elk VIER zijden...
 *
 * @author Piet
 */
public class Euler084 {
   public static void main(String... args) {
      new Euler084();
   }
   
   // variabelen
   
   ArrayList<Veld> bord;
   CCAndCH kansEnAlgemeenFonds;
   int positie;
   Dobbelsteen d1, d2;
   int aantal_worpen;
   int aantal_dubbels;
   final int MAX_WORPEN = 50_000_000;
   
   public Euler084() {
      positie = 0;
      aantal_worpen = 0;
      aantal_dubbels = 0;
      d1 = new Dobbelsteen(6);
      d2 = new Dobbelsteen(6);
      bord = createBord();
      kansEnAlgemeenFonds = new CCAndCH();
      ((CC)bord.get(2)).addKaarten(kansEnAlgemeenFonds);
      ((CC)bord.get(17)).addKaarten(kansEnAlgemeenFonds);
      ((CC)bord.get(33)).addKaarten(kansEnAlgemeenFonds);
      ((CH)bord.get(7)).addKaarten(kansEnAlgemeenFonds);
      ((CH)bord.get(22)).addKaarten(kansEnAlgemeenFonds);
      ((CH)bord.get(36)).addKaarten(kansEnAlgemeenFonds);
      
      // daar gaan we
      while (aantal_worpen < MAX_WORPEN) {
         aantal_worpen++;
         if (aantal_worpen % 100 == 0) {
            System.out.println("worp: " + aantal_worpen);
         }
         int worp1 = d1.werp();
         int worp2 = d2.werp();
         if (worp1 == worp2) {
            aantal_dubbels++;
            if (aantal_dubbels == 3) {
               aantal_dubbels = 0;
               positie = 10;
               bord.get(positie).turf();
               continue;
            }
         }
         else {
            aantal_dubbels = 0;
         }
         // hier gekomen   
         positie = (positie + worp1 + worp2) % 40;
         positie = bord.get(positie).action();
         bord.get(positie).turf();
      }
      
      // en sorteren
      Comparator<Veld> cv = new Comparator<Veld>() {
         @Override
         public int compare(Veld v, Veld w) {
            if (v.turf < w.turf) return 1;
            else if (v.turf > w.turf) return -1;
            else return 0;
         }
      };
      Collections.sort(bord, cv);
      for (Veld v: bord) {
         double perc = 100.0 * v.turf / MAX_WORPEN;
         System.out.format("pos: %2d   perc: %8.2f%n", v.positie, perc);
      }
         
   }
   
/******************************************************************
 * static methoden
 ******************************************************************/  
   public static ArrayList<Veld> createBord() {
      ArrayList<Veld> b = new ArrayList<>();
      b.add(new Start());
      b.add(new Straat("Dorpsstraat, Ons Dorp", 1));
      b.add(new CC("Algemeen Fonds", 2));
      b.add(new Straat("Brink, Ons Dorp", 3));
      b.add(new Belasting("Inkomstenbelasting", 4));
      b.add(new Station("Station Zuid", 5));
      b.add(new Straat("Steenstraat, Arnhem", 6));
      b.add(new CH("Kans", 7));
      b.add(new Straat("Ketelstraat, Arnhem", 8));
      b.add(new Straat("Velperplein, Arnhem", 9));
      b.add(new Gevangenis());
      b.add(new Straat("Barteljorisstraat, Haarlem", 11));
      b.add(new WaterEnStroom("Energiebdrijf", 12));
      b.add(new Straat("Zijlweg, Haarlem", 13));
      b.add(new Straat("Houtweg, Haarlem", 14));
      b.add(new Station("Station West", 15));
      b.add(new Straat("Neude, Utrecht", 16));
      b.add(new CC("Algemeen Fonds", 17));
      b.add(new Straat("Biltstraat, Utrecht", 18));
      b.add(new Straat("Vreeburg, Utrecht", 19));
      b.add(new VrijParkeren());
      b.add(new Straat("A-Kerkhof, Groningen", 21));
      b.add(new CH("Kans", 22));
      b.add(new Straat("Groote Markt, Groningen", 23));
      b.add(new Straat("Heerestraat, Groningen", 24));
      b.add(new Station("Station Noord", 25));
      b.add(new Straat("Spui, 's Gravenhage", 26));
      b.add(new Straat("Plein, ;s Gravenhage", 27));
      b.add(new WaterEnStroom("Waterbedrijf", 28));
      b.add(new Straat("Lange Poten", 29));
      b.add(new GoToJail());
      b.add(new Straat("Hofplein, Rotterdam", 31));
      b.add(new Straat("Blaak, Rotterdam", 32));
      b.add(new CC("Algemeen Fonds", 33));
      b.add(new Straat("Coolsingel, Rotterdam", 34));
      b.add(new Station("Station Oost", 35));
      b.add(new CH("Kans", 36));
      b.add(new Straat("Leidsestraat, Amsterdam", 37));
      b.add(new Belasting("Belasting", 38));
      b.add(new Straat("Kalverstraat, Amsterdam", 39));
      
      return b;
   }
/******************************************************************
     interne klassen
 ******************************************************************/
   enum Speciaal {
      Niks,
      AdvanceToGo,
      GoToJail,
      GoToC1,
      GoToE3,
      GoToH2,
      GoToR1,
      GoToNextR,
      GoToNextU,
      GoBack3
   }
   
   /***************************************************/
   abstract static class Veld {
      String naam;
      int positie;
      int turf;
      
      public Veld(String s, int pos) {
         naam = s;
         positie = pos;
         turf = 0;
      }
      
      abstract public int action();
      
      public void turf() {
         turf++;
      }
      
      @Override
      public boolean equals(Object other) {
         if ( ! (other instanceof Veld)) return false;
         Veld o = (Veld) other;
         return naam.equals(o.naam) && positie == o.positie;
      }

      @Override
      public int hashCode() {
         int hash = 3;
         hash = 47 * hash + Objects.hashCode(this.naam);
         hash = 47 * hash + this.positie;
         return hash;
      }
      
      @Override
      public String toString() {
         String s = naam + ", positie op bord: " + positie;
         return s;
      }
   }  // einde class Veld
   
   /***************************************************/
   static class Straat extends Veld {
      
      public Straat(String naam, int positie) {
         super(naam, positie);
      }
      
      @Override
      public int action() {
         return positie;
      }
   }
   
   /***************************************************/
   static class Start extends Veld {
      
      public Start() {
         super("Start", 0);
      }
      
      @Override
      public int action() {
         return positie;
      }
   }
   
   /***************************************************/
   static class Gevangenis extends Veld {
      
      public Gevangenis() {
         super("Gevangenis", 10);
      }
      
      @Override
      public int action() {
         return positie;
      }
   }
   
   /***************************************************/
   static class GoToJail extends Veld {
      
      public GoToJail() {
         super("Ga Direct Naar De Gevangenis", 30);
      }
      
      @Override
      public int action() {
         return 10;
      }
   }
   
   /***************************************************/
   static class CC extends Veld {
      
      CCAndCH cc;
      
      public CC(String name, int pos) {
         super(name, pos);
      }
      
      public void addKaarten(CCAndCH cc) {
         this.cc = cc;
      }
      
      @Override
      public int action() {
         Speciaal temp = cc.trekKaart(true);
         return (
             temp == Speciaal.AdvanceToGo ?  0 :
             temp == Speciaal.GoToJail    ? 10 :
                                            positie
         );
      }
   }
   
   /***************************************************/
   static class CH extends Veld {
      
      CCAndCH ch;
      
      public CH(String name, int pos) {
         super(name, pos);
      }
      
      public void addKaarten(CCAndCH ch) {
         this.ch = ch;
      }
      
      @Override
      public int action() {
         Speciaal temp = ch.trekKaart(false);
         return (
             temp == Speciaal.AdvanceToGo ?  0 :
             temp == Speciaal.GoToJail    ? 10 :
             temp == Speciaal.GoToC1      ? 11 :
             temp == Speciaal.GoToE3      ? 24 :
             temp == Speciaal.GoToH2      ? 39 :
             temp == Speciaal.GoToR1      ?  5 :
             temp == Speciaal.GoToNextR ? (
                             positie <  5 ?  5 :
                             positie < 15 ? 15 : 
                             positie < 25 ? 25 :
                             positie < 35 ? 35 : 
                                             5
                     )                         :    
             temp == Speciaal.GoToNextU ?  (
                             positie < 12 ? 12 :
                             positie < 28 ? 28 : 
                                            12
                     )                         :
             temp == Speciaal.GoBack3 ?  (
                        positie < 3 ? 
                              40 + positie - 3 :
                                   positie - 3
                     )                         :
                                       positie
             );
      }
   }
   
   /***************************************************/
   static class VrijParkeren extends Veld {
      
      public VrijParkeren() {
         super("Vrij Parkeren", 20);
      }
      
      @Override
      public int action() {
         return positie;
      }
   }
   
   /***************************************************/
   static class Station extends Veld {
      
      public Station(String naam, int pos) {
         super(naam, pos);
      }
      
      @Override
      public int action() {
         return positie;
      }
   }
   
   /***************************************************/
   static class WaterEnStroom extends Veld {
      
      public WaterEnStroom(String naam, int pos) {
          super(naam, pos);
      }
      
      @Override
      public int action() {
         return positie;
      }
   }
   
   /***************************************************/
   static class Belasting extends Veld {
      
      public Belasting(String naam, int pos) {
         super(naam, pos);
      }
      
      @Override
      public int action() {
         return positie;
      }
   }
   
   /***************************************************/
   static class CCAndCH {
      ArrayList<Speciaal> CCkaart = new ArrayList<>();
      ArrayList<Speciaal> CHkaart = new ArrayList<>();
      int pointer_CC;
      int pointer_CH;
      int lengte_CC;
      int lengte_CH;
      
      public CCAndCH() {
         // eerst CC
         CCkaart.add(Speciaal.AdvanceToGo);
         CCkaart.add(Speciaal.GoToJail);
         for (int i = 2; i < 16; i++) CCkaart.add(Speciaal.Niks);
         Collections.shuffle(CCkaart);
         pointer_CC = 0;
         lengte_CC = CCkaart.size();
         // dan CH
         CHkaart.add(Speciaal.AdvanceToGo);
         CHkaart.add(Speciaal.GoToJail);
         CHkaart.add(Speciaal.GoToC1);
         CHkaart.add(Speciaal.GoToE3);
         CHkaart.add(Speciaal.GoToH2);
         CHkaart.add(Speciaal.GoToR1);
         CHkaart.add(Speciaal.GoToNextR);
         CHkaart.add(Speciaal.GoToNextR);
         CHkaart.add(Speciaal.GoToNextU);
         CHkaart.add(Speciaal.GoBack3);
         for (int i = 10; i < 16; i++) CHkaart.add(Speciaal.Niks);
         Collections.shuffle(CHkaart);
         pointer_CH = 0;
         lengte_CH = CCkaart.size();
      }
      
      public Speciaal trekKaart(boolean cc) {
         Speciaal temp;
         if (cc) {
            temp = CCkaart.get(pointer_CC);
            pointer_CC = (pointer_CC + 1) % lengte_CC;
         }
         else {
            temp = CHkaart.get(pointer_CH);
            pointer_CH = (pointer_CH + 1) % lengte_CH;
         }
         return temp;
      }
   }  // einde class CCAndCH
   
   /***************************************************/
   static class Dobbelsteen {
      int aantal_vlakken;
      Random r;
      
      public Dobbelsteen(int vlakken) {
         aantal_vlakken = vlakken;
         r = new Random();
      }
      
      public int werp() {
         return r.nextInt(aantal_vlakken) + 1;
      }
   }
   
   /***************************************************/
   
} // einde class Euler084


