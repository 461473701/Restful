package org.hulan.util;

public class StringUtils {

    /**
     * 功能描述：空判断
     *
     * @param str
     * @return
     * @author : zhaokuiqiang
     */
    public static boolean hasLength(CharSequence str) {
        return str != null && str.length() > 0;
    }

    /**
     * 功能描述：空判断
     *
     * @param str
     * @return
     * @author : zhaokuiqiang
     */
    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }
    
    /**
     * 功能描述：不能为空校验
     *
     * @param str
     * @return
     * @author : zhaokuiqiang
     */
    public static boolean isNotNull(String str, String errmsg) {
        if(!hasLength((CharSequence) str)) {
            throw new NullPointerException(errmsg);
        }
        return true;
    }

    /**
     * 功能描述：替换字符串
     *
     * @param inString
     * @param oldPattern
     * @param newPattern
     * @return
     * @author:zhaokuiqiang
     */
    public static String replace(String inString, String oldPattern, String newPattern) {
        if(hasLength(inString) && hasLength(oldPattern) && newPattern != null) {
            StringBuilder sb = new StringBuilder();
            int pos = 0;
            int index = inString.indexOf(oldPattern);

            for(int patLen = oldPattern.length(); index >= 0; index = inString.indexOf(oldPattern, pos)) {
                sb.append(inString.substring(pos, index));
                sb.append(newPattern);
                pos = index + patLen;
            }

            sb.append(inString.substring(pos));
            return sb.toString();
        } else {
            return inString;
        }
    }

    /**
     * 功能描述：删除字符串
     *
     * @param inString
     * @param pattern  删除的串
     * @return
     */
    public static String delete(String inString, String pattern) {
        return replace(inString, pattern, "");
    }
    
    /**
     * 通过%格式化format
     * @param format
     * @param args
     * @return
     */
    public static String format(String format,String ... args){
        return formatByPattern(format,'%',args);
    }

    /**
     * 通过pattern格式化format
     * @param format
     * @param pattern
     * @param args
     * @return
     */
    public static String formatByPattern(String format,char pattern,String ... args){
        StringBuilder sb = new StringBuilder();
        int loop = 0,
                end = format.length(),
                index = 0,
                start = 0;
        while(index < end){
            if(pattern == format.charAt(index)){
                sb.append(format.substring(start, index));
                if(loop < args.length){
                    sb.append(args[loop]);
                }else{
                    sb.append(pattern);
                }
                loop++;
                start = ++index;
                continue;
            }
            index++;
        }
        if(start != end){
            sb.append(format.substring(start,end));
        }
        //多余的args拼接到最后
        while(loop < args.length){
            sb.append(args[loop]);
            loop++;
        }
        return sb.toString();
    }
	
}
