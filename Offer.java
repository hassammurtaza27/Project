import java.io.Serializable;

public class Offer implements Serializable
{
    private int sms;
    private int onNetMinutes;
    private int offNetMinutes;
    private int duration;
    private final int id;
    private String internet, offerName;
    private double price;

    public Offer(int sms, int onNetMinutes, int offNetMinutes, String internet, String offerName, int duration, double price, int id) {
        this.sms = sms;
        this.onNetMinutes = onNetMinutes;
        this.offNetMinutes = offNetMinutes;
        this.internet = internet;
        this.offerName = offerName;
        this.duration = duration;
        this.price = price;
        this.id = id;
    }

    public Offer(Offer offer)
    {
        this.sms = offer.sms;
        this.onNetMinutes = offer.onNetMinutes;
        this.offNetMinutes = offer.offNetMinutes;
        this.internet = offer.internet;
        this.offerName = offer.offerName;
        this.duration = offer.duration;
        this.price = offer.price;
        this.id = offer.id;
    }

    int getId()
    {
        return id;
    }

    void setSms(int sms) {
        this.sms = sms;
    }


    void setOnNetMinutes(int onNetMinutes) {
        this.onNetMinutes = onNetMinutes;
    }

    void setOffNetMinutes(int offNetMinutes) {
        this.offNetMinutes = offNetMinutes;
    }

    int getDuration() {
        return duration;
    }

    void setDuration(int duration) {
        this.duration = duration;
    }

    void setInternet(String internet) {
        this.internet = internet;
    }

    void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    double getPrice() {
        return price;
    }

    void setPrice(double price) {
        this.price = price;
    }

    String getOfferName() { return this.offerName; }


    public String toString()
    {
        String str = "";
        str += offerName + " - " + price + " PKR\n\t";

        if (onNetMinutes != 0)
            str += onNetMinutes + " On-net Minutes  ";
        if (offNetMinutes != 0)
            str += offNetMinutes + " Off-net Minutes  ";
        if (sms != 0)
            str += sms + " SMS  ";
        if (!internet.equals("0"))
            str += internet + " Internet";

        str += "\n";

        return str;
    }
}
