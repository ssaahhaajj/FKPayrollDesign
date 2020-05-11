public class ByPaymaster extends ModeOfPayment{

    @Override
    public String getMode() {
        return "ByPaymaster";
    }

    @Override
    public String modeDetails() {
        String temp="You have to collect the paychecks from the Paymaster";
        return temp;
    }
}
