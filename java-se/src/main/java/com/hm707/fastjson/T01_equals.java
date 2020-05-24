package com.hm707.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class T01_equals {
    public static void main(String[] args) {
        String s1 ="{\n" +
                "    \"questionTitle\":\"sss\",\n" +
                "    \"questionJson\":[\n" +
                "        {\n" +
                "            \"id\":\"980d3f7cac544d61ab34cbebab099472\",\n" +
                "            \"questionName\":\"姓名\",\n" +
                "            \"questionType\":\"fixed\",\n" +
                "            \"markedWords\":\"请输入姓名\",\n" +
                "            \"questionStyle\":null,\n" +
                "            \"answerField\":null,\n" +
                "            \"defaultId\":null,\n" +
                "            \"defaultName\":null,\n" +
                "            \"optionInfos\":[\n" +
                "\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\":\"a4af64d6f6c842f7bc2d43c622d5d249\",\n" +
                "            \"questionName\":\"aabbb\",\n" +
                "            \"questionType\":\"standard\",\n" +
                "            \"markedWords\":\"dddd\",\n" +
                "            \"questionStyle\":\"input\",\n" +
                "            \"answerField\":\"number\",\n" +
                "            \"defaultId\":\"d67a69505c3b4b7c8eff17018844f439\",\n" +
                "            \"defaultName\":\"ceshi 2\",\n" +
                "            \"optionInfos\":[\n" +
                "\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\":\"75607cb9582f42fba57465234021ccdf\",\n" +
                "            \"questionName\":\"111111\",\n" +
                "            \"questionType\":\"standard\",\n" +
                "            \"markedWords\":null,\n" +
                "            \"questionStyle\":\"checkbox\",\n" +
                "            \"answerField\":null,\n" +
                "            \"defaultId\":null,\n" +
                "            \"defaultName\":null,\n" +
                "            \"optionInfos\":[\n" +
                "                {\n" +
                "                    \"id\":\"f8a738aef37741f281cbfd84994c7ad3\",\n" +
                "                    \"optionValue\":\"a\",\n" +
                "                    \"sort\":0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\":\"a5f2616f32c04f23a9900018905d99c2\",\n" +
                "                    \"optionValue\":\"e\",\n" +
                "                    \"sort\":1\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\":\"e729ddbc3c1c4052bc441d38130b8c3d\",\n" +
                "                    \"optionValue\":\"d\",\n" +
                "                    \"sort\":2\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\":\"c80cee126dec4372811ac3304f2df393\",\n" +
                "            \"questionName\":\"ddd\",\n" +
                "            \"questionType\":\"standard\",\n" +
                "            \"markedWords\":null,\n" +
                "            \"questionStyle\":\"radio\",\n" +
                "            \"answerField\":null,\n" +
                "            \"defaultId\":null,\n" +
                "            \"defaultName\":null,\n" +
                "            \"optionInfos\":[\n" +
                "                {\n" +
                "                    \"id\":\"cfa4d7d1d78b41f58f393646cdc7b4c0\",\n" +
                "                    \"optionValue\":\"1\",\n" +
                "                    \"sort\":0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\":\"dce871165af84ee6a66d291e9929b125\",\n" +
                "                    \"optionValue\":\"2\",\n" +
                "                    \"sort\":1\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        String s2 ="{\n" +
                "    \"questionTitle\":\"sss\",\n" +
                "    \"questionJson\":[\n" +
                "        {\n" +
                "            \"id\":\"980d3f7cac544d61ab34cbebab099472\",\n" +
                "            \"questionName\":\"姓名\",\n" +
                "            \"questionType\":\"fixed\",\n" +
                "            \"markedWords\":\"请输入姓名\",\n" +
                "            \"questionStyle\":null,\n" +
                "            \"answerField\":null,\n" +
                "            \"defaultId\":null,\n" +
                "            \"defaultName\":null,\n" +
                "            \"optionInfos\":[\n" +
                "\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\":\"a4af64d6f6c842f7bc2d43c622d5d249\",\n" +
                "            \"questionName\":\"aabbb\",\n" +
                "            \"questionType\":\"standard\",\n" +
                "            \"markedWords\":\"dddd\",\n" +
                "            \"questionStyle\":\"input\",\n" +
                "            \"answerField\":\"number\",\n" +
                "            \"defaultId\":\"d67a69505c3b4b7c8eff17018844f439\",\n" +
                "            \"defaultName\":\"ceshi 2\",\n" +
                "            \"optionInfos\":[\n" +
                "\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\":\"75607cb9582f42fba57465234021ccdf\",\n" +
                "            \"questionName\":\"111111\",\n" +
                "            \"questionType\":\"standard\",\n" +
                "            \"markedWords\":null,\n" +
                "            \"questionStyle\":\"checkbox\",\n" +
                "            \"answerField\":null,\n" +
                "            \"defaultId\":null,\n" +
                "            \"defaultName\":null,\n" +
                "            \"optionInfos\":[\n" +
                "                {\n" +
                "                    \"id\":\"f8a738aef37741f281cbfd84994c7ad3\",\n" +
                "                    \"optionValue\":\"a\",\n" +
                "                    \"sort\":0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\":\"e729ddbc3c1c4052bc441d38130b8c3d\",\n" +
                "                    \"optionValue\":\"d\",\n" +
                "                    \"sort\":2\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\":\"c80cee126dec4372811ac3304f2df393\",\n" +
                "            \"questionName\":\"ddd\",\n" +
                "            \"questionType\":\"standard\",\n" +
                "            \"markedWords\":null,\n" +
                "            \"questionStyle\":\"radio\",\n" +
                "            \"answerField\":null,\n" +
                "            \"defaultId\":null,\n" +
                "            \"defaultName\":null,\n" +
                "            \"optionInfos\":[\n" +
                "                {\n" +
                "                    \"id\":\"cfa4d7d1d78b41f58f393646cdc7b4c0\",\n" +
                "                    \"optionValue\":\"1\",\n" +
                "                    \"sort\":0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\":\"dce871165af84ee6a66d291e9929b125\",\n" +
                "                    \"optionValue\":\"2\",\n" +
                "                    \"sort\":1\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        JSONObject jsonObject1 = JSON.parseObject(s1);
        JSONObject jsonObject2 = JSON.parseObject(s2);

        System.out.println(jsonObject1.equals(jsonObject2));

    }
}
