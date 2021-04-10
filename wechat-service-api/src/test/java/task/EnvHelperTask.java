package task;

import wechat.apiobject.DepartmentObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @Author FunQuinn
 * @Description:  封装清理方法
 * @Date 2021/3/7 22:52
 */
public class EnvHelperTask {
    private static final Logger logger = LoggerFactory.getLogger(EnvHelperTask.class);

    public static void clearDepartMentTask(String accessToken){

        ArrayList<Integer> departmentIds = DepartmentObject.searchDepartment("",accessToken).path("department.id");
        for( int departmentId:departmentIds){
            if(1==departmentId)
                continue;
            DepartmentObject.deleteDepartment(departmentId+"",accessToken);
        }
    }
}
