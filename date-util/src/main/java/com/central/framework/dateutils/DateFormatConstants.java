package com.central.framework.dateutils;

public class DateFormatConstants {
    // Basic Date Formats
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_DD_MM_YYYY = "dd-MM-yyyy";
    public static final String DATE_FORMAT_MM_DD_YYYY = "MM-dd-yyyy";
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy/MM/dd";
    public static final String DATE_FORMAT_DD_MM_COMMA_YYYY = "dd MM, yyyy";
    public static final String DATE_FORMAT_MMM_DD_YYYY = "MMM dd, yyyy";
    public static final String DATE_FORMAT_FULL_MONTH = "dd MMMM, yyyy";

    // Variations of "dd/MMM/yyyy" and "dd.MMM.yyyy"
    public static final String DATE_FORMAT_DD_MMM_YYYY_SLASH = "dd/MMM/yyyy";
    public static final String DATE_FORMAT_DD_MMM_YYYY_DOT = "dd.MMM.yyyy";
    public static final String DATE_FORMAT_DD_MMM_YYYY_HYPHEN = "dd-MMM-yyyy";
    public static final String DATE_FORMAT_DD_MMM_YYYY_SPACE = "dd MMM yyyy";
    public static final String DATE_FORMAT_DD_MMMM_YYYY_SLASH = "dd/MMMM/yyyy";
    public static final String DATE_FORMAT_DD_MMMM_YYYY_DOT = "dd.MMMM.yyyy";
    public static final String DATE_FORMAT_DD_MMMM_YYYY_HYPHEN = "dd-MMMM-yyyy";
    public static final String DATE_FORMAT_DD_MMMM_YYYY_SPACE = "dd MMMM yyyy";

    // Date-Time Formats
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT_12H = "dd-MM-yyyy hh:mm a";
    public static final String DATE_TIME_FORMAT_24H = "dd-MM-yyyy HH:mm:ss";
    public static final String DATE_TIME_FORMAT_ISO = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    // Timestamp Formats
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String TIMESTAMP_FORMAT_ISO = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    // Readable Formats
    public static final String READABLE_DATE_FORMAT = "EEEE, MMMM dd, yyyy";
    public static final String READABLE_DATE_TIME_FORMAT = "EEEE, MMM dd, yyyy HH:mm:ss";

}
