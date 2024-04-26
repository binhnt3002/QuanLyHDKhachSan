package regisration.checkaccount;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {
    public static boolean checkAcc(String ac) // check acc and pass
    {
        return ac.matches("^[a-zA-Z0-9]{3,16}");
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@gmail+\\.com$", Pattern.CASE_INSENSITIVE);

    public boolean checkEmail(String em) // check em
    {
        Matcher matcher =  VALID_EMAIL_ADDRESS_REGEX.matcher(em);
        return matcher.matches();  
    }

    public boolean checkPas(String ps) // check acc and pass
    {
        return ps.matches("^[\\w]{3,16}");
    }

    public boolean checkPhone(String num)// check phone number
    {
        return num.matches("^[0-9]{10}");
    }
}
