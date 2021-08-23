package com.chilitech.base.utils;

import java.util.List;


public class ListUtils {
    public static final String DEFAULT_JOIN_SEPARATOR = ",";

    public ListUtils() {
    }

    public static <V> int getSize(List<V> sourceList) {
        return sourceList == null ? 0 : sourceList.size();
    }

    public static <V> boolean isEmpty(List<V> sourceList) {
        return sourceList == null || sourceList.size() == 0;
    }

    public static String join(List<String> list) {
        return join(list, ",");
    }

    public static String join(List<String> list, char separator) {
        return join(list, new String(new char[]{separator}));
    }

    public static String join(List<String> list, String separator) {
        if (isEmpty(list)) {
            return "";
        } else {
            if (separator == null) {
                separator = ",";
            }

            StringBuilder joinStr = new StringBuilder();

            for (int i = 0; i < list.size(); ++i) {
                joinStr.append((String) list.get(i));
                if (i != list.size() - 1) {
                    joinStr.append(separator);
                }
            }
            return joinStr.toString();
        }
    }
}
