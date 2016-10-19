package com.yoga.china.http.config;

/**
 * Created by sunsiyuan on 16/3/9.
 */
public class HttpConstant {

    // 商户PID
    public static final String PARTNER = "2088121096280234";
    // 商户收款账号
    public static final String SELLER = "yogainchina@qq.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALhB8BS74Ie/z3dY1Q8NPDj8P8RNnuIy+9YHnS1YXAAXswanAAUb9DeH/x8e/cn+lyO9mu9o108MUbcBkJFCtzARLB+wwYJCpDDgaYsS23Ruxwgehs2sbEwa6hPpsWFqTSjQqykDMoYqmEXqJorXIfr5SI9mgC/0u5Qkf7IkxBXbAgMBAAECgYEAuBMnhY8sAHHfEZvtPdw7DbLzAWmKEPBeJrP0khhz0KPcdgjgPDpxAAkdFjof0GxHEa2Z2xMiBhaIjAtEIdpEpk4WbyiuhJ+D6G0TqIwyZBVL2avWUs5LsM9RRpm8vrtD7nbIQbCVfSP53Kq68YF/7YZfziaL8UKUa0C8jPN8sgECQQDjh3nzo+pvYRk5IDC+taV2P0Jnj3SBblifaL9UhMgPUlt1z/GUDx5FzLK+tKU0iPLYzDOzmugf6Iou1YWGE4m/AkEAz1BTaWr93r1uS/Zv+bRCTa0gFd8UdTIBY/lcrC8BfQClFhNM+69dKKWJBFCBDQIzZQAoH6ZgTGtCLpCYiaGi5QJAXDMg4u7wZ/Pj4bc+qXk4NIOL6CVEo9JQTUCID7rCyxqnf0UlcsbxkH1TjV3seC5qohmVddl26L81h6b0tZEjRwJBAMwK/D3aX0n2WfOL9wPmpgd7qQO9z43VjMbQLKUPksBsfQ9wKaniHKef3W6QqaObEeiLsfIMt+UfAEGCcY+stf0CQAiX2yN8VCrcngRudbCTi4ZwgYJNw2JXLDUN/CSUaXEueI/S3uHq/EptKjkpwRJLWpoF8AZh9Ut2hmFdRcgrFz0=";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "";

    // appid
    // 请同时修改 androidmanifest.xml里面，.PayActivityd里的属性<data
    // android:scheme="wxb4ba3c02aa476ea1"/>为新设置的appid
    public static final String APP_ID = "wx30d12656fc26429e";

    // 商户号
    public static final String MCH_ID = "1297950201";

    // API密钥，在商户平台设置
    public static final String API_KEY = "zyqr1FyjrBxNATppGlEnHbXYY4Fkv5uH";
    // 测试地址
//	 public static String BASE_URL = "http://192.168.199.201:8080/YJZG";
    // 李东
//	 public static String BASE_URL = "http://10.58.178.115:8080/YJZG";
    //  亚娟
//	 public static String BASE_URL = "http://10.58.178.81:8888/YJZG";
    // 外网测试
//	public static String BASE_URL = "http://112.64.173.178/YJZG";
    //客户阿里服务器
    public static String BASE_URL = "http://120.55.161.177/YJZG";

