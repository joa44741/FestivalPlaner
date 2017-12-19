package de.oth.joa44741.swprojektjohn.core;

/**
 *
 * @author Andreas John
 */
public final class RegexPattern {

    private static final String REGEX_SCHEME = "https?:"; //Also called 'protocol'
    private static final String REGEX_AUTHORATIVE_DECLARATION = "/{2}";
    private static final String REGEX_USERINFO = "(?:[A-Za-z0-9-._~]|%[A-Fa-f0-9]{2})+(?::(?:[A-Za-z0-9-._~]|%[A-Fa-f0-9]{2})+)?@";
    private static final String REGEX_HOST = "(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.){1,126}[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?";
    private static final String REGEX_PORT = ":[0-9]+";
    private static final String REGEX_PATH = "/(?:[A-Za-z0-9-._~]|%[A-Fa-f0-9]{2})*";
    private static final String REGEX_QUERY = "\\?(?:[A-Za-z0-9-._~]+(?:=(?:[A-Za-z0-9-._~+]|%[A-Fa-f0-9]{2})+)?)(?:[&|;][A-Za-z0-9-._~]+(?:=(?:[A-Za-z0-9-._~+]|%[A-Fa-f0-9]{2})+)?)*";
//FRAGMENTs don't need to be parsed as they won't be sent to the server anyways

    public static final String REGEX_URL = "(?:" + REGEX_SCHEME + REGEX_AUTHORATIVE_DECLARATION + ")?(?:" + REGEX_USERINFO + ")?" + REGEX_HOST + "(?:" + REGEX_PORT + ")?(?:" + REGEX_PATH + ")*(?:" + REGEX_QUERY + ")?";

    public static final String REGEX_TIME24HOURS
            = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    private RegexPattern() {

    }

}
