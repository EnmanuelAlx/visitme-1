package visit.me.gil.mota.visitme;

/**
 * Created by Slaush on 22/05/2017.
 */
public class Urls
{

    public static final String LOGIN = "/user/auth";
    public static final String REGISTER = "/user";
    public static final String USER_PROFILE = "/user/me";
    public static final String USER_VISITS_SCHEDULED = USER_PROFILE + "/visits/SCHEDULED";
    public static final String USER_VISITS_FREQUENT = USER_PROFILE + "/visits/FREQUENT";
    public static final String USER_VISITS_SPORADIC = USER_PROFILE + "/visits/SPORADIC";
    public static final String USER_COMMUNITIES = USER_PROFILE + "/communities";
    public static final String CREATE_ALERT = "/alerts";
    public static final String USER_DEVICES = USER_PROFILE + "/devices";
}
