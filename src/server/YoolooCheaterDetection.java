package server;

import common.YoolooKarte;
import common.YoolooSpieler;


/**
 * 
 * @author thiela
 *
 */

public class YoolooCheaterDetection{
	/**
	 * checkForCheaters gleicht eine neue Karte mit allen schon gespielten Karten ab und setzt falls gecheated wird
	 * eine cheaterFlag auf dem Spieler. Welche ihm vom Punkten in dieser und zukünftigen Stichen auschließt.
	 * 
	 * @param checkingArray Das Array von Karten das auf dopplte kontrolliert wird.
	 * @param neueKarte Die Karte die gegen schon gespielte Karten im Array verglichen wird.
	 * @param meinSpieler Der Spieler dem die Karte gehört.
	 * @return returned das Array dem Clienthandler
	 */
	static Object[] checkForCheaters(Object[] checkingArray, YoolooKarte neueKarte, YoolooSpieler meinSpieler) {
		/**
		
		 */
		boolean cheater = false;
		for(int iterator = 0; iterator<checkingArray.length;iterator++) {
			if(neueKarte.equals(checkingArray[iterator]))
			{
				cheater = true;
			}
		}	
		Object[] cheaterArray = addToCheaterArray(checkingArray,neueKarte);
		meinSpieler.setCheaterFlag(cheater);
		return cheaterArray;
	}
	/**
	 * Eine Methode die eine neue Karte zu dem cheaterArray hinzufügt.
	 * Ein temporäres Array mit cheaterArray.length+1 wird erzeugt, die neue Karte hinzugefügt und dann
	 * wird cheaterArray vom temp Array überschrieben.
	 * 
	 * @param cheaterArray ein Array das alle Karten die ein Client dem Server schickt aufnimmt.
	 * @param neueKarte Die neue Karte die dem Array inzugefügt wird.
	 * @return returned das neue CheaterArray mit der neuen Karte am ende
	 */
	private static Object[] addToCheaterArray(Object[] cheaterArray, YoolooKarte neueKarte) {

		System.out.println("Adding to checkingstack...");
		Object[] temp = new Object[cheaterArray.length + 1];
		   System.out.println("cheaterArray Length = " + cheaterArray.length);
		   for (int i = 0; i < cheaterArray.length; i++){
		      temp[i] = cheaterArray[i];
		   }
		   cheaterArray = temp;
		   cheaterArray[cheaterArray.length-1] = neueKarte;
		   return cheaterArray;
	}
}