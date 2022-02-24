import java.awt.*;

public class Mlecz extends Roslina{
    
    public Mlecz(int wX, int wY, Swiat sw){
        super(0, 0, wX, wY, 'M', sw, Color.YELLOW);
    }

    @Override
    public void akcja(){
        for (int i = 0; i < 3; i++)
	    {
		    super.akcja();
	    }
    }
}