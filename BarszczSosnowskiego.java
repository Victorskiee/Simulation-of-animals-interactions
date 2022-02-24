import java.awt.*;

public class BarszczSosnowskiego extends Roslina{
    
    public BarszczSosnowskiego(int wX, int wY, Swiat sw){
        super(10, 0, wX, wY, 'B', sw, Color.LIGHT_GRAY);
    }

    @Override
    public void akcja(){
        Organizm[][] organizm = this.swiat.GetOrganizmy();

        super.akcja();

        if (this.wspX + 1 < this.swiat.GetSzerokosc() && organizm[this.wspX + 1][this.wspY] != null)
        {
            if (organizm[this.wspX + 1][this.wspY] instanceof Zwierze && !(organizm[this.wspX + 1][this.wspY] instanceof Cyberowca))
            {
                organizm[this.wspX + 1][this.wspY].UstawZycie(false);
                this.swiat.ZerujOrganizm(this.wspX + 1, this.wspY);
            }
        }
        if (this.wspX - 1 >= 0 && organizm[this.wspX - 1][this.wspY] != null)
        {
            if (organizm[this.wspX - 1][this.wspY] instanceof Zwierze && !(organizm[this.wspX - 1][this.wspY] instanceof Cyberowca))
            {
                organizm[this.wspX - 1][this.wspY].UstawZycie(false);
                this.swiat.ZerujOrganizm(this.wspX - 1, this.wspY);
            }
        }
        if (this.wspY + 1 < this.swiat.GetWysokosc() && organizm[this.wspX][this.wspY + 1] != null)
        {
            if (organizm[this.wspX][this.wspY + 1] instanceof Zwierze && !(organizm[this.wspX][this.wspY + 1] instanceof Cyberowca))
            {
                organizm[this.wspX][this.wspY + 1].UstawZycie(false);
                this.swiat.ZerujOrganizm(this.wspX, this.wspY + 1);
            }
        }
        if (this.wspY - 1 >= 0 && organizm[this.wspX][this.wspY - 1] != null)
        {
            if (organizm[this.wspX][this.wspY - 1] instanceof Zwierze && !(organizm[this.wspX][this.wspY - 1] instanceof Cyberowca))
            {
                organizm[this.wspX][this.wspY - 1].UstawZycie(false);
                this.swiat.ZerujOrganizm(this.wspX, this.wspY - 1);
            }
        }
    }

    @Override
    public void kolizja(Organizm zwierze, int staryX, int staryY){
        this.komunikatPrzedWalka(zwierze, staryX, staryY);
	    this.swiat.ZerujOrganizm(this.wspX, this.wspY);
        this.UstawZycie(false);
        if(!(zwierze instanceof Cyberowca))
        {
            zwierze.UstawZycie(false);
        }
        else
        {
            this.swiat.UstawOrganizm(zwierze);
        }
    }
}