package com.coderunning.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.Map;

/**
 * @author harryhook
 * @date 2022/9/25 19:57
 * @desc
 */
public class CommentDemo {

    public static void main(String[] args) {

        boolean isLastPage = false;
        //从开发者工具中获取到的第一页的链接
        String url = "https://www.douyin.com/aweme/v1/web/comment/list/?device_platform=webapp&aid=6383&channel=channel_pc_web&aweme_id=7081550085791681822&cursor=20&count=20&item_type=0&insert_ids=&rcFT=AAN3g_udg&pc_client_type=1&version_code=170400&version_name=17.4.0&cookie_enabled=true&screen_width=1440&screen_height=900&browser_language=zh&browser_platform=MacIntel&browser_name=Chrome&browser_version=105.0.0.0&browser_online=true&engine_name=Blink&engine_version=105.0.0.0&os_name=Mac+OS&os_version=10.15.7&cpu_core_num=8&device_memory=8&platform=PC&downlink=10&effective_type=4g&round_trip_time=50&webid=7143181481778333224&msToken=F_lz5zekUBA5NdMNqfx2gwbZPHBWgn5WXq4jMNc7NAzcNNTJFeE0np-jER0qTir8W2iv8R-Ima7IdsKqUkEq028qUVO937ElrBD0USSMKL_Ex-qY3Qw8OewQHIuVRk4=&X-Bogus=DFSzswVuJ0vANHnBSKw//lm4pIDe";
        //从第一页开始循环，直到最后一页
        while (!isLastPage) {
            System.out.println("----------url:" + url);
            Map<String, Object> paramMap = MapUtil.newHashMap();
            paramMap.put("cookie", "wettwid=1|xBgjfh3Z9enM7WwP2ldaBESGFifARj06412xWdCL9gM|1663151552|12c23f724b3c2607aea1630bcac0294decc972cf4356a6e949186932a9f9a507; s_v_web_id=verify_l81hhshl_aZe4Hi6R_jxBo_4mYx_BR7F_3vuPqEmqx1CB; passport_csrf_token=1ca22b9dcf4a52238b892d6c20285822; passport_csrf_token_default=1ca22b9dcf4a52238b892d6c20285822; d_ticket=51e317d11e0355178eecb6a6f05cb3f6bad99; n_mh=Qm87ojE38qqrLrkB_O3Ks30W_9RroMf3Zzi33gD9GAU; sso_auth_status=e7e9ca240ce7e74ae85d1efda6e8828b; sso_auth_status_ss=e7e9ca240ce7e74ae85d1efda6e8828b; sso_uid_tt=b04cc60337fce439ce420a6c6bdefaeb; sso_uid_tt_ss=b04cc60337fce439ce420a6c6bdefaeb; toutiao_sso_user=4e2083137ba5e52a1ac0693ddfc2dfbb; toutiao_sso_user_ss=4e2083137ba5e52a1ac0693ddfc2dfbb; sid_ucp_sso_v1=1.0.0-KGVhNzIzYzljYTNiZDliNGY4MGUyMDFlODhlNDJlYzYxNTJlZGM3MGQKHwiDpbDdgfSeBxDd24aZBhjvMSAMMMH3hugFOAJA8QcaAmxmIiA0ZTIwODMxMzdiYTVlNTJhMWFjMDY5M2RkZmMyZGZiYg; ssid_ucp_sso_v1=1.0.0-KGVhNzIzYzljYTNiZDliNGY4MGUyMDFlODhlNDJlYzYxNTJlZGM3MGQKHwiDpbDdgfSeBxDd24aZBhjvMSAMMMH3hugFOAJA8QcaAmxmIiA0ZTIwODMxMzdiYTVlNTJhMWFjMDY5M2RkZmMyZGZiYg; odin_tt=a302865927abb0163c1f065871e8fed2119a353eb7777d56e3e8cb1196dccbc319a7e301da2ad4b563077df364dfa6132d1f33a462b88f99764adeb5d2579703; passport_auth_status=71a85e888da6381ade805a6a6ab0fd45,857451cb5fb1f8e4e09bf345f3bbf3ab; passport_auth_status_ss=71a85e888da6381ade805a6a6ab0fd45,857451cb5fb1f8e4e09bf345f3bbf3ab; sid_guard=659da11a5728469b0e6b060d040dfc87|1663151581|5184000|Sun,+13-Nov-2022+10:33:01+GMT; uid_tt=e1457f45dfbefb0e021afabec8ca8f8f; uid_tt_ss=e1457f45dfbefb0e021afabec8ca8f8f; sid_tt=659da11a5728469b0e6b060d040dfc87; sessionid=659da11a5728469b0e6b060d040dfc87; sessionid_ss=659da11a5728469b0e6b060d040dfc87; sid_ucp_v1=1.0.0-KDAxYWQyYWNhYmNjODg5MjkzMmNjNzA5OTI3MjRhZGU4YTE0Njc0NGEKGQiDpbDdgfSeBxDd24aZBhjvMSAMOAJA8QcaAmxxIiA2NTlkYTExYTU3Mjg0NjliMGU2YjA2MGQwNDBkZmM4Nw; ssid_ucp_v1=1.0.0-KDAxYWQyYWNhYmNjODg5MjkzMmNjNzA5OTI3MjRhZGU4YTE0Njc0NGEKGQiDpbDdgfSeBxDd24aZBhjvMSAMOAJA8QcaAmxxIiA2NTlkYTExYTU3Mjg0NjliMGU2YjA2MGQwNDBkZmM4Nw; FOLLOW_NUMBER_YELLOW_POINT_INFO=\"MS4wLjABAAAAXYMdU6EZgBtk5GzT_jYNrzzwXSopzDl5D4LFLQwLnWTtPRAm1gLu2KfPfMy8CPsV/1663689600000/0/1663670274601/0\"; __ac_nonce=063304a0200a48f63672c; __ac_signature=_02B4Z6wo00f01GZ2FZAAAIDA.h2C4yA9BeBmVhEAAHq1zMdqY1qpRJfQ5ucRu88Gnr9-nu38C9mGXgAurOEgjJKj1cRZPQZv2jMZHKHVoIusMvor4O0EcrFo6Di.DxVIcqw2IVymDCFSGrV-7e; strategyABtestKey=1664109059.482; download_guide=\"1/20220925\"; msToken=dDSeXQaMCGBQdi-DUCFINl_lYqO2T_V8dv2R2T43Hnmpc2sBTge_HeF7wIFEoXNywXzdEeVbVBoJ7Pp48CtderDi_CfhBsgDvxLee8RmFcobzBcQ5kSOGeqoVDT4d6E=; home_can_add_dy_2_desktop=\"1\"; msToken=F_lz5zekUBA5NdMNqfx2gwbZPHBWgn5WXq4jMNc7NAzcNNTJFeE0np-jER0qTir8W2iv8R-Ima7IdsKqUkEq028qUVO937ElrBD0USSMKL_Ex-qY3Qw8OewQHIuVRk4=; tt_scid=0oAjoTVP0Bd4GczSvnwcCR9UL.Qw2Xf-7sHaMzT8ktaAl.LE9Q2pK4gQ-PYzjJhf0d4abapp");
            paramMap.put("referral", "https://www.douyin.com/user/MS4wLjABAAAAXmTofy3tudReliVtnPJ5w5TgZ0X3WeoW90HFBCYmDGw_CNvHca5ZUlox-QoBwd7L?modal_id=7081550085791681822");
            String result = HttpUtil.get(url, paramMap);
            System.out.printf("result:" + result);

            JSONObject jsonobject = JSONUtil.parseObj(result);

            String comments = jsonobject.get("comments").toString();
            System.out.printf(comments);

//            //1 是否最后一页
//            isLastPage = (boolean) JSONUtil.parseObj(jsonobject.get("comments")).get("is_end");
//
//            //取出下一页的URL
//            url = (String) JSONUtil.parseObj(jsonobject.get("paging")).get("next");
//
//            //解析data数组
//            JSONArray jsonArray = JSONUtil.parseArray(jsonobject.get("data"));
//            for (Object rootCommentsData : jsonArray) {
//                JSONObject rootCommentsArray = JSONUtil.parseObj(rootCommentsData);
//                String authorName = (String) JSONUtil.parseObj(JSONUtil.parseObj(rootCommentsArray.get("author")).get("member")).get("name");
//                String content = (String) rootCommentsArray.get("content");
//                int voteCount = (int) rootCommentsArray.get("vote_count");
//                int createdTime = (Integer) rootCommentsArray.get("created_time");
//                System.out.println(authorName + "" + content + "" + voteCount + "" + DateUtil.format(new DateTime(createdTime), "yyyy-MM-dd HH:mm:ss"));
//            }
        }
    }
}
