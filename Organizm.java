import java.awt.*;

public interface Organizm {
    void akcja();
    void kolizja(Organizm zwierze, int staryX, int staryY);
    void rysowanie();
	void komunikatRozmnazanie(Organizm organizm, int x, int y);
	void komunikatPrzedWalka(Organizm zwierze, int staryX, int staryY);
	void UstawSile(final int s);
	void UstawInicjatywe(final int ini);
	void UstawWspolrzednaX(final int x);
	void UstawWspolrzednaY(final int y);
	void UstawZycie(final boolean zycie);
	void UstawWiek(final int wiek);
	int GetSila();
	int GetInicjatywa();
	int GetWspolrzednaX();
	int GetWspolrzednaY();
	char GetZnak();
	boolean GetZycie();
	int GetWiek();
	Color GetKolor();
}