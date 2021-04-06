package jw.srb.core.hfb;

import java.util.Iterator;
import java.util.Map;

public class FormHelper {

    /**
     * 构建自动提交form表单
     * @param url 表单提交的url
     * @param paramMap 表单的提交项
     * @return
     */
    public static String buildForm(String url, Map<String, Object> paramMap) {

        StringBuffer inputStr = new StringBuffer();
        Iterator<Map.Entry<String, Object>> entries = paramMap.entrySet().iterator();
        while(entries.hasNext()){
            Map.Entry<String, Object> entry = entries.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            inputStr.append("<input type='hidden' name='"+key+"' value='"+value+"'/>");
        }

        String formStr = "<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "<head>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form name=\"form\" action=\""+url+"\" method=\"post\">\n"+

                inputStr +

                "</form>\n" +
                "<script>\n" +
                "\tdocument.form.submit();\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>";
        return formStr;
    }

}
