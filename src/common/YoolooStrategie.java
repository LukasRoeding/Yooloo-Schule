package common;


public class YoolooStrategie {


            //Strategie: 10 Karten werden in zwei Gruppen geteilt: 1-5 und 6-10.

            //In jeder Gruppe werden die Zahlen durch das Zufallsprinzip gemischt.


           public static YoolooKarte[] sortierungFestlegen(YoolooKarte[] aktuelleSortierung){

               YoolooKarte[] neueSortierung = new YoolooKarte[aktuelleSortierung.length];  //YoolooKarte[] <-Array

               int neuerIndex;

               for (int i = 0; i < neueSortierung.length; i++) {

                   if (i<5) {

                       neuerIndex = (int) (Math.random() * (neueSortierung.length/2));

                       while (neueSortierung[neuerIndex] != null) {

                           neuerIndex = (int) (Math.random() * (neueSortierung.length/2));

                       }

                   }


                   else {

                       neuerIndex = (int) (Math.random() * (neueSortierung.length/2+neueSortierung.length/2));

                       while (neueSortierung[neuerIndex] != null) {

                           neuerIndex = (int) (Math.random() * (neueSortierung.length/2+neueSortierung.length/2));

                       }

                   }


                   neueSortierung[neuerIndex] = aktuelleSortierung[i];

                   //System.out.printl(i+ ". neuerIndex: "+neuerIndex);

               }

               return neueSortierung;



           }

}




