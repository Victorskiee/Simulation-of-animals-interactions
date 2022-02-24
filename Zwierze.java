import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import javax.swing.JTextArea;

public abstract class Zwierze implements Organizm{
    protected int sila;
	protected int inicjatywa;
	protected int wspX;
	protected int wspY;
	protected int wiek;
	protected char znak;
	protected boolean czyZyje;
    protected Swiat swiat;
    protected Color kolor;
    private final int LEWO = 0;
    private final int PRAWO = 1;
    private final int GORA = 2;
    
    public Zwierze(int si, int ini, int wX, int wY, char znak, Swiat sw, Color kolor){
        this.sila = si;
        this.inicjatywa = ini;
        this.wspX = wX;
        this.wspY = wY;
        this.wiek = 0;
        this.znak = znak;
        this.swiat = sw;
        this.czyZyje = true;
        this.kolor = kolor;
    }

    @Override
    public void akcja(){
        Organizm[][] organizm = this.swiat.GetOrganizmy();

        Random rand = new Random();
        int kierunek = rand.nextInt(4);

        if (kierunek == LEWO)
        {
            if (this.wspX - 1 >= 0)
            {
                this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                this.UstawWspolrzednaX(this.wspX - 1);
                if (organizm[this.wspX][this.wspY] == null)
                {
                    this.swiat.UstawOrganizm(this);
                }
                else
                {
                    organizm[this.wspX][this.wspY].kolizja(this, this.wspX + 1, this.wspY);
                }
            }
        }
        else if (kierunek == PRAWO)
        {
            if (this.wspX + 1 < this.swiat.GetSzerokosc())
            {
                this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                this.UstawWspolrzednaX(this.wspX + 1);
                if (organizm[this.wspX][this.wspY] == null)
                {
                    this.swiat.UstawOrganizm(this);
                }
                else
                {
                    organizm[this.wspX][this.wspY].kolizja(this, this.wspX - 1, this.wspY);
                }
            }
        }
        else if (kierunek == GORA)
        {
            if (this.wspY - 1 >= 0)
            {
                this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                this.UstawWspolrzednaY(this.wspY - 1);
                if (organizm[this.wspX][this.wspY] == null)
                {
                    this.swiat.UstawOrganizm(this);
                }
                else
                {
                    organizm[this.wspX][this.wspY].kolizja(this, this.wspX, this.wspY + 1);
                }
            }
        }
        else
        {
            if (this.wspY + 1 < this.swiat.GetWysokosc())
            {
                this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                this.UstawWspolrzednaY(this.wspY + 1);
                if (organizm[this.wspX][this.wspY] == null)
                {
                    this.swiat.UstawOrganizm(this);
                }
                else
                {
                    organizm[this.wspX][this.wspY].kolizja(this, this.wspX, this.wspY - 1);
                }
            }
        }
    }

