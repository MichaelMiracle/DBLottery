package com.miracle.sport.common.constant;

/**********************************************************
 * @文件名称：UrlConstants.java
 **********************************************************/
public class UrlConstants {

    public static final String qqUrl = "mqq://im/chat?chat_type=wpa&uin=%1$s&version=1&src_type=web";//qq聊天
    public final static String wxUrl = "vnd.android.cursor.item/vnd.com.tencent.mm.chatting.profile";//微信聊天
    public static final int REQUESTCODE = 1001;
    public static final String baseUrl ="http://8.988lhkj.com/" ;
//    public static final String baseUrl ="http://xiaozhuang.988lhkj.com/" ;



     public static final String POST_SPORT_TYPE=baseUrl+"home/sport/type";
     public static final String POST_SPORT_LIST=baseUrl+"home/sport/sportlist";



    /**登录接口*/
    public static final String POST_LOGIN_DATA = "http://9.988lhkj.com/loginSet";
    /**注册*/
    public static final String POST_REGISTER_DATA = "http://9.988lhkj.com/login";

    /**
     * 列表接口
     */
    public static final String POST_INPUT_LIST = "http://9.988lhkj.com/input";
    /**
     * 详情接口
     */
    public static final String POST_CONTENT_ARTICLE = "http://9.988lhkj.com/articleRow";

    /**
     * 比分详情接口
     */
    public static final String POST_ROWS_ARTICLE = "http://9.988lhkj.com/footballRows";

    /**比分父列表*/

    public static final String GET_FOOTBALL_DATA = "http://9.988lhkj.com/football/data";

    /**比分详情列表接口
     *http://9.988lhkj.com/football
     */
    public static final String POST_FOOTBALL_LIST = "http://9.988lhkj.com/football";

    /**收藏列表
     *http://9.988lhkj.com/footballCollect
     */
    public static final String POST_FOOTBALL_COLLECT_LIST = "http://9.988lhkj.com/myCollection";
    /**收藏接口
     *http://9.988lhkj.com/footballCollect
     */
    public static final String POST_FOOTBALL_COLLECT = "http://9.988lhkj.com/footballCollect";




    //彩票类接口
    /**彩票接口list*/
    public static final String POST_LOTTERY_INDEX = "http://8.988lhkj.com/home/index/index";

    /**彩票开奖结果接口*/
    public static final String POST_LOTTERY_RESULT = "http://8.988lhkj.com/home/caipiao/detail";

    /**彩票开奖结果接口*/
    public static final String POST_LOTTERY_CPLIST = "http://8.988lhkj.com/home/index/cp_list";





}
