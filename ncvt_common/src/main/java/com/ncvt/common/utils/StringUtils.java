package com.ncvt.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by guohang on 2019/10/28
 */
public class StringUtils {

    /**
     * 首字母变小写
     */
    public static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] += ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    /**
     * 首字母变大写
     */
    public static String firstCharToUpperCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            char[] arr = str.toCharArray();
            arr[0] -= ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    /**
     * 用*替换字符
     *
     * @param str   需要替换的字符串
     * @param start 开始的位置
     * @param end   结束的位置
     * @return String
     */
    public static String replaceWithStar(String str, int start, int end) {
        int length = str.length();
        StringBuilder res = new StringBuilder(str);
        StringBuilder star = new StringBuilder("*");
        if (end <= 0) {
            end += length;
        }
        if (start < 0 || end > length || start > end) {
            return str;
        }
        for (int i = 0; i < end - start; i++) {
            star.append("*");
        }
        res.replace(start, end, star.toString());
        return res.toString();
    }

    /**
     * 数组转化String，相当于反split
     *
     * @param objects
     * @return
     */
    public static String join(Object... objects) {
        if (objects == null || objects.length < 1) {
            return null;
        }
        if (objects.length == 1) {
            return String.valueOf(objects[0]);
        }
        StringBuilder res = new StringBuilder();
        for (Object o : objects) {
            res.append(",").append(o);
        }
        return res.deleteCharAt(0).toString();
    }

    /**
     * 数组转化String，相当于反split
     *
     * @param strings
     * @return
     */
    public static String join2(String[] strings) {
        return join2(strings, ",");
    }

    /**
     * 数组转化String，相当于反split
     *
     * @param strings
     * @return
     */
    public static String join2(String[] strings, String split) {
        if (strings == null || strings.length < 1) {
            return null;
        }
        if (strings.length == 1) {
            return strings[0];
        }
        StringBuilder res = new StringBuilder();
        for (String s : strings) {
            res.append(split).append(s);
        }
        return res.deleteCharAt(0).toString();
    }

    /**
     * 字符串为 null 或者为  "" 时返回 true
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 字符串不为 null 而且不为  "" 时返回 true
     */
    public static boolean notBlank(String str) {
        return str != null && !"".equals(str.trim());
    }

    public static boolean notBlank(String... strings) {
        if (strings == null)
            return false;
        for (String str : strings)
            if (str == null || "".equals(str.trim()))
                return false;
        return true;
    }

    public static boolean notNull(Object... paras) {
        if (paras == null)
            return false;
        for (Object obj : paras)
            if (obj == null)
                return false;
        return true;
    }

    /**
     * 删除最后结尾的一个逗号
     */
    public static String DelLastComma(String str) {
        if (str.length() < 1) {
            return "";
        }
        return str.substring(0, str.lastIndexOf(","));
    }

    /**
     * 过滤非法字符
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String StringFilter(String str) throws PatternSyntaxException {
        // 只允许字母和数字
        // String regEx = "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);

        return m.replaceAll("").trim();
    }

    /**
     * html 字符串转换
     *
     * @param str
     * @return
     */
    public static String toHtml(String str) {
        if (str == null)
            return null;
        StringBuffer sb = new StringBuffer();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            switch (c) {
                case ' ':
                    sb.append("&nbsp;");
                    break;
                case '\n':
                    sb.append("<br>");
                    break;
                case '\r':
                    break;
                case '\'':
                    sb.append("&#39;");
                    break;
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '"':
                    sb.append("&#34;");
                    break;
                case '\\':
                    sb.append("&#92;");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    // / <summary>
    // / 检测是否是正确的Url
    // / </summary>
    // / <param para="strUrl">要验证的Url</param>
    // / <returns>判断结果</returns>
    public static boolean IsURL(String strUrl) {
        String regEx = "^(http|https)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-Z0-9\\.&%\\$\\-]+)*@)*(" +
                "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\\." +
                "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\." +
                "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\." +
                "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|localhost|([a-zA-Z0-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\" +
                ".(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{1,10}))(\\:[0-9]+)*(/" +
                "($|[a-zA-Z0-9\\.\\,\\?\\'\\\\\\+&%\\$#\\=~_\\-]+))*$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(strUrl);
        return m.matches();
    }

    /**
     * 检测是否有Sql危险字符
     *
     * @param str 要判断字符串
     * @return 判断结果
     */
    public static String FilterIllegalString(String str) {
        if (!notBlank(str)) {
            return "";
        }
        String regEx = "[-|;|,|\\/|\\(|\\)|\\[|\\]|\\}|\\{|%|@|\\*|!|\\'|\\<|\\>]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }

    /**
     * 检查危险字符
     *
     * @param sInput
     * @return
     */
    public static String FilterSqlString(String sInput) {
        if (sInput == null || sInput.equals("")) {
            return null;
        }
        String sInput1 = sInput.toLowerCase();
        String output = sInput;
        String regEx = "\\*|and|exec|insert|select|delete|update|count|master|truncate|declare|char\\(|mid\\(|chr\\(|\\'";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(sInput);
        if (m.find()) {
            output = m.replaceAll("");
        } else {
            output = output.replace("'", "''");
        }
        return output;
    }

    /**
     * @return
     */
    public static String GetGuidNoBar() {
        String s = UUID.randomUUID().toString();
        // 去掉"-"符号
        return s.replace("-", "");
    }

    public static boolean isNotBlank(StringBuffer instr) {
        return instr != null && !"".equals(instr);
    }


    public static String RemoveHtml(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        //  String regEx_html = "<(?!img)[^>]*>"; //定义HTML标签的正则表达式
        String regEx_html = "<[^>]+>";

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签

        return htmlStr.trim(); //返回文本字符串
    }


    /**
     * @param number
     * @param defaultVal
     * @return
     */
    public static int parseInt(String number, int defaultVal) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188 177
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 电信：177(电信4G号码段)
         * 总结起来就是第一位必定为1，第二位必定为3或5或7或8，其他位置的可以为0-9
         */
        String telRegex = "[1]\\d{10}";// "[1]"代表第1位为数字1，"[3578]"代表第二位可以为3、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (isBlank(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    /**
     * 验证密码格式
     */
    public static boolean isPasswordNO(String password) {
        /*
         * 1:全是数字
         * 2：全是字母
         * 3：全是特殊字符
         * 4：任意两种组合
         * 5：三种组合
         * 6:6~20个字符组成
         */
        String pwdRegex = "/^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{6,20}$/";
        if (isBlank(password)) {
            return false;
        } else {
            return password.matches(pwdRegex);
        }
    }

    /**
     * 获取完整的url地址
     *
     * @param request
     * @return
     */
    public static String getRequestURL(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String url = "http://" + request.getServerName(); //服务器地址
        if (request.getServerPort() != 0) {
            url += ":" + request.getServerPort();//端口号
        }
        url += request.getContextPath()      //项目名称
                + request.getServletPath();      //请求页面或其他地址
        if (notBlank(request.getQueryString())) {
            url += "?" + request.getQueryString();
        }
        return url;
    }

    /**
     * 下滑线转驼峰
     *
     * @param str
     * @return
     */
    public static String underlineToHump(String str) {
        if (isBlank(str)) {
            return str;
        }
        String[] properties = str.split("_");
        if (properties.length <= 1) {
            return str;
        }
        String tempStr = properties[0];
        for (int i = 1; i < properties.length; i++) {
            tempStr = tempStr + firstCharToUpperCase(properties[i]);
        }
        return tempStr;
    }

    /**
     * 下滑线转驼峰
     *
     * @param strs
     * @return
     */
    public static String[] underlineToHump(String[] strs) {
        if (strs == null || strs.length < 1) {
            return strs;
        }
        for (int i = 0; i < strs.length; i++) {
            strs[i] = underlineToHump(strs[i]);
        }
        return strs;
    }

    /**
     * 驼峰转下滑线
     *
     * @param str
     * @return
     */
    public static String humpToUnderline(String str) {
        if (isBlank(str)) {
            return str;
        }
        StringBuilder buf = new StringBuilder(str);
        for (int i = 1; i < buf.length() - 1; i++) {
            if (Character.isLowerCase(buf.charAt(i - 1)) && Character.isUpperCase(buf.charAt(i))
                    && Character.isLowerCase(buf.charAt(i + 1))) {
                buf.insert(i++, '_');
            }
        }
        return buf.toString().toLowerCase();
    }

    /**
     * 驼峰转下滑线
     *
     * @param strs
     * @return
     */
    public static String[] humpToUnderline(String[] strs) {
        if (strs == null || strs.length < 1) {
            return strs;
        }
        for (int i = 0; i < strs.length; i++) {
            strs[i] = humpToUnderline(strs[i]);
        }
        return strs;
    }

    /**
     * 对象是否不为空(新增)
     */
    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    /**
     * 对象是否为空
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            if ("".equals(o.toString().trim())) {
                return true;
            }
        } else if (o instanceof List) {
            if (((List) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Map) {
            if (((Map) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Set) {
            if (((Set) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Object[]) {
            if (((Object[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof int[]) {
            if (((int[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof long[]) {
            if (((long[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof Collection) {
            if (((Collection) o).size() == 0) {
                return true;
            }
        }


        return false;
    }

    /**
     * 对象组中是否存在空对象
     */
    public static boolean isOneEmpty(Object... os) {
        for (Object o : os) {
            if (isEmpty(o)) {
                return true;
            }
        }
        return false;
    }
}
