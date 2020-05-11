public class ByAccount extends ModeOfPayment {

    @Override
    public String getMode() {
        return "ByAccount";
    }

    @Override
    public String modeDetails() {
        String temp="Paychecks will be directly deposited into your bank account";
        return temp;
    }
}