    /** 获取验证码 */
    public static String URL_GET_CODE = "/yjzg/mobi/memberUser/MobiMemberUserAction/getVerificationCode.mobi";
    /** 注册 */
    public static String URL_REGISTER = "/yjzg/mobi/memberUser/MobiMemberUserAction/register.mobi";
    /** 登录 */
    public static String URL_LOGIN = "/yjzg/mobi/memberUser/MobiMemberUserAction/login.mobi";
    /** 忘记密码 */
    public static String URL_FORGET_PASSWORLD = "/yjzg/mobi/memberUser/MobiMemberUserAction/changePassword.mobi";
    /** 轮播图 */
    public static String URL_SLIDEPLAY = "/yjzg/mobi/index/MobiIndexAction/getIndexImg.mobi";
    /** 首页轮播 */
    public static String URL_HOME_CAROUSEL = "/yjzg/mobi/index/MobiIndexAction/getCarouselList.mobi";
    /** 热门活动 */
    public static String URL_HOT_ACTION = "/yjzg/mobi/hotActivity/MobiHotActivityAction/getHotActivityList.mobi";
    /** 优惠券列表 */
    public static String URL_GET_COUPON_LIST = "/yjzg/mobi/activity/MobiActivityAction/getCouponList.mobi";
    /** 热门活动详情 */
    public static String URL_MOBI_ACTION = "/yjzg/mobi/activity/MobiActivityAction/getHotActivityById.mobi";
    /** 教学视频列表 */
    public static String URL_GET_VIDEO_LIST = "/yjzg/mobi/video/MobiVideoAction/getVideoList.mobi";
    /** 视频详情 */
    public static String URL_GET_VIDEO_BY_ID = "/yjzg/mobi/video/MobiVideoAction/getVideoById.mobi";
    /** 视频评论 */
    public static String URL_GET_VIDEO_COMMENT = "/yjzg/mobi/video/MobiVideoAction/getVideoCommentList.mobi";
    /** 添加评论 */
    public static String URL_ADD_VIDEO_COMMENT = "/yjzg/mobi/common/MobiMemberAction/addComment.mobi";
    /** 活动报名 个人信息 */
    public static String URL_GETUSER_MESSAGE = "/yjzg/mobi/activity/MobiActivityAction/getUserMessageById.mobi";
    /** 活动报名 */
    public static String URL_HOT_APPLY = "/yjzg/mobi/activity/MobiActivityAction/registration.mobi";
    /** 瑜伽老师列表 */
    public static String URL_YOGA_TEACHER = "/yjzg/mobi/teacher/MobiTeacherAction/getTeachers.mobi";
    /** 人物故事 */
    public static String URL_PEOPLE_STPRIES = "/yjzg/mobi/story/MobiStoryAction/getStroys.mobi";
    /** 人物故事详情 */
    public static String URL_PEOPLE_DETAILS = "/yjzg/mobi/story/MobiStoryAction/getStroyById.mobi";
    /** 瑜乐大众列表(搜索) */
    public static String URL_YOGA_HAPPY_SEACH = "/yjzg/mobi/message/MobiMessageRecreationAction/listMessageReareation.mobi";
    /** 发布瑜乐大众 */
    public static String URL_YOGA_HAPPY_PUBLISH = "/yjzg/mobi/message/MobiMessageRecreationAction/addMessageReareation.mobi";
    /** 瑜乐大众详情 */
    public static String URL_YOGA_HAPPY_DETAILS = "/yjzg/mobi/message/MobiMessageRecreationAction/getMessageReareationInfo.mobi";
    /** 瑜乐大众评论列表 */
    public static String URL_YOGA_HAPPY_COMMENT = "/yjzg/mobi/message/MobiMessageRecreationAction/listMessageReareationComment.mobi";
    /** 添加/取消收藏 点赞/取消赞 */
    public static String URL_ADD_OR_DELETE = "/yjzg/mobi/common/MobiMemberAction/addOrDeleteAction.mobi";
    /** 按用户搜索 */
    public static String URL_GET_USER_LIST = "/yjzg/mobi/message/MobiMessageAction/listUser.mobi";
    /** 练习感想 */
    public static String PRACTICE_FEELINGS = "/yjzg/mobi/message/MobiMessagePracticeAction/listMessagePractice.mobi";
    /** 练习感想详情 */
    public static String PRACTICE_FEELINGS_DETAILS = "/yjzg/mobi/message/MobiMessagePracticeAction/getMessagePracticeInfo.mobi";
    /** 练习感想品论列表 */
    public static String PRACTICE_FEELINGS_LIST = "/yjzg/mobi/message/MobiMessagePracticeAction/listMessagePracticeComment.mobi";
    /** 老师专栏 */
    public static String TEACHER_COLUME = "/yjzg/mobi/message/MobiMessageTeacherAction/listMessageTeacher.mobi";
    /** 老师专栏详情头部 */
    public static String TEACHER_COLUME_DETAILS = "/yjzg/mobi/message/MobiMessageTeacherAction/getMessageTeacherInfo.mobi";
    /** 老师专栏详情评论列表 */
    public static String TEACHER_COLUME_DETAILS_LIST = "/yjzg/mobi/message/MobiMessageTeacherAction/listMessageTeacherComment.mobi";
    /** 他人资料 */
    public static String URL_OTHERS_DETAILS = "/yjzg/mobi/individual/MobiIndividualAction/getMemberUserInfo.mobi";
    /** 他人发布 */
    public static String URL_OTHERS_PUBLISH = "/yjzg/mobi/individual/MobiIndividualAction/getMemberRecreation.mobi";
    /** 教练评价 */
    public static String URL_OTHERS_PRACTICE = "/yjzg/mobi/message/MobiMessagePracticeAction/listMessagePractice.mobi";
    /** 相册 */
    public static String URL_USER_IMAGES = "/yjzg/mobi/individual/MobiIndividualAction/getMemberPhoto.mobi";
    /** 老师专栏发布 */
    public static String PUBLISH_COLUME = "/yjzg/mobi/message/MobiMessageTeacherAction/addMessageTeacher.mobi";
    /** 发现列表 */
    public static String URL_FIND = "/yjzg/mobi/found/MobiFoundAction/listFound.mobi";
    /** 发现列表详情 */
    public static String URL_FIND_DETAILS = "/yjzg/mobi/found/MobiFoundAction/getFoundInfo.mobi";
    /** 商品列表 */
    public static String INTEGRAL_MALL_LIST = "/yjzg/mobi/commodity/MobiCommodityAction/listCommodity.mobi";
    /** 商品详情 */
    public static String MALL_DETAILS = "/yjzg/mobi/commodity/MobiCommodityAction/getCommodity.mobi";
    /** 商品购买/增加订单 */
    public static String MALL_INDENT = "/yjzg/mobi/order/MobiOrderAction/addOrder.mobi";
    /** 我的订单列表 */
    public static String MY_INDENT = "/yjzg/mobi/order/MobiOrderAction/listOrder.mobi";
    /** 订单详情 */
    public static String INDENT_DETAILS = "/yjzg/mobi/order/MobiOrderAction/getOrder.mobi";
    /** 我的会馆 */
    public static String URL_GET_MY_GYMS = "/yjzg/mobi/commercial/MobiCommercialManagementAction/listMyCommercial.mobi";
    /** 热门会馆 */
    public static String URL_GET_HOT_GYMS = "/yjzg/mobi/commercial/MobiCommercialManagementAction/listCommercial.mobi";
    /** 商户详情 */
    public static String URL_GYM_DETAILS = "/yjzg/mobi/commercial/MobiCommercialManagementAction/getCommercialInfo.mobi";
    /** 商户相册 */
    public static String URL_GYM_IMAGES = "/yjzg/mobi/commercial/MobiCommercialManagementAction/listPhoto.mobi";
    /** 商户评价 */
    public static String URL_GYM_PRACTICE = "/yjzg/mobi/message/MobiMessagePracticeAction/listCommercialMessagePractice.mobi";
    /** 可预约课程列表 */
    public static String URL_ORDER_COURSE_LIST = "/yjzg/mobi/course/MobiCourseAction/listCourse.mobi";
    /** 预约课程 */
    public static String URL_ORDER_COURSE = "/yjzg/mobi/course/MobiCourseAction/addMakeAppointment.mobi";
    /** 判断是否是第一次设置密码 */
    public static String ISFRIST_PASSWORD = "/yjzg/mobi/memberUser/MobiMemberUserAction/isHasFundPassword.mobi";
    /** 判断是否是第一次设置密码 */
    public static String INFORMATION_CHANGES = "/yjzg/mobi/memberUser/MobiMemberAction/addMember.mobi";
    /** 教练详情 */
    public static String COACH_DETAILS = "/yjzg/mobi/memberUser/MobiMemberAction/getUserShow.mobi";
    /** 意见反馈 */
    public static String ADVICE_FEEDBACK = "/yjzg/mobi/suggestion/MobiSuggestionAction/addSuggestionAction.mobi";
    /** 首次设置支付密码 */
    public static String FIRST_PASSWORD = "/yjzg/mobi/memberUser/MobiMemberUserAction/setFundPassword.mobi";
    /** 原支付密码 */
    public static String ORIGINAL_PASSWORD = "/yjzg/mobi/memberUser/MobiMemberUserAction/identifyFundPassword.mobi";
    /** 新密码设置 */
    public static String NEWS_PASSWORD = "/yjzg/mobi/memberUser/MobiMemberUserAction/setFundPassword.mobi";
    /** 改变电话号码 */
    public static String CHANGE_TELEPHONE = "/yjzg/mobi/memberUser/MobiMemberUserAction/changeTelephone.mobi";
    /** 取消预约课程 */
    public static String URL_CANCEL_ORDER_COURSE = "/yjzg/mobi/course/MobiCourseAction/cancelMakeAppointment.mobi";
    /** 教练列表 */
    public static String URL_GET_TEACHERS = "/yjzg/mobi/coach/MobiCoachAction/listRecommendCoach.mobi";
    /** 教练详情 */
    public static String URL_GET_TEACHERS_DETAILS = "/yjzg/mobi/coach/MobiCoachAction/getCoachInfo.mobi";
    /** 场馆会员卡列表/我的会员卡列表（续卡续费） */
    public static String URL_MEMBERSHIP_CARD = "/yjzg/mobi/commercial/MobiCommercialComboAction/getCommercialCombo.mobi";
    /** 点赞列表 */
    public static String URL_SUPPORT_LIST = "/yjzg/mobi/message/MobiMessageAction/listSupportUser.mobi";
    /** 我的发布娱乐大众 */
    public static String URL_PUBLISH_PUBLIC = "/yjzg/mobi/message/MobiMessageRecreationAction/listMessageReareation.mobi";
    /** 我的发布文章专栏 */
    public static String URL_PUBLISH_ARTICLE = "/yjzg/mobi/message/MobiMessageTeacherAction/listMessageTeacher.mobi";
    /** 收藏 */
    public static String URL_MY_COLLECT = "/yjzg/mobi/collect/MobiCollectAction/listCollection.mobi";
    /** 套餐详情 */
    public static String URL_MEMBERSHIP_CARD_DETAILS = "/yjzg/mobi/commercial/MobiCommercialComboAction/getCommercialComboInfo.mobi";
    /** 商户图文详情 */
    public static String URL_GYM_INFOR_DETAILS = "/yjzg/mobi/commercial/MobiCommercialManagementAction/getContentAndPicture.mobi";
    /** 我预约的课程列表 */
    public static String URL_MY_ORDER_COURSE_LIST = "/yjzg/mobi/course/MobiCourseAction/listMyMake.mobi";
    /** 个人详情 */
    public static String URL_GET_USER_INFOR = "/yjzg/mobi/memberUser/MobiMemberAction/getMemberById.mobi";
    /** 预约课程详情 */
    public static String URL_MY_ORDER_COURSE_DETAILS = "/yjzg/mobi/course/MobiCourseAction/getMakeInfo.mobi";
    /** 个人详情修改 */
    public static String URL_CHANGGE_MESSAGE = "/yjzg/mobi/memberUser/MobiMemberAction/addMember.mobi";
    /** 我的点评 */
    public static String URL_MY_COMMENT = "/yjzg/mobi/message/MobiMessagePracticeAction/listMyPractice.mobi";
    /** 收到的点评 */
    public static String URL_RECEIVE_COMMENT = "/yjzg/mobi/message/MobiMessagePracticeAction/listMyCourse.mobi";
    /** 收到的点评列表 */
    public static String URL_RECEIVE_COMMENT_LIST = "/yjzg/mobi/message/MobiMessagePracticeAction/listCourseComment.mobi";
    /** 发布评论 */
    public static String URL_PUBLISH_COMMENT = "/yjzg/mobi/message/MobiMessagePracticeAction/addMessagePractice.mobi";
    /** 我报名的活动列表 */
    public static String URL_MY_APPLIED_ACTIVITY = "/yjzg/mobi/activity/MobiActivityPayAction/getActivityList.mobi";
    /** 我的授课列表 */
    public static String URL_MY_TEACH_COURSE = "/yjzg/mobi/activity/MobiActivityPayAction/getCourseList.mobi";
    /** 活动详情 */
    public static String URL_MY_ACTIVITY_DEATILS = "/yjzg/mobi/activity/MobiActivityPayAction/getActivityById.mobi";
    /** 小我 */
    public static String URL_INDIVIDUAL = "/yjzg/mobi/memberUser/MobiMemberAction/getUserInfo.mobi";
    /** 签到 */
    public static String URL_SIGN_NTEGRAL = "/yjzg/mobi/memberUser/MobiMemberAction/memberSignIn.mobi";
    /** 编辑我的故事 */
    public static String URL_COMPILE = "/yjzg/mobi/memberUser/MobiMemberAction/changeShow.mobi";
    /** 我的发布删除 */
    public static String URL_DELETE = "/yjzg/mobi/message/MobiMessageAction/deleteMessage.mobi";
    /** 互相关注 */
    public static String URL_EACH_ATTENTION_LIST = "/yjzg/mobi/individual/MobiIndividualAction/listEach.mobi";
    /** 我的关注 */
    public static String URL_MY_ATTENTION_LIST = "/yjzg/mobi/individual/MobiIndividualAction/listMyAttention.mobi";
    /** 粉丝列表 */
    public static String URL_FANS_LIST = "/yjzg/mobi/individual/MobiIndividualAction/listAttentionForMe.mobi";
    /** 增加体验课程申请 */
    public static String URL_ADD_EXPERIENCE_COURSE = "/yjzg/mobi/experience/MobiExperienceAction/saveExperience.mobi";
    /** 时间列表 */
    public static String URL_ORDER_TIME_LIST = "/yjzg/mobi/course/MobiCourseAction/listMyDate.mobi";
    /** 教练认证 */
    public static String URL_COACH_IDENTIFY = "/yjzg/mobi/teacher/MobiTeacherAction/AuthenticateCoach.mobi";
    /** 授课详情 */
    public static String URL_MY_TEACH_COURSE_DETAILS = "/yjzg/mobi/activity/MobiActivityPayAction/getCourseById.mobi";
    /** 我的会员卡 */
    public static String URL_MY_CARD = "/yjzg/mobi/memberUser/MobiMemberAction/getUserMessage.mobi";
    /** 积分明细 */
    public static String URL_INTEGRAL_DETAIL = "/yjzg/mobi/memberUser/MobiMemberAction/listIntegralRecord.mobi";
    /** 账户交易明细 */
    public static String URL_DEAL_DETAIL = "/yjzg/mobi/memberUser/MobiMemberAction/listMoneyRecord.mobi";
    /** 支付宝 */
    public static String URL_ALIPAY = "/interfacte/AliPayBackAction/excute.mobi";
    /** 购买视频 */
    public static String URL_BUY_VIDEO = "/yjzg/mobi/video/MobiVideoAction/buyVideo.mobi";
    /** 浏览历史 */
    public static String URL_MY_VIDEO_COOKIES = "/yjzg/mobi/video/MobiVideoAction/listMyScanVideo.mobi";
    /** 购买历史 */
    public static String URL_MY_BUY_VIDEO_HISTORY = "/yjzg/mobi/video/MobiVideoAction/listMyBoughtVideo.mobi";
    /** 会员卡开通/续卡续费 */
    public static String URL_OPEN_MEMBER_CARD = "/yjzg/mobi/commercial/MobiCommercialComboAction/openMemberCard.mobi";
    /** 充值 */
    public static String URL_RECHARGE = "/yjzg/mobi/memberUser/MobiMemberAction/recharge.mobi";
    /** 提现 */
    public static String URL_WITHDRAW_DEPOSIT = "/yjzg/mobi/memberUser/MobiMemberAction/getOutMoney.mobi";
    /** 找回支付宝密码 */
    public static String URL_MAIL_PASSWORD = "/yjzg/mobi/memberUser/MobiMemberUserAction/findFundPassword.mobi";
    /** 记录视屏下载 */
    public static String URL_ADD_VIDEO_DOWNLOAD = "/yjzg/mobi/video/MobiVideoAction/recordDownload.mobi";
    /** 下载历史 */
    public static String URL_GET_VIDEO_DOWNLOAD_HISTORY = "/yjzg/mobi/video/MobiVideoAction/listMyDownloadVideo.mobi";
    /** 积分规则 */
    public static String URL_INTEGRAL_RULE = "/yjzg/mobi/order/MobiOrderAction/getIntegralRule.mobi";

    /** 推送常量 */
    public static final String ACTION_PUSHMSG = "com.bm.yogainchina.util.push";
    /** 微信回调 */
    public static final String WX_SYNTONY = "/interfacte/WeChatBackAction/excute.mobi";
    /** 分享接口 */
    public static final String SHARE_PORT = "/yjzg/mobi/message/MobiMessagePracticeAction/shareMessage.mobi";
    /** 消息通知设置列表 */
    public static final String URL_GET_MESSAGE_SET = "/yjzg/mobi/messageSet/MobiMessageSetAction/getMessageSet.mobi";
    /** 消息通知设置 */
    public static final String URL_SET_MESSAGE_SET = "/yjzg/mobi/messageSet/MobiMessageSetAction/setMessageSet.mobi";
    /** 是否在黑名单 */
    public static final String URL_BLACKLIST = "/yjzg/mobi/memberUser/MobiMemberAction/isBlack.mobi";


}
