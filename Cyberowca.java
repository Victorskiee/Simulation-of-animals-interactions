import java.awt.*;

public class Cyberowca extends Zwierze{

    public Cyberowca(int wX, int wY, Swiat sw){
        super(11, 4, wX, wY, 'X', sw, Color.BLACK);
    }

    @Override
    public void akcja(){
        boolean czyBarszcz = false;
        Organizm[][] organizm = this.swiat.GetOrganizmy();
        int x=0, y=0, odleglosc=-1;
        int tmp_x=0, tmp_y=0, tmp_odleglosc=0;

        for(int i=0; i < this.swiat.GetSzerokosc(); i++)
        {
            for(int j=0; j < this.swiat.GetWysokosc(); j++)
            {
                if(organizm[i][j] instanceof BarszczSosnowskiego)
                {
                    czyBarszcz = true;
                    tmp_x = organizm[i][j].GetWspolrzednaX() - this.wspX;
                    tmp_y = organizm[i][j].GetWspolrzednaY() - this.wspY;
                    tmp_odleglosc = Math.abs(tmp_x) + Math.abs(tmp_y);

                    if(tmp_odleglosc < odleglosc || odleglosc == -1)
                    {
                        odleglosc = tmp_odleglosc;
                        x = tmp_x;
                        y = tmp_y;
                    }
                }
            }
        }

        if(czyBarszcz)
        {
            if(x != 0)
            {
                if(x > 0)
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
                else
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
            else if(y != 0)
            {
                if(y > 0)
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
                else
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
        }
        else
        {
            super.akcja();
        }
    }
}