public class ByPost extends ModeOfPayment {

    @Override
    public String getMode() {
        return "ByPost";
    }

    @Override
    public String modeDetails() {
        String temp="Paychecks will be mailed to your postal address";
        return temp;
    }
}
