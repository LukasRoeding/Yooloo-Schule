package client;

import common.YoolooKarte;
import common.YoolooSpieler;

public class YoolooCheater {
	
	public void TauscheKarten(int indexA,int indexB, YoolooSpieler meinSpieler) {
		
		YoolooKarte[] aktuelleSortierung = meinSpieler.getAktuelleSortierung();
		aktuelleSortierung[indexA].getFarbe();
		YoolooKarte tempKarte;
		tempKarte = new YoolooKarte(aktuelleSortierung[indexA].getFarbe(), aktuelleSortierung[indexB].getWert());
		aktuelleSortierung[indexA] = aktuelleSortierung[indexB];
		aktuelleSortierung[indexB] = tempKarte;
	}

	public static void cheat(int stichNummer,int joker,YoolooSpieler meinSpieler) {
				
	}

}
