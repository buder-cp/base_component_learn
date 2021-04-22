package com.example.databinding.simpletextview;

public class BookRatingUtil {

    /**
     * 为书本打星，注意，该方法需要为静态方法，在布局文件中，通过<import/>标签引入
     */
    public static String getRatingString(int rating) {
        switch (rating) {
            case 0:
                return "零星";
            case 1:
                return "一星";
            case 2:
                return "二星";
            case 3:
                return "三星";
            case 4:
                return "四星";
            case 5:
                return "五星";
        }
        return "";
    }
}
