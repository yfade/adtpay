package com.pay.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class TestRest {
    public static void main(String[] args) {
        String yz = "日本、新加坡、韩国、亚美尼亚、塞浦路斯、格鲁吉亚、土耳其、阿富汗、阿塞拜疆、巴林、孟加拉国、不丹、文莱、" +
                "柬埔寨、东帝汶、中国香港、印度、印度尼西亚、伊朗、伊拉克、以色列、约旦、哈萨克斯坦、科威特、吉尔吉斯斯坦、老挝、" +
                "黎巴嫩、澳门、马来西亚、马尔代夫、蒙古、缅甸、尼泊尔、阿曼、巴基斯坦、巴勒斯坦、菲律宾、卡塔尔、沙特阿拉伯、" +
                "叙利亚、中国台湾、塔吉克斯坦、泰国、土库曼斯坦、阿联酋、乌兹别克斯坦、越南、也门、关岛、斯里兰卡、中国、朝鲜";
        String[] yzArr = yz.replace("\n", "").split("、");
        Set<String> yzSet = new HashSet<>(Arrays.asList(yzArr));

        String oz = "英国、法国、德国、意大利、俄罗斯、荷兰、挪威、波兰、葡萄牙、罗马尼亚、奥地利、西班牙、瑞典、瑞士、乌克兰、" +
                "白俄罗斯、比利时、波斯尼亚、保加利亚、克罗地亚、捷克、丹麦、爱沙尼亚、芬兰、希腊、匈牙利、冰岛、爱尔兰、马恩岛、" +
                "拉脱维亚、列支敦士登、立陶宛、卢森堡、马其顿、马耳他、摩尔多瓦、摩纳哥、圣马力诺、塞尔维亚、黑山共和国、斯洛伐克、" +
                "斯洛文尼亚、阿尔巴尼亚、安道尔、加那利群岛、海峡群岛、法罗群岛、梵蒂冈、科索沃、圣巴泰勒米岛";
        String[] ozArr = oz.replace("\n", "").split("、");
        Set<String> ozSet = new HashSet<>(Arrays.asList(ozArr));

        String fz = "直布罗陀、阿尔及利亚、安哥拉、贝宁、博茨瓦纳、布基纳法索、布隆迪、喀麦隆、佛得角、中非共和国、休达、乍得、" +
                "科摩罗、刚果、吉布提、埃及、赤道几内亚、厄立特里亚、埃塞俄比亚、加蓬、加纳、冈比亚、几内亚、几内亚比绍共和国、" +
                "科特迪瓦、肯尼亚、莱索托、利比亚、利比里亚、马达加斯加、马拉维、马里、毛里塔尼亚、毛里求斯、马约特岛、梅利利亚、" +
                "摩洛哥、莫桑比克、纳米比亚、尼日尔、尼日利亚、留尼旺岛、卢旺达、撒哈拉沙漠、圣多美和普林西比、塞内加尔、塞舌尔、" +
                "塞拉利昂、索马里、索马里兰、南非、圣海伦娜、苏丹、斯威士兰、坦桑尼亚、多哥、突尼斯、乌干达、西撒哈拉、扎伊尔、" +
                "赞比亚、桑给巴尔岛、津巴布韦、刚果民主共和国、刚果共和国、南苏丹";
        String[] fzArr = fz.replace("\n", "").split("、");
        Set<String> fzSet = new HashSet<>(Arrays.asList(fzArr));

        String nmz = "美国、加拿大、哥斯达黎加、古巴、牙买加、墨西哥、尼加拉瓜、巴拿马、" +
                "格陵兰岛、马提尼克、安圭拉岛、蒙特塞拉特、安提瓜和巴布达、安德烈斯群岛、阿鲁巴、巴哈马、" +
                "巴巴多斯、伯利兹、百慕大群岛、博奈尔、加勒比海岛、开曼群岛、库拉索、多米尼加、多米尼加共和国、" +
                "萨尔瓦多、格林纳达、瓜德罗普岛、危地马拉、海地、洪都拉斯、波多黎各、萨巴、圣基茨和尼维斯、圣文森特和格林纳丁斯、" +
                "法属、荷属圣马丁、托托拉岛、特立尼达和多巴哥、特克斯和凯科斯群岛、美属维尔京群岛、维尔京群岛、" +
                "圣卢西亚、法属圣马丁、圣皮埃尔和密克隆、圣尤斯特歇斯、英属维尔京群岛";
        String[] nmzArr = nmz.replace("\n", "").split("、");
        Set<String> nmzSet = new HashSet<>(Arrays.asList(nmzArr));

        String smz = "阿根廷、玻利维亚、巴西、智利、哥伦比亚、厄瓜多尔、福克兰群岛（马尔维纳斯）、法属圭亚那、圭亚那、巴拉圭、秘鲁、南乔治亚岛和南桑威奇群岛、苏里南、乌拉圭、委内瑞拉";
        String[] smzArr = smz.replace("\n", "").split("、");
        Set<String> smzSet = new HashSet<>(Arrays.asList(smzArr));

        String dyz = "澳大利亚、库克群岛、斐济、法属波利尼西亚、甘比尔群岛、基里巴斯、马绍尔群岛、瑙鲁、新喀里多尼亚、新西兰、" +
                "诺福克岛、帕劳、巴布亚新几内亚、萨摩亚、社会群岛、所罗门群岛、汤加、土阿莫土群岛、瓦利斯和富图纳群岛、瓦努阿图共和国、" +
                "图瓦卢、美属萨摩亚、圣诞岛、密克罗尼西亚联邦、纽埃、北马里亚纳群岛、托克劳、威克岛、中途岛、加罗林群岛、阿德默勒尔蒂群岛";
        String[] dyzArr = dyz.replace("\n", "").split("、");
        Set<String> dyzSet = new HashSet<>(Arrays.asList(dyzArr));

        List<JSONObject> yzList = new ArrayList<>();
        List<JSONObject> ozList = new ArrayList<>();
        List<JSONObject> fzList = new ArrayList<>();
        List<JSONObject> nmzList = new ArrayList<>();
        List<JSONObject> smzList = new ArrayList<>();
        List<JSONObject> dyzList = new ArrayList<>();

        List<JSONObject> remainList = new ArrayList<>();

        List<JSONObject> array = JSONObject.parseArray(json, JSONObject.class);

        for (JSONObject object : array) {
            String name = object.getString("name");

            if (yzSet.contains(name)) {
                yzList.add(object);
            } else if (ozSet.contains(name)) {
                ozList.add(object);
            } else if (fzSet.contains(name)) {
                fzList.add(object);
            } else if (nmzSet.contains(name)) {
                nmzList.add(object);
            } else if (smzSet.contains(name)) {
                smzList.add(object);
            } else if (dyzSet.contains(name)) {
                dyzList.add(object);
            } else {
                remainList.add(object);
            }
        }

        System.out.println(ozList.size() + yzList.size() + nmzList.size() + smzList.size() + dyzList.size() + fzList.size());


        //排序
        String[] oS = "德国、英国、法国、意大利、俄罗斯、西班牙、荷兰、瑞士、瑞典、波兰、比利时、奥地利、挪威、爱尔兰".split("、");
        List<JSONObject> ozRes = new ArrayList<>();
        sortList(ozList, ozRes, oS);

        String[] yS = "日本、印度、韩国、印度尼西亚、沙特阿拉伯、土耳其、中国台湾、泰国、阿联酋、伊朗、以色列、中国香港、马来西亚、新加坡".split("、");
        List<JSONObject> yzRes = new ArrayList<>();
        sortList(yzList, yzRes, yS);

        String[] nmS = "美国、加拿大、墨西哥、巴拿马".split("、");
        List<JSONObject> nmRes = new ArrayList<>();
        sortList(nmzList, nmRes, nmS);

        String[] smS = "巴西、阿根廷、秘鲁、委内瑞拉、厄瓜多尔".split("、");
        List<JSONObject> smRes = new ArrayList<>();
        sortList(smzList, smRes, smS);

        String[] dyS = "澳大利亚、新西兰".split("、");
        List<JSONObject> dyRes = new ArrayList<>();
        sortList(dyzList, dyRes, dyS);

        String[] fzS = "尼日利亚、南非、埃及".split("、");
        List<JSONObject> fzRes = new ArrayList<>();
        sortList(fzList, fzRes, fzS);


        JSONArray jsonArray = new JSONArray();
        JSONObject ozObj = new JSONObject();
        ozObj.put("name", "欧洲");
        ozObj.put("code", "");
        ozObj.put("childList", ozRes);
        JSONObject yzObj = new JSONObject();
        yzObj.put("name", "亚洲");
        yzObj.put("code", "");
        yzObj.put("childList", yzRes);
        JSONObject nmzObj = new JSONObject();
        nmzObj.put("name", "北美洲");
        nmzObj.put("code", "");
        nmzObj.put("childList", nmRes);
        JSONObject smzObj = new JSONObject();
        smzObj.put("name", "南美洲");
        smzObj.put("code", "");
        smzObj.put("childList", smRes);
        JSONObject dyzObj = new JSONObject();
        dyzObj.put("name", "大洋洲");
        dyzObj.put("code", "");
        dyzObj.put("childList", dyRes);
        JSONObject fzObj = new JSONObject();
        fzObj.put("name", "非洲");
        fzObj.put("code", "");
        fzObj.put("childList", fzRes);

        jsonArray.add(ozObj);
        jsonArray.add(yzObj);
        jsonArray.add(nmzObj);
        jsonArray.add(smzObj);
        jsonArray.add(dyzObj);
        jsonArray.add(fzObj);

        System.out.println("=====================");
        System.out.println(jsonArray.toJSONString());
    }

    private static void sortList(List<JSONObject> sourceList, List<JSONObject> resList, String[] arr) {
        for (String o : arr) {
            for (int i = sourceList.size() - 1; i >= 0; i--) {
                if (o.equals(sourceList.get(i).getString("name"))) {
                    resList.add(sourceList.get(i));
                    sourceList.remove(i);
                    break;
                }
            }
        }
        resList.addAll(sourceList);
    }

    private static String json = "[\n" +
            "  {\n" +
            "    \"name\": \"阿德默勒尔蒂群岛\",\n" +
            "    \"code\": \"004\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"阿富汗\",\n" +
            "    \"code\": \"AFG\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"阿尔巴尼亚\",\n" +
            "    \"code\": \"ALB\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"阿尔及利亚\",\n" +
            "    \"code\": \"DZA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"美属萨摩亚\",\n" +
            "    \"code\": \"ASM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"安道尔\",\n" +
            "    \"code\": \"AND\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"安哥拉\",\n" +
            "    \"code\": \"AGO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"安圭拉岛\",\n" +
            "    \"code\": \"AIA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"安提瓜和巴布达\",\n" +
            "    \"code\": \"ATG\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"阿根廷\",\n" +
            "    \"code\": \"ARG\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"亚美尼亚\",\n" +
            "    \"code\": \"ARM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"阿鲁巴\",\n" +
            "    \"code\": \"ABW\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"澳大利亚\",\n" +
            "    \"code\": \"AUS\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"奥地利\",\n" +
            "    \"code\": \"AUT\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"阿塞拜疆\",\n" +
            "    \"code\": \"AZE\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"巴哈马\",\n" +
            "    \"code\": \"BHS\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"巴林\",\n" +
            "    \"code\": \"BHR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"孟加拉国\",\n" +
            "    \"code\": \"BGD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"巴巴多斯\",\n" +
            "    \"code\": \"BRB\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"白俄罗斯\",\n" +
            "    \"code\": \"BLR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"比利时\",\n" +
            "    \"code\": \"BEL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"伯利兹\",\n" +
            "    \"code\": \"BLZ\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"贝宁\",\n" +
            "    \"code\": \"BEN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"百慕大群岛\",\n" +
            "    \"code\": \"BMU\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"不丹\",\n" +
            "    \"code\": \"BTN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"玻利维亚\",\n" +
            "    \"code\": \"BOL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"博奈尔\",\n" +
            "    \"code\": \"001\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"波斯尼亚\",\n" +
            "    \"code\": \"BIH\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"博茨瓦纳\",\n" +
            "    \"code\": \"BWA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"巴西\",\n" +
            "    \"code\": \"BRA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"文莱\",\n" +
            "    \"code\": \"BRN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"保加利亚\",\n" +
            "    \"code\": \"BGR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"布基纳法索\",\n" +
            "    \"code\": \"BFA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"布隆迪\",\n" +
            "    \"code\": \"BDI\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"柬埔寨\",\n" +
            "    \"code\": \"KHM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"喀麦隆\",\n" +
            "    \"code\": \"CMR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"加拿大\",\n" +
            "    \"code\": \"CAN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"佛得角\",\n" +
            "    \"code\": \"CPV\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"加罗林群岛\",\n" +
            "    \"code\": \"006\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"开曼群岛\",\n" +
            "    \"code\": \"CYM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"中非共和国\",\n" +
            "    \"code\": \"CAF\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"乍得\",\n" +
            "    \"code\": \"TCD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"海峡群岛\",\n" +
            "    \"code\": \"010\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"智利\",\n" +
            "    \"code\": \"CHL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"中国\",\n" +
            "    \"code\": \"CHN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"圣诞岛\",\n" +
            "    \"code\": \"CXR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"哥伦比亚\",\n" +
            "    \"code\": \"COL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"科摩罗\",\n" +
            "    \"code\": \"COM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"刚果民主共和国\",\n" +
            "    \"code\": \"COD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"刚果共和国\",\n" +
            "    \"code\": \"COG\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"库克群岛\",\n" +
            "    \"code\": \"COK\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"哥斯达黎加\",\n" +
            "    \"code\": \"CRI\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"科特迪瓦\",\n" +
            "    \"code\": \"CIV\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"克罗地亚\",\n" +
            "    \"code\": \"HRV\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"古巴\",\n" +
            "    \"code\": \"CUB\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"库拉索\",\n" +
            "    \"code\": \"CUW\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"塞浦路斯\",\n" +
            "    \"code\": \"CYP\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"捷克\",\n" +
            "    \"code\": \"CZE\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"丹麦\",\n" +
            "    \"code\": \"DNK\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"吉布提\",\n" +
            "    \"code\": \"DJI\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"多米尼加\",\n" +
            "    \"code\": \"DMA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"多米尼加共和国\",\n" +
            "    \"code\": \"DOM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"厄瓜多尔\",\n" +
            "    \"code\": \"ECU\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"埃及\",\n" +
            "    \"code\": \"EGY\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"萨尔瓦多\",\n" +
            "    \"code\": \"SLV\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"赤道几内亚\",\n" +
            "    \"code\": \"GNQ\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"厄立特里亚\",\n" +
            "    \"code\": \"ERI\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"爱沙尼亚\",\n" +
            "    \"code\": \"EST\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"埃塞俄比亚\",\n" +
            "    \"code\": \"ETH\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"福克兰群岛（马尔维纳斯）\",\n" +
            "    \"code\": \"FLK\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"法罗群岛\",\n" +
            "    \"code\": \"FRO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"斐济\",\n" +
            "    \"code\": \"FJI\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"芬兰\",\n" +
            "    \"code\": \"FIN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"法国\",\n" +
            "    \"code\": \"FRA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"法属圭亚那\",\n" +
            "    \"code\": \"GUF\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"法属波利尼西亚\",\n" +
            "    \"code\": \"PYF\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"加蓬\",\n" +
            "    \"code\": \"GAB\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"冈比亚\",\n" +
            "    \"code\": \"GMB\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"格鲁吉亚\",\n" +
            "    \"code\": \"GEO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"德国\",\n" +
            "    \"code\": \"DEU\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"加纳\",\n" +
            "    \"code\": \"GHA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"直布罗陀\",\n" +
            "    \"code\": \"GIB\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"希腊\",\n" +
            "    \"code\": \"GRC\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"格陵兰岛\",\n" +
            "    \"code\": \"GRL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"格林纳达\",\n" +
            "    \"code\": \"GRD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"瓜德罗普岛\",\n" +
            "    \"code\": \"GLP\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"关岛\",\n" +
            "    \"code\": \"GUM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"危地马拉\",\n" +
            "    \"code\": \"GTM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"几内亚\",\n" +
            "    \"code\": \"GIN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"几内亚比绍共和国\",\n" +
            "    \"code\": \"GNB\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"圭亚那\",\n" +
            "    \"code\": \"GUY\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"海地\",\n" +
            "    \"code\": \"HTI\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"洪都拉斯\",\n" +
            "    \"code\": \"HND\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"中国香港\",\n" +
            "    \"code\": \"HKG\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"匈牙利\",\n" +
            "    \"code\": \"HUN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"冰岛\",\n" +
            "    \"code\": \"ISL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"印度\",\n" +
            "    \"code\": \"IND\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"印度尼西亚\",\n" +
            "    \"code\": \"IDN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"伊朗\",\n" +
            "    \"code\": \"IRN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"伊拉克\",\n" +
            "    \"code\": \"IRQ\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"爱尔兰\",\n" +
            "    \"code\": \"IRL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"马恩岛\",\n" +
            "    \"code\": \"IMN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"以色列\",\n" +
            "    \"code\": \"ISR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"意大利\",\n" +
            "    \"code\": \"ITA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"牙买加\",\n" +
            "    \"code\": \"JAM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"日本\",\n" +
            "    \"code\": \"JPN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"约旦\",\n" +
            "    \"code\": \"JOR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"哈萨克斯坦\",\n" +
            "    \"code\": \"KAZ\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"肯尼亚\",\n" +
            "    \"code\": \"KEN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"基里巴斯\",\n" +
            "    \"code\": \"KIR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"韩国\",\n" +
            "    \"code\": \"KOR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"朝鲜\",\n" +
            "    \"code\": \"PRK\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"科索沃\",\n" +
            "    \"code\": \"009\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"科威特\",\n" +
            "    \"code\": \"KWT\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"吉尔吉斯斯坦\",\n" +
            "    \"code\": \"KGZ\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"老挝\",\n" +
            "    \"code\": \"LAO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"拉脱维亚\",\n" +
            "    \"code\": \"LVA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"黎巴嫩\",\n" +
            "    \"code\": \"LBN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"莱索托\",\n" +
            "    \"code\": \"LSO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"利比里亚\",\n" +
            "    \"code\": \"LBR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"利比亚\",\n" +
            "    \"code\": \"LBY\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"列支敦士登\",\n" +
            "    \"code\": \"LIE\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"立陶宛\",\n" +
            "    \"code\": \"LTU\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"卢森堡\",\n" +
            "    \"code\": \"LUX\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"澳门\",\n" +
            "    \"code\": \"MAC\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"马其顿\",\n" +
            "    \"code\": \"MKD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"马达加斯加\",\n" +
            "    \"code\": \"MDG\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"马拉维\",\n" +
            "    \"code\": \"MWI\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"马来西亚\",\n" +
            "    \"code\": \"MYS\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"马尔代夫\",\n" +
            "    \"code\": \"MDV\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"马里\",\n" +
            "    \"code\": \"MLI\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"马耳他\",\n" +
            "    \"code\": \"MLT\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"马绍尔群岛\",\n" +
            "    \"code\": \"MHL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"马提尼克\",\n" +
            "    \"code\": \"MTQ\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"毛里塔尼亚\",\n" +
            "    \"code\": \"MRT\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"毛里求斯\",\n" +
            "    \"code\": \"MUS\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"马约特岛\",\n" +
            "    \"code\": \"MYT\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"墨西哥\",\n" +
            "    \"code\": \"MEX\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"密克罗尼西亚联邦\",\n" +
            "    \"code\": \"FSM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"中途岛\",\n" +
            "    \"code\": \"007\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"摩尔多瓦\",\n" +
            "    \"code\": \"MDA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"摩纳哥\",\n" +
            "    \"code\": \"MCO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"蒙古\",\n" +
            "    \"code\": \"MNG\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"黑山共和国\",\n" +
            "    \"code\": \"MNE\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"蒙特塞拉特\",\n" +
            "    \"code\": \"MSR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"摩洛哥\",\n" +
            "    \"code\": \"MAR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"莫桑比克\",\n" +
            "    \"code\": \"MOZ\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"缅甸\",\n" +
            "    \"code\": \"MMR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"纳米比亚\",\n" +
            "    \"code\": \"NAM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"瑙鲁\",\n" +
            "    \"code\": \"NRU\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"尼泊尔\",\n" +
            "    \"code\": \"NPL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"荷兰\",\n" +
            "    \"code\": \"NLD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"新喀里多尼亚\",\n" +
            "    \"code\": \"NCL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"新西兰\",\n" +
            "    \"code\": \"NZL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"尼加拉瓜\",\n" +
            "    \"code\": \"NIC\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"尼日尔\",\n" +
            "    \"code\": \"NER\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"尼日利亚\",\n" +
            "    \"code\": \"NGA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"纽埃\",\n" +
            "    \"code\": \"NIU\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"诺福克岛\",\n" +
            "    \"code\": \"NFK\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"北马里亚纳群岛\",\n" +
            "    \"code\": \"MNP\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"挪威\",\n" +
            "    \"code\": \"NOR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"阿曼\",\n" +
            "    \"code\": \"OMN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"巴基斯坦\",\n" +
            "    \"code\": \"PAK\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"帕劳\",\n" +
            "    \"code\": \"PLW\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"巴勒斯坦\",\n" +
            "    \"code\": \"PSE\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"巴拿马\",\n" +
            "    \"code\": \"PAN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"巴布亚新几内亚\",\n" +
            "    \"code\": \"PNG\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"巴拉圭\",\n" +
            "    \"code\": \"PRY\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"秘鲁\",\n" +
            "    \"code\": \"PER\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"菲律宾\",\n" +
            "    \"code\": \"PHL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"波兰\",\n" +
            "    \"code\": \"POL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"葡萄牙\",\n" +
            "    \"code\": \"PRT\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"波多黎各\",\n" +
            "    \"code\": \"PRI\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"卡塔尔\",\n" +
            "    \"code\": \"QAT\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"留尼旺岛\",\n" +
            "    \"code\": \"REU\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"罗马尼亚\",\n" +
            "    \"code\": \"ROU\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"俄罗斯\",\n" +
            "    \"code\": \"RUS\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"卢旺达\",\n" +
            "    \"code\": \"RWA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"萨巴\",\n" +
            "    \"code\": \"002\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"圣巴泰勒米岛\",\n" +
            "    \"code\": \"BLM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"圣海伦娜\",\n" +
            "    \"code\": \"SHN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"圣基茨和尼维斯\",\n" +
            "    \"code\": \"KNA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"圣卢西亚\",\n" +
            "    \"code\": \"LCA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"法属圣马丁\",\n" +
            "    \"code\": \"MAF\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"圣皮埃尔和密克隆\",\n" +
            "    \"code\": \"SPM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"圣文森特和格林纳丁斯\",\n" +
            "    \"code\": \"VCT\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"萨摩亚\",\n" +
            "    \"code\": \"WSM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"圣多美和普林西比\",\n" +
            "    \"code\": \"STP\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"沙特阿拉伯\",\n" +
            "    \"code\": \"SAU\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"塞内加尔\",\n" +
            "    \"code\": \"SEN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"塞尔维亚\",\n" +
            "    \"code\": \"SRB\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"塞舌尔\",\n" +
            "    \"code\": \"SYC\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"塞拉利昂\",\n" +
            "    \"code\": \"SLE\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"新加坡\",\n" +
            "    \"code\": \"SGP\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"圣尤斯特歇斯\",\n" +
            "    \"code\": \"003\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"荷属圣马丁\",\n" +
            "    \"code\": \"SXM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"斯洛伐克\",\n" +
            "    \"code\": \"SVK\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"斯洛文尼亚\",\n" +
            "    \"code\": \"SVN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"所罗门群岛\",\n" +
            "    \"code\": \"SLB\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"索马里\",\n" +
            "    \"code\": \"SOM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"南非\",\n" +
            "    \"code\": \"ZAF\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"南乔治亚岛和南桑威奇群岛\",\n" +
            "    \"code\": \"SGS\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"南苏丹\",\n" +
            "    \"code\": \"SSD\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"西班牙\",\n" +
            "    \"code\": \"ESP\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"斯里兰卡\",\n" +
            "    \"code\": \"LKA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"苏丹\",\n" +
            "    \"code\": \"SDN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"苏里南\",\n" +
            "    \"code\": \"SUR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"斯威士兰\",\n" +
            "    \"code\": \"SWZ\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"瑞典\",\n" +
            "    \"code\": \"SWE\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"瑞士\",\n" +
            "    \"code\": \"CHE\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"叙利亚\",\n" +
            "    \"code\": \"SYR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"中国台湾\",\n" +
            "    \"code\": \"TWN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"塔吉克斯坦\",\n" +
            "    \"code\": \"TJK\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"坦桑尼亚\",\n" +
            "    \"code\": \"TZA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"泰国\",\n" +
            "    \"code\": \"THA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"东帝汶\",\n" +
            "    \"code\": \"TLS\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"多哥\",\n" +
            "    \"code\": \"TGO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"托克劳\",\n" +
            "    \"code\": \"TKL\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"汤加\",\n" +
            "    \"code\": \"TON\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"特立尼达和多巴哥\",\n" +
            "    \"code\": \"TTO\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"突尼斯\",\n" +
            "    \"code\": \"TUN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"土耳其\",\n" +
            "    \"code\": \"TUR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"土库曼斯坦\",\n" +
            "    \"code\": \"TKM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"特克斯和凯科斯群岛\",\n" +
            "    \"code\": \"TCA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"图瓦卢\",\n" +
            "    \"code\": \"TUV\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"乌干达\",\n" +
            "    \"code\": \"UGA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"乌克兰\",\n" +
            "    \"code\": \"UKR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"阿联酋\",\n" +
            "    \"code\": \"ARE\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"英国\",\n" +
            "    \"code\": \"GBR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"美国\",\n" +
            "    \"code\": \"USA\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"乌拉圭\",\n" +
            "    \"code\": \"URY\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"乌兹别克斯坦\",\n" +
            "    \"code\": \"UZB\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"瓦努阿图共和国\",\n" +
            "    \"code\": \"VUT\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"委内瑞拉\",\n" +
            "    \"code\": \"VEN\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"越南\",\n" +
            "    \"code\": \"VNM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"英属维尔京群岛\",\n" +
            "    \"code\": \"VGB\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"美属维尔京群岛\",\n" +
            "    \"code\": \"VIR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"威克岛\",\n" +
            "    \"code\": \"008\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"瓦利斯和富图纳群岛\",\n" +
            "    \"code\": \"WLF\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"西撒哈拉\",\n" +
            "    \"code\": \"ESH\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"也门\",\n" +
            "    \"code\": \"YEM\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"赞比亚\",\n" +
            "    \"code\": \"ZMB\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"津巴布韦\",\n" +
            "    \"code\": \"ZWE\"\n" +
            "  }\n" +
            "]";
}