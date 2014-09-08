package org.gym;

import android.provider.BaseColumns;

/**
 * Created by AndreyNick on 12.07.2014.
 */
public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Exercise";
        public static final String COLUMN_NAME_WEIGHT = "weight";
        public static final String COLUMN_NAME_TIMES = "times";
        public static final String COLUMN_NAME_DATE = "date";

    }
}
