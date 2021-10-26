package utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 服务代理类
 */
public class ServiceProxy implements InvocationHandler {
    private Object obj;

    public ServiceProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args){
        //方法之前用换行隔开上次的方法输出
        System.out.println();
        System.out.println();
        Object res = null;

        try {
            //被增强的原方法执行
            res = method.invoke(obj, args);

            //操作失败则回滚
            if ((int) res == -1) {
                JdbcUtils.rollbackAndClose();
                System.out.println("操作失败！请确保操作的正确性与合理性！");
            } else {//操作成功则提交
                JdbcUtils.commitAndClose();
                System.out.println("操作成功！");
            }
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();
            System.out.println("您所输入的数据有误！");
        } finally {
            System.out.println();
            System.out.println();
            return res;
        }
    }
}
