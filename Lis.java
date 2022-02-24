import java.util.Random;
import java.awt.*;

public class Lis extends Zwierze{
    
    private final int LEWO = 0;
    private final int PRAWO = 1;
    private final int GORA = 2;
    private final int DOL = 3;

    public Lis(int wX, int wY, Swiat sw){
        super(3, 7, wX, wY, 'L', sw, Color.RED);
    }

    @Override
    public void akcja(){
        Organizm[][] organizm = this.swiat.GetOrganizmy();
        boolean wylosowano = false;
        boolean czy_mozna[] = { true, true, true, true };

        while (!wylosowano)
        {
            Random rand = new Random();
            int kierunek = rand.nextInt(4);

            if (kierunek == LEWO)
            {
                if (this.wspX - 1 >= 0 && organizm[this.wspX - 1][this.wspY] != null && this.sila >= organizm[this.wspX - 1][this.wspY].GetSila())
                {
                    this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                    this.UstawWspolrzednaX(wspX - 1);
                    organizm[this.wspX][this.wspY].kolizja(this, wspX + 1, wspY);
                    wylosowano = true;
                }
                else if (this.wspX - 1 >= 0 && organizm[this.wspX - 1][this.wspY] == null)
                {
                    this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                    this.UstawWspolrzednaX(wspX - 1);
                    this.swiat.UstawOrganizm(this);
                    wylosowano = true;
                }
                else if (this.wspX - 1 < 0)
                {
                    break;
                }
                else
                {
                    czy_mozna[LEWO] = false;
                }
            }
            else if (kierunek == PRAWO)
            {
                if (this.wspX + 1 < this.swiat.GetSzerokosc() && organizm[this.wspX + 1][this.wspY] != null && this.sila >= organizm[this.wspX + 1][this.wspY].GetSila())
                {
                    this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                    this.UstawWspolrzednaX(this.wspX + 1);
                    organizm[this.wspX][this.wspY].kolizja(this, wspX - 1, wspY);
                    wylosowano = true;
                }
                else if (this.wspX + 1 < this.swiat.GetSzerokosc() && organizm[this.wspX + 1][this.wspY] == null)
                {
                    this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                    this.UstawWspolrzednaX(this.wspX + 1);
                    this.swiat.UstawOrganizm(this);
                    wylosowano = true;
                }
                else if (this.wspX + 1 >= this.swiat.GetSzerokosc())
                {
                    break;
                }
                else
                {
                    czy_mozna[PRAWO] = false;
                }
            }
            else if (kierunek == GORA)
            {
                if (this.wspY - 1 >= 0 && organizm[this.wspX][this.wspY - 1] != null && this.sila >= organizm[this.wspX][this.wspY - 1].GetSila())
                {
                    this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                    this.UstawWspolrzednaY(wspY - 1);
                    organizm[this.wspX][this.wspY].kolizja(this, wspX, wspY + 1);
                    wylosowano = true;
                }
                else if (this.wspY - 1 >= 0 && organizm[this.wspX][this.wspY - 1] == null)
                {
                    this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                    this.UstawWspolrzednaY(this.wspY - 1);
                    this.swiat.UstawOrganizm(this);
                    wylosowano = true;
                }
                else if (this.wspY - 1 < 0)
                {
                    break;
                }
                else
                {
                    czy_mozna[GORA] = false;
                }
            }
            else if (kierunek == DOL)
            {
                if (this.wspY + 1 < this.swiat.GetWysokosc() && organizm[this.wspX][this.wspY + 1] != null && this.sila >= organizm[this.wspX][this.wspY + 1].GetSila())
                {
                    this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                    this.UstawWspolrzednaY(wspY + 1);
                    organizm[this.wspX][this.wspY].kolizja(this, wspX, wspY - 1);
                    wylosowano = true;
                }
                else if (this.wspY + 1 < this.swiat.GetWysokosc() && organizm[this.wspX][this.wspY + 1] == null)
                {
                    this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                    this.UstawWspolrzednaY(this.wspY + 1);
                    this.swiat.UstawOrganizm(this);
                    wylosowano = true;
                }
                else if (this.wspY + 1 >= this.swiat.GetWysokosc())
                {
                    break;
                }
                else
                {
                    czy_mozna[DOL] = false;
                }
            }
            if (czy_mozna[LEWO] == false && czy_mozna[PRAWO] == false
                && czy_mozna[GORA] == false && czy_mozna[DOL] == false)
            {
                break;
            }
        }
    }
}