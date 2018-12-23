import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

public class TestJson {


    public static void main(String[] args) {
        String json = "{\n" +
                "    \"fvalue\" : \"-1\"}";

        JSONObject jsonObject = JSONObject.parseObject(json);
        Object object = jsonObject.get("fvalue");
        if (object instanceof BigDecimal) {
            System.out.println(((BigDecimal) object).doubleValue());
        }

    }
}
