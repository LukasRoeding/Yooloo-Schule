// History of Change
// vernr    |date  | who | lineno | what
//  V0.106  |200107| cic |    -   | add history of change

package common;

import java.io.Serializable;
import java.util.Arrays;

import common.YoolooKartenspiel.Kartenfarbe;
import server.YoolooLoginData;

public class YoolooSpieler implements Serializable {

	private static final long serialVersionUID = 376078630788146549L;
	private String name;
	private Kartenfarbe spielfarbe;
	private int clientHandlerId = -1;
	private int punkte;
	private YoolooKarte[] aktuelleSortierung;
	private boolean zuschauer;
	private boolean bot = false;
	private boolean cheaterFlag = false;
	public YoolooSpieler(String name, int maxKartenWert, boolean zuschauer) {
		this.name = name;
		this.punkte = 0;
		this.spielfarbe = null;
		this.aktuelleSortierung = new YoolooKarte[maxKartenWert];
		this.zuschauer = zuschauer;
		
	}
	public YoolooSpieler(String name, int maxKartenWert, boolean zuschauer, boolean bot) {
		this.name = name;
		this.punkte = 0;
		this.spielfarbe = null;
		this.aktuelleSortierung = new YoolooKarte[maxKartenWert];
		this.zuschauer = zuschauer;
		this.bot = bot;
	}
	// Sortierung wird zufuellig ermittelt
	/**
	 * Sortierung unterscheided sich von Spieler zu Bot zu Zuschauer;
	 */
	public void sortierungFestlegen() {
		if (bot) {
			aktuelleSortierung = YoolooStrategie.sortierungFestlegen(aktuelleSortierung);
		}
		else {
			YoolooKarte[] neueSortierung = new YoolooKarte[this.aktuelleSortierung.length];
			for (int i = 0; i < neueSortierung.length; i++) {
	            int[] neuerIndex = YoolooLoginData.getCardOrder(name);
				neueSortierung[i] = aktuelleSortierung[neuerIndex[i]];
				if (zuschauer == true) {
					neueSortierung[neuerIndex[i]].setWert(0);
				}
			}
			aktuelleSortierung = neueSortierung;
		}
		
	}

	public int erhaeltPunkte(int neuePunkte) {
		System.out.print(name + " hat " + punkte + " P - erhaelt " + neuePunkte + " P - neue Summe: ");
		this.punkte = this.punkte + neuePunkte;
		System.out.println(this.punkte);
		return this.punkte;
	}

	@Override
	public String toString() {
		return "YoolooSpieler [name=" + name + ", spielfarbe=" + spielfarbe + ", puntke=" + punkte
				+ ", altuelleSortierung=" + Arrays.toString(aktuelleSortierung) + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Kartenfarbe getSpielfarbe() {
		return spielfarbe;
	}

	public void setSpielfarbe(Kartenfarbe spielfarbe) {
		this.spielfarbe = spielfarbe;
	}

	public int getClientHandlerId() {
		return clientHandlerId;
	}

	public void setClientHandlerId(int clientHandlerId) {
		this.clientHandlerId = clientHandlerId;
	}

	public int getPunkte() {
		return punkte;
	}

	public void setPunkte(int puntke) {
		this.punkte = puntke;
	}

	public YoolooKarte[] getAktuelleSortierung() {
		return aktuelleSortierung;
	}

	public void setAktuelleSortierung(YoolooKarte[] aktuelleSortierung) {
		this.aktuelleSortierung = aktuelleSortierung;
	}

	public void stichAuswerten(YoolooStich stich) {
		System.out.println(stich.toString());

	}

	public boolean isCheater() {
		return cheaterFlag;
	}

	public void setCheaterFlag(boolean cheaterFlag) {
		this.cheaterFlag = cheaterFlag;
	}

}
