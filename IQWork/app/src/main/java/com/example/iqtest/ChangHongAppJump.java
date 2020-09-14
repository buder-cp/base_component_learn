package com.example.iqtest;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class ChangHongAppJump {

    public static void testChangHongApp(final Context context, View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * start service
                 */
                //全部设置 OK
                Intent intent = new Intent();
                intent.setPackage("com.changhong.easysetting");
                intent.setAction("Changhong.EasySetting");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("MainPage", true);
                context.startService(intent);

                //信号源 OK
//                Intent intent1 = new Intent();
//                intent1.setComponent(new ComponentName("com.changhong.source",
//                        "com.changhong.source.SourceService"));
//                intent1.putExtra("KeyCode", 2001);
//                context.startService(intent1);


                /**
                 * start activity
                 */


                //多屏互动 OK，本地
                //代码中的跳转使用startActivity
//                Intent intent = context.getPackageManager()
//                        .getLaunchIntentForPackage("com.changhong.aiqiyi.MultiScrInteraction");
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);

                //多屏互动，云端配置，投屏配置上，需要使用 action + 参数，才可以跳转到对应TAB
//                Intent intent = new Intent();
//                intent.setAction("com.changhong.aiqiyi.MultiScrInteraction.select_page");
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("multi_scr_select_page", 9);
//                context.startActivity(intent);



                //一键优化 OK
//                Intent intent2 = new Intent();
////                intent2.setPackage("com.changhong.tvmanager");//这里无需包名
//                intent2.setAction("com.changhong.tvmanager.BoostOnClick");
//                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent2);

                //电视管家 OK
//                Intent intent = context.getPackageManager()
//                        .getLaunchIntentForPackage("com.changhong.tvmanager");
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);

                //系统升级 OK
//                Intent intent = context.getPackageManager()
//                        .getLaunchIntentForPackage("com.changhong.systemupgrade");
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);

                //玩机指南 OK
//                Intent intent3 = context.getPackageManager()
//                        .getLaunchIntentForPackage("com.changhong.tvintroduction");
//                intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent3);

                //全部应用 不行
//                Intent intent = context.getPackageManager()
//                        .getLaunchIntentForPackage("com.changhong.cuk.CHAppManager");
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//
//                Intent intent1 = new Intent();
//                intent1.setComponent(new ComponentName("com.changhong.cuk.CHAppManager",
//                        "com.changhong.cuk.CHAppManager.AppManagerActivity"));
//                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent1);

                /**
                 * 聚合card -- 好剧多多
                 */

                //跳转好剧多多首页 OK
//                Intent intent3 = context.getPackageManager()
//                        .getLaunchIntentForPackage("com.changhong.video.home");
//                intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent3);

                //好剧多多分类 填写参数无效，和首页跳转一致
//                Intent intent = new Intent("com.changhong.video.home");
//                intent.setComponent(new ComponentName("com.changhong.video.home",
//                        "com.changhong.video.home.MainActivity"));
//                intent.putExtra("columnName", "少儿");
//                intent.putExtra("subColumnName", "儿歌专区");
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);

                //乐趣后台配置参考
//                Intent intent = context.getPackageManager()
//                        .getLaunchIntentForPackage("com.changhong.video.home");
//                Intent intent = new Intent();
//                intent.setAction("com.changhong.video.home");
//                intent.putExtra("columnName", "动漫");
//                intent.putExtra("subColumnName", "日本动漫");
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);


                //消息中心OK
//                Intent intent = new Intent();
//                intent.setAction("com.changhong.chnoticesystem.noticelist");
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
//                        Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED |
//                        Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                        Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                startActivity(intent);


            }
        });
    }

}