    @Override
    public void kolizja(Organizm zwierze, int staryX, int staryY){
        //int x=0, y=0;
        Coordinates pkt = new Coordinates();
        Organizm[][] organizm = this.swiat.GetOrganizmy();
        ArrayList<Organizm> org = this.swiat.GetUporzadkowane_org();

        if (this instanceof Owca && zwierze instanceof Owca)
        {
            if (this.generujPotomstwo(zwierze, staryX, staryY, pkt))
            {
                int x = pkt.GetWspolrzednaX();
                int y = pkt.GetWspolrzednaY();
                this.komunikatRozmnazanie(zwierze, x, y);
                organizm[x][y] = new Owca(x, y, this.swiat);
                org.add(organizm[x][y]);
                this.swiat.UstawUporzadkowane_org(org);
            }
        }
        else if (this instanceof Lis && zwierze instanceof Lis)
        {
            if (this.generujPotomstwo(zwierze, staryX, staryY, pkt))
            {
                int x = pkt.GetWspolrzednaX();
                int y = pkt.GetWspolrzednaY();
                this.komunikatRozmnazanie(zwierze, x, y);
                organizm[x][y] = new Lis(x, y, this.swiat);

                org.add(organizm[x][y]);
                this.swiat.UstawUporzadkowane_org(org);
            }
        }
        else if (this instanceof Zolw && zwierze instanceof Zolw)
        {
            if (this.generujPotomstwo(zwierze, staryX, staryY, pkt))
            {
                int x = pkt.GetWspolrzednaX();
                int y = pkt.GetWspolrzednaY();
                this.komunikatRozmnazanie(zwierze, x, y);
                organizm[x][y] = new Zolw(x, y, this.swiat);
                org.add(organizm[x][y]);
                this.swiat.UstawUporzadkowane_org(org);
            }
            
        }
        else if (this instanceof Wilk && zwierze instanceof Wilk)
        {
            if (this.generujPotomstwo(zwierze, staryX, staryY, pkt))
            {
                int x = pkt.GetWspolrzednaX();
                int y = pkt.GetWspolrzednaY();
                this.komunikatRozmnazanie(zwierze, x, y);
                organizm[x][y] = new Wilk(x, y, this.swiat);
                org.add(organizm[x][y]);
                this.swiat.UstawUporzadkowane_org(org);
            }
        }
        else if (this instanceof Cyberowca && zwierze instanceof Cyberowca)
        {
            if (this.generujPotomstwo(zwierze, staryX, staryY, pkt))
            {
                int x = pkt.GetWspolrzednaX();
                int y = pkt.GetWspolrzednaY();
                this.komunikatRozmnazanie(zwierze, x, y);
                organizm[x][y] = new Cyberowca(x, y, this.swiat);
                org.add(organizm[x][y]);
                this.swiat.UstawUporzadkowane_org(org);
            }
        }
        else
        {
            this.walcz(zwierze, staryX, staryY);
        }
    }

