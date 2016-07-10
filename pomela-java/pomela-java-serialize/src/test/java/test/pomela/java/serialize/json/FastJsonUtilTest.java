//package test.pomela.java.serialize.json;
//
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import org.junit.Test;
//import pomela.java.common.entities.EntityMockerFactory;
//import pomela.java.common.entities.Order;
//import pomela.java.common.entities.OrderMocker;
//import pomela.java.common.utils.PrintUtil;
//import pomela.java.serialize.json.FastJsonUtil;
//import pomela.java.serialize.json.fastjson.SimplePropertyPreFilterCreator;
//import pomela.java.common.entities.CommRet;
//
//import java.util.List;
//
///**
// * Created by hetor on 15/10/18.
// */
//public class FastJsonUtilTest {
//
//    private OrderMocker orderMocker = EntityMockerFactory.getOrderMocker();
//
//    @Test
//    public void test_toJson_Filter_Features() {
//        Order order = orderMocker.mockOne();
//        PrintUtil.toConsole(FastJsonUtil.toJson(order, SimplePropertyPreFilterCreator.newInstance(Order.class, "title", "updateTime")));
//
//        List<Order> orders = orderMocker.mockList(2);
//        PrintUtil.toConsole(FastJsonUtil.toJson(orders, SimplePropertyPreFilterCreator.newInstance("title", "updateTime"),
//                SerializerFeature.DisableCircularReferenceDetect));
//
//        //TODO: multi Filters
//
//    }
//
//    @Test
//    public void test_toJson_Config_Filter_Features() {
//
//    }
//
//
//    @Test
//    public void test_toJson_DateFormat() {
//        Order order = orderMocker.mockOne();
//        PrintUtil.toConsole(FastJsonUtil.toJson(order, "yyyy-MM-dd"));
//    }
//
//    @Test
//    public void test_toJson_CircularRefDetect() {
//        List<Order> orders = orderMocker.mockList(2);
//        CommRet<List<Order>> commRet = CommRet.newInstance();
//        commRet.setId(1);
//        commRet.setCode(CommRet.SUCC);
//        commRet.setData(orders);
//        commRet.getExtras().put("circularRef", commRet);
//
//        PrintUtil.toConsole(FastJsonUtil.toJson(commRet));
//        PrintUtil.toConsole(FastJsonUtil.toJson(commRet,
//                SerializerFeature.PrettyFormat,
////                SerializerFeature.DisableCircularReferenceDetect,
//                SerializerFeature.IgnoreNonFieldGetter));
//    }
//
//    @Test
//    public void test_toJson_Filter_Feature() {
//
//    }
//}
