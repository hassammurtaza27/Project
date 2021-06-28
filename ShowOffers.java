import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ShowOffers extends Network
{
    public ShowOffers() throws IOException, ClassNotFoundException { super(); }

    public void readZongOffers(int duration)
    {
        super.readOffers(duration, super.zong);
    }

    public void readJazzOffers(int duration)
    {
        super.readOffers(duration, super.jazz);
    }

    public void readUfoneOffers(int duration)
    {
        super.readOffers(duration, super.ufone);
    }

    public void readTelenorOffers(int duration)
    {
        super.readOffers(duration, super.telenor);
    }

}