    public boolean generujPotomstwo(Organizm zwierze, int staryX, int staryY, Coordinates punkt){
        Organizm[][] organizm = this.swiat.GetOrganizmy();

        zwierze.UstawWspolrzednaX(staryX);
        zwierze.UstawWspolrzednaY(staryY);
        this.swiat.UstawOrganizm(zwierze);

        if (this.wiek >= 3 && zwierze.GetWiek() >= 3)
        {
            if (this.wspX + 1 < this.swiat.GetSzerokosc() && organizm[this.wspX + 1][this.wspY] == null)
            {
                punkt.UstawWsporzedne(this.wspX + 1, this.wspY);
                return true;
            }
            else if (this.wspX - 1 >= 0 && organizm[this.wspX - 1][this.wspY] == null)
            {
                punkt.UstawWsporzedne(this.wspX - 1, this.wspY);
                return true;
            }
            else if (this.wspY + 1 < this.swiat.GetWysokosc() && organizm[this.wspX][this.wspY + 1] == null)
            {
                punkt.UstawWsporzedne(this.wspX, this.wspY + 1);
                return true;
            }
            else if (this.wspY - 1 >= 0 && organizm[this.wspX][this.wspY - 1] == null)
            {
                punkt.UstawWsporzedne(this.wspX, this.wspY - 1);
                return true;
            }
            else if (zwierze.GetWspolrzednaX() + 1 < this.swiat.GetSzerokosc() && organizm[zwierze.GetWspolrzednaX() + 1][zwierze.GetWspolrzednaY()] == null)
            {
                punkt.UstawWsporzedne(zwierze.GetWspolrzednaX() + 1, zwierze.GetWspolrzednaY());
                return true;
            }
            else if (zwierze.GetWspolrzednaX() - 1 >= 0 && organizm[zwierze.GetWspolrzednaX() - 1][zwierze.GetWspolrzednaY()] == null)
            {
                punkt.UstawWsporzedne(zwierze.GetWspolrzednaX() - 1, zwierze.GetWspolrzednaY());
                return true;
            }
            else if (zwierze.GetWspolrzednaY() + 1 < this.swiat.GetWysokosc() && organizm[zwierze.GetWspolrzednaX()][zwierze.GetWspolrzednaY() + 1] == null)
            {
                punkt.UstawWsporzedne(zwierze.GetWspolrzednaX(), zwierze.GetWspolrzednaY() + 1);
                return true;
            }
            else if (zwierze.GetWspolrzednaY() - 1 >= 0 && organizm[zwierze.GetWspolrzednaX()][zwierze.GetWspolrzednaY() - 1] == null)
            {
                punkt.UstawWsporzedne(zwierze.GetWspolrzednaX(), zwierze.GetWspolrzednaY() - 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public void rysowanie(){
        char[][] plansza = this.swiat.GetPlansza();
	    plansza[this.wspX + 1][this.wspY + 1] = this.znak;
    }

    @Override
    public void komunikatRozmnazanie(Organizm zwierze, int x, int y){
        System.out.println(this.znak + "(" + this.wspX + "," + this.wspY + ") rozmnaza sie z " + zwierze.GetZnak() + "(" + zwierze.GetWspolrzednaX() + "," + zwierze.GetWspolrzednaY() + "). Dziecko: " + this.znak + "(" + x + "," + y + ")\n");

        JTextArea txt = this.swiat.GetGUI().GetTextArea();
        txt.append("    " + this.znak + "(" + this.wspX + "," + this.wspY + ") rozmnaza sie z " + zwierze.GetZnak() + "(" + zwierze.GetWspolrzednaX() + "," + zwierze.GetWspolrzednaY() + "). Dziecko: " + this.znak + "(" + x + "," + y + ")\n");
        this.swiat.GetGUI().SetTextArea(txt);
    }

    public void walcz(Organizm zwierze, int staryX, int staryY){
        this.komunikatPrzedWalka(zwierze, staryX, staryY);

        if (this.GetSila() > zwierze.GetSila())
        {
            this.komunikatPoWalce(this, zwierze);
            zwierze.UstawWspolrzednaX(staryX);
            zwierze.UstawWspolrzednaY(staryY);
            zwierze.UstawZycie(false);
            this.swiat.ZerujOrganizm(staryX, staryY);
        }
        else
        {
            this.komunikatPoWalce(zwierze, this);
            this.UstawZycie(false);
            this.swiat.ZerujOrganizm(this.wspX, this.wspY);
            this.swiat.UstawOrganizm(zwierze);
        }
    }

    @Override
    public void komunikatPrzedWalka(Organizm zwierze, int staryX, int staryY){
        System.out.println(zwierze.GetZnak() + "(" + staryX + "," + staryY + ") atakuje " + this.znak + "(" + this.wspX + "," + this.wspY + ")\n");
        
        JTextArea txt = this.swiat.GetGUI().GetTextArea();
        txt.append("    " + zwierze.GetZnak() + "(" + staryX + "," + staryY + ") atakuje " + this.znak + "(" + this.wspX + "," + this.wspY + ")\n");
        this.swiat.GetGUI().SetTextArea(txt);
    }

    public void komunikatPoWalce(Organizm pierwszy, Organizm drugi){
        System.out.println(pierwszy.GetZnak() + " pokonuje " + drugi.GetZnak() + "\n");

        JTextArea txt = this.swiat.GetGUI().GetTextArea();
        txt.append("    " + pierwszy.GetZnak() + " pokonuje " + drugi.GetZnak() + "\n");
        this.swiat.GetGUI().SetTextArea(txt);
    }

    @Override
    public void UstawSile(final int s){
        this.sila = s;
    }

    @Override
    public void UstawInicjatywe(final int ini){
        this.inicjatywa = ini;
    }

    @Override
    public void UstawWspolrzednaX(final int x){
        this.wspX = x;
    }
    
    @Override
    public void UstawWspolrzednaY(final int y){
        this.wspY = y;
    }
    
    @Override
    public void UstawZycie(final boolean zycie){
        this.czyZyje = zycie;
    }
    
    @Override
    public void UstawWiek(final int wiek){
        this.wiek = wiek;
    }
    
    @Override
    public int GetSila(){
        return this.sila;
    }
    
    @Override
    public int GetInicjatywa(){
        return this.inicjatywa;
    }
    
    @Override
    public int GetWspolrzednaX(){
        return this.wspX;
    }
    
    @Override
    public int GetWspolrzednaY(){
        return this.wspY;
    }
    
    @Override
    public char GetZnak(){
        return this.znak;
    }
    
    @Override
    public boolean GetZycie(){
        return this.czyZyje;
    }
    
    @Override
    public int GetWiek(){
        return this.wiek;
    }

    @Override
    public Color GetKolor(){
        return this.kolor;
    } 
}