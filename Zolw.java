import java.util.Random;
import java.awt.*;
import javax.swing.JTextArea;

public class Zolw extends Zwierze{
    private final int TAK = 0;

    public Zolw(int wX, int wY, Swiat sw){
        super(2, 1, wX, wY, 'Z', sw, Color.BLUE);
    }

    @Override
    public void akcja(){
        Random rand = new Random();
        int czy_rusza_sie = rand.nextInt(4);

	    if (czy_rusza_sie == TAK)
	    {
		    super.akcja();
	    }
    }

    @Override
    public void kolizja(Organizm zwierze, int staryX, int staryY){
        if (zwierze instanceof Zolw)
        {
            super.kolizja(zwierze, staryX, staryY);
        }
        else
        {
            super.komunikatPrzedWalka(zwierze, staryX, staryY);

            if (zwierze.GetSila() < 5)
            {
                this.odepchniecie(zwierze, staryX, staryY);
                zwierze.UstawWspolrzednaX(staryX);
                zwierze.UstawWspolrzednaY(staryY);
                this.swiat.UstawOrganizm(zwierze);
            }
            else
            {
                super.komunikatPoWalce(zwierze, this);
                this.UstawZycie(false);
                this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                this.swiat.UstawOrganizm(zwierze);
            }
        }
    }

    public void odepchniecie(Organizm zwierze, int staryX, int staryY){
        System.out.println(this.znak + " odpycha " + zwierze.GetZnak() + " na pole (" + staryX + "," + staryY + ")\n");

        JTextArea txt = this.swiat.GetGUI().GetTextArea();
        txt.append("    " + this.znak + " odpycha " + zwierze.GetZnak() + " na pole (" + staryX + "," + staryY + ")\n");
        this.swiat.GetGUI().SetTextArea(txt);
    }
}