import java.util.Random;
import java.awt.*;
import javax.swing.JTextArea;

public class Czlowiek extends Zwierze{
    
    private boolean czyAktywowana;
    private int nrTurySpecjalnej;
    private int nrTuryOczekiwania;
    private final int GORA = 0;
    private final int DOL = 1;
    private final int LEWO = 2;
    private final int PRAWO = 3;

    public Czlowiek(int wX, int wY, Swiat sw){
        super(5, 4, wX, wY, 'C', sw, Color.PINK);
        this.czyAktywowana = false;
	    this.nrTurySpecjalnej = 0;
	    this.nrTuryOczekiwania = 0;
    }

    @Override
    public void akcja(){
        int zasieg = this.UstawZasieg();
	
        Organizm[][] organizm = this.swiat.GetOrganizmy();

        if (this.swiat.GetGUI().GetKierunek() == GORA) 
        {
            
            if (this.wspY - zasieg >= 0)
            {
                this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                this.UstawWspolrzednaY(this.wspY - zasieg);
                if (organizm[this.wspX][this.wspY] == null)
                {
                    this.swiat.UstawOrganizm(this);
                }
                else
                {
                    organizm[this.wspX][this.wspY].kolizja(this, this.wspX, this.wspY + zasieg);
                }
            }
        }
        else if (this.swiat.GetGUI().GetKierunek() == DOL)
        {
            if (this.wspY + zasieg < this.swiat.GetWysokosc())
            {
                this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                this.UstawWspolrzednaY(this.wspY + zasieg);
                if (organizm[this.wspX][this.wspY] == null)
                {
                    this.swiat.UstawOrganizm(this);
                }
                else
                {
                    organizm[this.wspX][this.wspY].kolizja(this, this.wspX, this.wspY - zasieg);
                }
            }
        }
        else if (this.swiat.GetGUI().GetKierunek() == LEWO)
        {
            if (this.wspX - zasieg >= 0)
            {
                this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                this.UstawWspolrzednaX(this.wspX - zasieg);
                if (organizm[this.wspX][this.wspY] == null)
                {
                    this.swiat.UstawOrganizm(this);
                }
                else
                {
                    organizm[this.wspX][this.wspY].kolizja(this, this.wspX + zasieg, this.wspY);
                }
            }
        }
        else if (this.swiat.GetGUI().GetKierunek() == PRAWO)
        {
            if (this.wspX + zasieg < this.swiat.GetSzerokosc())
            {
                this.swiat.ZerujOrganizm(this.wspX, this.wspY);
                this.UstawWspolrzednaX(this.wspX + zasieg);
                if (organizm[this.wspX][this.wspY] == null)
                {
                    this.swiat.UstawOrganizm(this);
                }
                else
                {
                    organizm[this.wspX][this.wspY].kolizja(this, this.wspX - zasieg, this.wspY);
                }
            }
        }
    }

    public void UstawNrTurySpecjalnej(int nr){
        this.nrTurySpecjalnej = nr;
    }

    public void UstawNrTuryOczekiwania(int nr){
        this.nrTuryOczekiwania = nr;
    }

    public void UstawAktywowanie(boolean aktywowanie){
        this.czyAktywowana = aktywowanie;
    }

    public int GetNrTurySpecjalnej(){
        return this.nrTurySpecjalnej;
    }

    public int GetNrTuryOczekiwania(){
        return this.nrTuryOczekiwania;
    }

    public boolean GetAktywowanie(){
        return this.czyAktywowana;
    }

    public int UstawZasieg(){
        if (this.GetAktywowanie())
        {
            int nr = this.GetNrTurySpecjalnej();
            this.UstawNrTurySpecjalnej(nr + 1);

            if (this.GetNrTurySpecjalnej() > 5)
            {
                this.UstawNrTurySpecjalnej(0);
                this.UstawAktywowanie(false);
                this.UstawNrTuryOczekiwania(1);
                return 1;
            }
            else
            {
                nr = this.GetNrTurySpecjalnej();
                if (nr <= 3)
                {
                    return 2;
                }
                else
                {
                    Random rand = new Random();
                    int los = rand.nextInt(2);
                    if (los == 1)
                    {
                        return 2;
                    }
                    else
                    {
                        return 1;
                    }
                }
            }
        }
        else
        {
            int nr = this.GetNrTuryOczekiwania();

            if (nr <= 5 && nr > 0)
            {
                this.UstawNrTuryOczekiwania(nr + 1);
                if(this.swiat.GetGUI().GetAktywowanie())
                {
                    JTextArea txt = this.swiat.GetGUI().GetTextArea();
                    txt.append("    Jeszcze nie możesz aktywować superumiejętności\n");
                    this.swiat.GetGUI().SetTextArea(txt);
                }
                return 1;
            }
            else if (nr == 0 || nr == 6)
            {
                this.UstawNrTuryOczekiwania(0);
                if(this.swiat.GetGUI().GetAktywowanie())
                {
                    this.UstawAktywowanie(true);
                    this.UstawNrTurySpecjalnej(1);

                    JTextArea txt = this.swiat.GetGUI().GetTextArea();
                    txt.append("    Aktytwowano superumiejętność\n");
                    this.swiat.GetGUI().SetTextArea(txt);

                    return 2;
                }
            }
            return 1;
        }
    }
}