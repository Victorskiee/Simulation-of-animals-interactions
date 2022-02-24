import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import javax.swing.JTextArea;

public abstract class Roslina implements Organizm{
    protected int sila;
	protected int inicjatywa;
	protected int wspX;
	protected int wspY;
	protected int wiek;
	protected char znak;
	protected boolean czyZyje;
    protected Swiat swiat;
    protected Color kolor;
    
    public Roslina(int si, int ini, int wX, int wY, char znak, Swiat sw, Color kolor){
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
        //TO DO
        Organizm[][] organizm = this.swiat.GetOrganizmy();
	
        if (this.wspX - 1 >= 0 && organizm[this.wspX - 1][this.wspY] == null)
        {
            this.rozmnazaj(this.wspX - 1, this.wspY);
        }
        else if (this.wspX + 1 < this.swiat.GetSzerokosc() && organizm[this.wspX + 1][this.wspY] == null)
        {
            this.rozmnazaj(this.wspX + 1, this.wspY);
        }
        else if (this.wspY - 1 >= 0 && organizm[this.wspX][this.wspY - 1] == null)
        {
            this.rozmnazaj(this.wspX, this.wspY - 1);
        }
        else if (this.wspY + 1 < this.swiat.GetWysokosc() && organizm[this.wspX][this.wspY + 1] == null)
        {
            this.rozmnazaj(this.wspX, this.wspY + 1);
        }
    }

    @Override
    public void kolizja(Organizm zwierze, int staryX, int staryY){
        //TO DO
        this.komunikatPrzedWalka(zwierze, staryX, staryY);
	    this.UstawZycie(false);
	    this.swiat.ZerujOrganizm(this.wspX, this.wspY);
	    this.swiat.UstawOrganizm(zwierze);
    }

    @Override
    public void rysowanie(){
        //TO DO
        char[][] plansza = this.swiat.GetPlansza();
	    plansza[this.wspX + 1][this.wspY + 1] = this.znak;
    }

    public void rozmnazaj(int nowyX, int nowyY){
        //TO DO
        Organizm[][] organizm = this.swiat.GetOrganizmy();
        //vector<Organizm*> org = this.swiat.GetUporzadkowane_org();
        ArrayList<Organizm> org = this.swiat.GetUporzadkowane_org();

        Random rand = new Random();
        int los = rand.nextInt(100);
        
        if (this.wiek >= 5 && los < 10)
        {
            this.komunikatRozmnazanie(this, nowyX, nowyY);

            if (this instanceof BarszczSosnowskiego)
            {
                organizm[nowyX][nowyY] = new BarszczSosnowskiego(nowyX, nowyY, this.swiat);
                //org.push_back(organizm[nowyX][nowyY]);
                //this.swiat.UstawUporzadkowane_org(org);
                org.add(organizm[nowyX][nowyY]);
                this.swiat.UstawUporzadkowane_org(org);
            }
            else if (this instanceof Guarana)
            {
                organizm[nowyX][nowyY] = new Guarana(nowyX, nowyY, this.swiat);
                //org.push_back(organizm[nowyX][nowyY]);
                //this.swiat.UstawUporzadkowane_org(org);
                org.add(organizm[nowyX][nowyY]);
                this.swiat.UstawUporzadkowane_org(org);
            }
            else if (this instanceof Trawa)
            {
                organizm[nowyX][nowyY] = new Trawa(nowyX, nowyY, this.swiat);
                //org.push_back(organizm[nowyX][nowyY]);
                //this.swiat.UstawUporzadkowane_org(org);
                org.add(organizm[nowyX][nowyY]);
                this.swiat.UstawUporzadkowane_org(org);
            }
            else if (this instanceof Mlecz)
            {
                organizm[nowyX][nowyY] = new Mlecz(nowyX, nowyY, this.swiat);
                //org.push_back(organizm[nowyX][nowyY]);
                //this.swiat.UstawUporzadkowane_org(org);
                org.add(organizm[nowyX][nowyY]);
                this.swiat.UstawUporzadkowane_org(org);
            }
            else if (this instanceof WilczeJagody)
            {
                organizm[nowyX][nowyY] = new WilczeJagody(nowyX, nowyY, this.swiat);
                //org.push_back(organizm[nowyX][nowyY]);
                //this.swiat.UstawUporzadkowane_org(org);
                org.add(organizm[nowyX][nowyY]);
                this.swiat.UstawUporzadkowane_org(org);
            }
        }
    }

    @Override
    public void komunikatRozmnazanie(Organizm roslina, int x, int y){
        System.out.println(roslina.GetZnak() + "(" + this.GetWspolrzednaX() + "," + this.GetWspolrzednaY() + ") rozsiewa " + this.GetZnak() + "(" + x + "," + y + ")\n");
        
        JTextArea txt = this.swiat.GetGUI().GetTextArea();
        txt.append("    " + roslina.GetZnak() + "(" + this.GetWspolrzednaX() + "," + this.GetWspolrzednaY() + ") rozsiewa " + this.GetZnak() + "(" + x + "," + y + ")\n");
        this.swiat.GetGUI().SetTextArea(txt);
    }

    @Override
    public void komunikatPrzedWalka(Organizm zwierze, int staryX, int staryY){
        System.out.println(zwierze.GetZnak() + "(" + staryX + "," + staryY + ") zjada " + this.znak + "(" + this.wspX + "," + this.wspY + ")\n");
        
        JTextArea txt = this.swiat.GetGUI().GetTextArea();
        txt.append("    " + zwierze.GetZnak() + "(" + staryX + "," + staryY + ") zjada " + this.znak + "(" + this.wspX + "," + this.wspY + ")\n");
        this.swiat.GetGUI().SetTextArea(txt);

    }

    @Override
    public void UstawSile(final int s){
        this.sila = s;
    }

    @Override
    public void UstawInicjatywe(final int ini)
    {
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