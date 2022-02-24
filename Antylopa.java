import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import javax.swing.JTextArea;

public class Antylopa extends Zwierze{

    private final int LEWO = 0;
    private final int PRAWO = 1;
    private final int GORA = 2;
    private final int UCIEKAJ = 0;

    public Antylopa(int wX, int wY, Swiat sw){
        super(4, 4, wX, wY, 'A', sw, Color.ORANGE);
    }

    @Override
    public void akcja(){
        Organizm[][] organizm = this.swiat.GetOrganizmy();

        Random rand = new Random();
        int kierunek = rand.nextInt(4);

        if (kierunek == LEWO)
        {
            if (this.wspX - 2 >= 0)
            {
                this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                this.UstawWspolrzednaX(this.wspX - 2);
                if (organizm[this.wspX][this.wspY] == null)
                {
                    this.swiat.UstawOrganizm(this);
                }
                else
                {
                    organizm[this.wspX][this.wspY].kolizja(this, this.wspX + 2, this.wspY);
                }
            }
        }
        else if (kierunek == PRAWO)
        {
            if (this.wspX + 2 < this.swiat.GetSzerokosc())
            {
                this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                this.UstawWspolrzednaX(this.wspX + 2);
                if (organizm[this.wspX][this.wspY] == null)
                {
                    this.swiat.UstawOrganizm(this);
                }
                else
                {
                    organizm[wspX][this.wspY].kolizja(this, this.wspX - 2, this.wspY);
                }
            }
        }
        else if (kierunek == GORA)
        {
            if (this.wspY - 2 >= 0)
            {
                this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                this.UstawWspolrzednaY(this.wspY - 2);
                if (organizm[this.wspX][this.wspY] == null)
                {
                    this.swiat.UstawOrganizm(this);
                }
                else
                {
                    organizm[this.wspX][this.wspY].kolizja(this, this.wspX, this.wspY + 2);
                }
            }
        }
        else
        {
            if (this.wspY + 2 < this.swiat.GetWysokosc())
            {
                this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                this.UstawWspolrzednaY(this.wspY + 2);
                if (organizm[this.wspX][this.wspY] == null)
                {
                    this.swiat.UstawOrganizm(this);
                }
                else
                {
                    organizm[this.wspX][this.wspY].kolizja(this, this.wspX, this.wspY - 2);
                }
            }
        }
    }

    @Override
    public void kolizja(Organizm zwierze, int staryX, int staryY){
        Coordinates punkt = new Coordinates();
        Organizm[][] organizm = this.swiat.GetOrganizmy();
        ArrayList<Organizm> org = this.swiat.GetUporzadkowane_org();

        Random rand = new Random();
        int los = rand.nextInt(2);

        if (zwierze instanceof Antylopa)
        {
            int x = punkt.GetWspolrzednaX();
            int y = punkt.GetWspolrzednaY();

            if (this.generujPotomstwo(zwierze, staryX, staryY, punkt))
            {
                this.komunikatRozmnazanie(zwierze, x, y);
                organizm[x][y] = new Antylopa(x, y, this.swiat);
                org.add(organizm[x][y]);
                this.swiat.UstawUporzadkowane_org(org);
            }
        }
        else if (los == UCIEKAJ)
        {
            this.komunikatPrzedWalka(zwierze, staryX, staryY);

            if (this.wspX + 1 < this.swiat.GetSzerokosc() && organizm[this.wspX + 1][this.wspY] == null)
            {
                this.komunikatUcieczka(this.wspX + 1, this.wspY);
                this.UstawWspolrzednaX(this.wspX + 1);
                this.swiat.UstawOrganizm(zwierze);
                this.swiat.UstawOrganizm(this);
            }

            else if (this.wspY - 1 >= 0 && organizm[this.wspX][this.wspY - 1] == null)
            {
                this.komunikatUcieczka(this.wspX, this.wspY - 1);
                this.UstawWspolrzednaY(this.wspY - 1);
                this.swiat.UstawOrganizm(zwierze);
                this.swiat.UstawOrganizm(this);
            }

            else if (this.wspX - 1 >= 0 && organizm[this.wspX - 1][this.wspY] == null)
            {
                this.komunikatUcieczka(this.wspX - 1, this.wspY);
                this.UstawWspolrzednaX(this.wspX - 1);
                this.swiat.UstawOrganizm(zwierze);
                this.swiat.UstawOrganizm(this);
            }

            else if (this.wspY + 1 < this.swiat.GetWysokosc() && organizm[this.wspX][this.wspY + 1] == null)
            {
                this.komunikatUcieczka(this.wspX, this.wspY + 1);
                this.UstawWspolrzednaY(this.wspY + 1);
                this.swiat.UstawOrganizm(zwierze);
                this.swiat.UstawOrganizm(this);
            }
            else
            {
                super.kolizja(zwierze, staryX, staryY);
            }
        }
        else
        {
            super.kolizja(zwierze, staryX, staryY);
        }
    }

    public void komunikatUcieczka(int x, int y){
        System.out.println(this.znak + " ucieka na pole (" + x + "," + y + ")\n");
        JTextArea txt = this.swiat.GetGUI().GetTextArea();
        txt.append("    " + this.znak + " ucieka na pole (" + x + "," + y + ")\n");
        this.swiat.GetGUI().SetTextArea(txt);
    }

    public boolean generujPotomstwo(Organizm zwierze, int staryX, int staryY, Coordinates punkt){
        Organizm[][] organizm = this.swiat.GetOrganizmy();

        zwierze.UstawWspolrzednaX(staryX);
        zwierze.UstawWspolrzednaY(staryY);
        this.swiat.UstawOrganizm(zwierze);

        if (this.wiek >= 5 && zwierze.GetWiek() >= 5)
        {
            if (this.wspX == zwierze.GetWspolrzednaX())
            {
                if (this.wspY > zwierze.GetWspolrzednaY() && organizm[this.wspX][this.wspY - 1] == null)
                {
                    punkt.UstawWsporzedne(this.wspX, this.wspY - 1);
                    return true;
                }
                else if(this.wspY < zwierze.GetWspolrzednaY() && organizm[this.wspX][this.wspY + 1] == null)
                {
                    punkt.UstawWsporzedne(this.wspX, this.wspY + 1);
                    return true;
                }
            }
            else if (this.wspY == zwierze.GetWspolrzednaY())
            {
                if (this.wspX > zwierze.GetWspolrzednaX() && organizm[this.wspX - 1][this.wspY] == null)
                {
                    punkt.UstawWsporzedne(this.wspX - 1, this.wspY);
                    return true;
                }
                else if (this.wspX < zwierze.GetWspolrzednaX() && organizm[this.wspX + 1][this.wspY] == null)
                {
                    punkt.UstawWsporzedne(this.wspX + 1, this.wspY);
                    return true;
                }
            }
        }
        return false;
    }
}